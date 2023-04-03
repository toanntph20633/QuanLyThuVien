package com.example.quanlythuvien.cores.bookmanager.service.impl;

import com.example.quanlythuvien.cores.bookmanager.entity.request.BmBookCreateReq;
import com.example.quanlythuvien.cores.bookmanager.entity.request.BmBookUpdateReq;
import com.example.quanlythuvien.cores.bookmanager.entity.request.BmListBookReq;
import com.example.quanlythuvien.cores.bookmanager.entity.response.BmBookRes;
import com.example.quanlythuvien.cores.bookmanager.entity.response.BmListBookRes;
import com.example.quanlythuvien.cores.bookmanager.mapper.BmBookMapper;
import com.example.quanlythuvien.cores.bookmanager.repository.BmBookCategoryRepository;
import com.example.quanlythuvien.cores.bookmanager.repository.BmBookDetailRepository;
import com.example.quanlythuvien.cores.bookmanager.repository.BmBookRepository;
import com.example.quanlythuvien.cores.bookmanager.service.BmBookService;
import com.example.quanlythuvien.entities.Book;
import com.example.quanlythuvien.entities.BookCategory;
import com.example.quanlythuvien.entities.BookDetail;
import com.example.quanlythuvien.exceptions.exception.BadRequestException;
import com.example.quanlythuvien.exceptions.exception.NotFoundException;
import com.example.quanlythuvien.utilities.enumclass.StatusAction;
import com.example.quanlythuvien.utilities.enumclass.StatusLive;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BmBookServiceImpl implements BmBookService {
    @Autowired
    private BmBookRepository bmBookRepository;
    @Autowired
    private BmBookCategoryRepository bmBookCategoryRepository;
    @Autowired
    private BmBookDetailRepository bmBookDetailRepository;
    @Autowired
    private BmBookMapper bmBookMapper;

    @Override
    public List<BmListBookRes> getList(BmListBookReq req) {
        return bmBookRepository.findByActive(req.getStatusAction(),
                        "%" + req.getKeyword() + "%", PageRequest.of(req.getOffset(), req.getLimit()))
                .map(book -> bmBookMapper.toBmListBookRes(book)).stream().toList();
    }

    @Transactional
    @Override
    public void save(BmBookCreateReq req) {
        Book book = bmBookMapper.toBook(req);
        if (Objects.nonNull(book)) {
            if (bmBookRepository.findByBookCode(book.getBookCode()).isPresent()) {
                throw new BadRequestException("Mã này đã tồn tại");
            }
            BookCategory bookCategory = bmBookCategoryRepository.findById(req.getIdBookCategory()).orElseThrow(() -> new NotFoundException("Không tìm thấy danh mục sách"));
            book.setBookCategoryId(bookCategory);
            book.setStatusLive(StatusLive.CREATED.getValue());
            book.setStatusAction(StatusAction.ACTIVE.getValue());
            bmBookRepository.save(book);
            for (int i = 0; i < req.getQuantity(); i++) {
                String code = "";
                while (true) {
                    code = "BD" + (Math.random() * 1000000000000000000L);
                    if (bmBookDetailRepository.findByBookDetailCode(code).isEmpty()) {
                        break;
                    }
                }
                bmBookDetailRepository.save(BookDetail.builder().bookId(book).bookDetailCode(code)
                        .statusLive(StatusLive.CREATED.getValue())
                        .statusAction(StatusAction.BOOK_DETAIL_TRONGKHO.getValue()).build());
            }
        }
    }

    @Transactional
    @Override
    public void update(BmBookUpdateReq req) {
        Book book = bmBookRepository.findById(req.getId()).
                orElseThrow(() -> new NotFoundException("Không tìm thấy thông tin sách"));
        book.setBookName(req.getBookName());
        book.setBookDescription(req.getBookDescription());
        BookCategory bookCategory = bmBookCategoryRepository.findById(req.getIdBookCategory()).
                orElseThrow(() -> new NotFoundException("Không tìm thấy thông tin danh mục sách"));
        book.setBookCategoryId(bookCategory);
        bmBookRepository.save(book);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        Book book = bmBookRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Không tìm thấy thông tin sách"));
        book.setStatusLive(StatusLive.DELETED.getValue());
        bmBookRepository.save(book);
    }

    @Transactional
    @Override
    public BmBookRes getOne(UUID id) {
        Book book = bmBookRepository.findById(id).orElseThrow(() -> new NotFoundException("Không tìm thấy thông tin sách"));
        BmBookRes res = bmBookMapper.toBmBookRes(book);
        res.setSumBookBorrow(bmBookDetailRepository.countBookDetailByBookIdAndStatusAction(book, StatusAction.BOOK_DETAIL_DANGMUON.getValue()));
        res.setSumBrokenBook(bmBookDetailRepository.countBookDetailByBookIdAndStatusAction(book, StatusAction.BOOK_DETAIL_HONG.getValue()));
        res.setSumLostBook(bmBookDetailRepository.countBookDetailByBookIdAndStatusAction(book, StatusAction.BOOK_DETAIL_THATLAC.getValue()));
        res.setSumActiveBook(bmBookDetailRepository.countBookDetailByBookIdAndStatusAction(book, StatusAction.BOOK_DETAIL_TRONGKHO.getValue()));
        res.setBookCategoryId(bmBookMapper.toBmBookCaregoryRes(book.getBookCategoryId()));
        res.setBmBookDetailRes(book.getBookDetails().stream().map(bookDetail -> bmBookMapper.toBmBookDetailRes(bookDetail))
                .collect(Collectors.toSet()));
        return res;
    }
}
