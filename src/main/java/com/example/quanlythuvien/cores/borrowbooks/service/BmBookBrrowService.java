package com.example.quanlythuvien.cores.borrowbooks.service;

import com.example.quanlythuvien.cores.borrowbooks.entity.request.BrrowBookReq;
import com.example.quanlythuvien.cores.borrowbooks.entity.request.ReturnBookReq;

public interface BmBookBrrowService {
    void brrowBook(BrrowBookReq req);

    void returnBook(ReturnBookReq req);
}
