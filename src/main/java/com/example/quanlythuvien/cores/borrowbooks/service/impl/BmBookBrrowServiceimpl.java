package com.example.quanlythuvien.cores.borrowbooks.service.impl;

import com.example.quanlythuvien.cores.bookmanager.repository.BmBookDetailRepository;
import com.example.quanlythuvien.cores.borrowbooks.entity.request.BrrowBookReq;
import com.example.quanlythuvien.cores.borrowbooks.repository.BbBookDetailRepository;
import com.example.quanlythuvien.cores.borrowbooks.repository.BbLoanSlipDetailRepository;
import com.example.quanlythuvien.cores.borrowbooks.repository.BbLoanSlipRepository;
import com.example.quanlythuvien.cores.borrowbooks.repository.BbStudentRepository;
import com.example.quanlythuvien.cores.borrowbooks.service.BmBookBrrowService;
import com.example.quanlythuvien.entities.BookDetail;
import com.example.quanlythuvien.entities.LoanSlip;
import com.example.quanlythuvien.entities.LoanSlipDetail;
import com.example.quanlythuvien.entities.Student;
import com.example.quanlythuvien.exceptions.exception.BadRequestException;
import com.example.quanlythuvien.exceptions.exception.NotFoundException;
import com.example.quanlythuvien.utilities.enumclass.StatusAction;
import com.example.quanlythuvien.utilities.enumclass.StatusLive;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
public class BmBookBrrowServiceimpl implements BmBookBrrowService {
    @Autowired
    private BbBookDetailRepository bmBookDetailRepository;
    @Autowired
    private BbLoanSlipDetailRepository bbLoanSlipDetailRepository;
    @Autowired
    private BbLoanSlipRepository bbLoanSlipRepository;
    @Autowired
    private BbStudentRepository bbStudentRepository;

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
        LoanSlip loanSlip = bbLoanSlipRepository.save(LoanSlip.builder().loanSlipCode(req.getLoanSlipCode())
                .sinhVienId(student).lastDate(req.getLastDate()).statusAction(StatusAction.LOANSLIP_DANGMUON.getValue())
                .statusLive(StatusLive.CREATED.getValue()).userId(null).build());//tam thoi

        //Chi tiết phiếu
        for (UUID id : req.getBookDetailIds()) {
            BookDetail bookDetail = bmBookDetailRepository.findById(id).orElseThrow(() -> new NotFoundException("Không tồn tại sách này"));
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
            bmBookDetailRepository.save(bookDetail);
        }

    }
}
