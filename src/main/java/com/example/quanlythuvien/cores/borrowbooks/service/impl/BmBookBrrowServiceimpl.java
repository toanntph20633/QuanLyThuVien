package com.example.quanlythuvien.cores.borrowbooks.service.impl;

import com.example.quanlythuvien.cores.bookmanager.repository.BmBookDetailRepository;
import com.example.quanlythuvien.cores.borrowbooks.entity.request.BrrowBookReq;
import com.example.quanlythuvien.cores.borrowbooks.entity.request.ReturnBookReq;
import com.example.quanlythuvien.cores.borrowbooks.repository.BbBookDetailRepository;
import com.example.quanlythuvien.cores.borrowbooks.repository.BbLoanSlipDetailRepository;
import com.example.quanlythuvien.cores.borrowbooks.repository.BbLoanSlipRepository;
import com.example.quanlythuvien.cores.borrowbooks.repository.BbStudentRepository;
import com.example.quanlythuvien.cores.borrowbooks.repository.BbUserRepository;
import com.example.quanlythuvien.cores.borrowbooks.service.BmBookBrrowService;
import com.example.quanlythuvien.entities.BookDetail;
import com.example.quanlythuvien.entities.LoanSlip;
import com.example.quanlythuvien.entities.LoanSlipDetail;
import com.example.quanlythuvien.entities.Student;
import com.example.quanlythuvien.entities.Users;
import com.example.quanlythuvien.exceptions.exception.BadRequestException;
import com.example.quanlythuvien.exceptions.exception.NotFoundException;
import com.example.quanlythuvien.utilities.enumclass.StatusAction;
import com.example.quanlythuvien.utilities.enumclass.StatusLive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BmBookBrrowServiceimpl implements BmBookBrrowService {
    @Autowired
    private BbBookDetailRepository bbBookDetailRepository;
    @Autowired
    private BbLoanSlipDetailRepository bbLoanSlipDetailRepository;
    @Autowired
    private BbLoanSlipRepository bbLoanSlipRepository;
    @Autowired
    private BbStudentRepository bbStudentRepository;
    @Autowired
    private BbUserRepository bbUserRepository;

    @Transactional
    @Override
    public void brrowBook(BrrowBookReq req) {
        if (bbLoanSlipRepository.findByLoanSlipCodeAndStatusActionInAndStatusLiveIn(req.getLoanSlipCode()
                , List.of(StatusAction.LOANSLIP_DANGMUON.getValue(), StatusAction.LOANSLIP_CHUATRA.getValue())
                , List.of(StatusLive.CREATED.getValue(), StatusLive.UPDATED.getValue())).isPresent()) {
            throw new BadRequestException("Mã đã tồn tại");
        }
        Student student = bbStudentRepository.findById(req.getStudentId()).orElseThrow(() -> new NotFoundException("Không tìm thấy sinh viên"));
        long date = LocalDate.now().until(req.getLastDate(), ChronoUnit.DAYS);
        if (date < 0) {
            throw new BadRequestException("Ngày trả phải hơn hoặc bằng ngày hôm nay");
        }
        if (date > 14) {
            throw new BadRequestException("Ngày trả không được quá 14 ngày");
        }
        Users users = bbUserRepository.findById(req.getUserID()).orElseThrow(() -> new NotFoundException("Không tìm thấy user!"));
        LoanSlip loanSlip = bbLoanSlipRepository.save(LoanSlip.builder().loanSlipCode(req.getLoanSlipCode())
                .sinhVienId(student).lastDate(req.getLastDate()).statusAction(StatusAction.LOANSLIP_DANGMUON.getValue())
                .statusLive(StatusLive.CREATED.getValue()).userId(users).build());//tam thoi

        //Chi tiết phiếu
        for (UUID id : req.getBookDetailIds()) {
            BookDetail bookDetail = bbBookDetailRepository.findById(id).orElseThrow(() -> new NotFoundException("Không tồn tại sách này"));
            if (bookDetail.getStatusAction() == StatusAction.BOOK_DETAIL_THATLAC.getValue() ||
                    bookDetail.getStatusAction() == StatusAction.BOOK_DETAIL_DANGMUON.getValue() ||
                    bookDetail.getStatusAction() == StatusAction.BOOK_DETAIL_HONG.getValue()) {
                throw new BadRequestException("Sách này không mượn được: "
                        + StatusAction.valueOf(bookDetail.getStatusAction()).getValue2());
            }
            bbLoanSlipDetailRepository.save(LoanSlipDetail.builder().bookDetailId(bookDetail)
                    .loanSlipId(loanSlip).statusAction(StatusAction.LOANSLIPDETAIL_DANGMUON.getValue())
                    .statusLive(StatusLive.CREATED.getValue()).build());
            bookDetail.setStatusAction(StatusAction.BOOK_DETAIL_DANGMUON.getValue());
            bbBookDetailRepository.save(bookDetail);
        }

    }

    @Transactional
    @Override
    public void returnBook(ReturnBookReq req) {
        LoanSlip loanSlip = bbLoanSlipRepository.findByLoanSlipCodeAndStatusAction(req.getMaPhieu(), StatusAction.LOANSLIP_DANGMUON.getValue()).orElseThrow(() -> new NotFoundException("Không tìm thấy phiếu mượn"));

        //Lấy danh sách các chi tiết phiếu đang mượn
        Set<UUID> bookDetails = loanSlip.getLoanSlipDetails().stream().filter(loanSlipDetail ->
                        loanSlipDetail.getStatusAction() == StatusAction.LOANSLIPDETAIL_DANGMUON.getValue())
                .map(n -> n.getBookDetailId().getId())
                .collect(Collectors.toSet());
        //Duyệt set
        for (UUID id : req.getBookDetails()) {
            BookDetail bookDetail = bbBookDetailRepository.findById(id).orElseThrow(() -> new NotFoundException("Không tìm thấy sách"));
            //Kiểm tra sách có tồn tại trong phiếu mượn không
            if (bookDetails.contains(id)) {
                LoanSlipDetail loanSlipDetail = bbLoanSlipDetailRepository.findByBookDetailIdAndAndLoanSlipId(bookDetail, loanSlip)
                        .orElseThrow(() -> new NotFoundException("Không tìm thấy sách trong phiếu mượn"));
                //Kiểm tra trạng thái sách
                if (loanSlipDetail.getStatusAction() == StatusAction.LOANSLIPDETAIL_DATRA.getValue()
                        || loanSlipDetail.getStatusAction() == StatusAction.LOANSLIPDETAIL_TRAMUON.getValue()) {
                    throw new BadRequestException("Sách mã số " + bookDetail.getBookDetailCode() + "Đã được trả");
                }
                //Kiểm tra ngày mượn
                if (loanSlipDetail.getDateCreate().until(LocalDate.now(), ChronoUnit.DAYS) > 14) {
                    loanSlipDetail.setStatusAction(StatusAction.LOANSLIPDETAIL_TRAMUON.getValue());
                } else {
                    loanSlipDetail.setStatusAction(StatusAction.LOANSLIPDETAIL_DATRA.getValue());
                }
                //Cập nhật lại phiếu sau khi trả
                loanSlipDetail.setStatusLive(StatusLive.UPDATED.getValue());
                bbLoanSlipDetailRepository.save(loanSlipDetail);
                //Cập nhật trạng thái của sách sau khi trả
                bookDetail.setStatusAction(StatusAction.valueOf(req.getTrangThaiSach()).getValue());
                //Cập nhật lại thông tin
                bookDetail.setStatusLive(StatusLive.UPDATED.getValue());
                bbBookDetailRepository.save(bookDetail);
            } else {
                throw new BadRequestException("Sách mã số " + bookDetail.getBookDetailCode() + " không nằm trong danh sách mượn của sinh viên này");
            }

            //Cập nhật lại phiếu mượn
            if (bbLoanSlipDetailRepository.countByLoanSlipIdAndStatusActionIn(loanSlip, List.of(StatusAction.LOANSLIPDETAIL_DANGMUON.getValue())) == 0) {
                //Kiểm tra ngày mượn
                if (loanSlip.getDateCreate().until(LocalDate.now(), ChronoUnit.DAYS) > 14) {
                    loanSlip.setStatusAction(StatusAction.LOANSLIP_TRAQUAHAN.getValue());
                } else {
                    loanSlip.setStatusAction(StatusAction.LOANSLIP_DATRA.getValue());
                }
                loanSlip.setStatusLive(StatusLive.UPDATED.getValue());
                bbLoanSlipRepository.save(loanSlip);
            } else {
                if (loanSlip.getDateCreate().until(LocalDate.now(), ChronoUnit.DAYS) > 14) {
                    loanSlip.setStatusAction(StatusAction.LOANSLIP_CHUATRA.getValue());
                    loanSlip.setStatusLive(StatusLive.UPDATED.getValue());
                    bbLoanSlipRepository.save(loanSlip);
                }
            }
        }

    }
}
