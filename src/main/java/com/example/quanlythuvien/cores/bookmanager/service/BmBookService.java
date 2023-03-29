package com.example.quanlythuvien.cores.bookmanager.service;

import com.example.quanlythuvien.cores.bookmanager.entity.request.BmBookCreateReq;
import com.example.quanlythuvien.cores.bookmanager.entity.request.BmBookUpdateReq;
import com.example.quanlythuvien.cores.bookmanager.entity.request.BmListBookReq;
import com.example.quanlythuvien.cores.bookmanager.entity.response.BmBookRes;
import com.example.quanlythuvien.cores.bookmanager.entity.response.BmListBookRes;

import java.util.List;
import java.util.UUID;

public interface BmBookService {
    List<BmListBookRes> getList(BmListBookReq req);

    void save(BmBookCreateReq req);

    void update(BmBookUpdateReq req);

    void delete(UUID id);

    BmBookRes getOne(UUID id);
}
