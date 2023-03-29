package com.example.quanlythuvien.cores.bookmanager.mapper;

import com.example.quanlythuvien.cores.bookmanager.entity.request.BmBookCreateReq;
import com.example.quanlythuvien.cores.bookmanager.entity.response.BmBookCaregoryRes;
import com.example.quanlythuvien.cores.bookmanager.entity.response.BmBookDetailRes;
import com.example.quanlythuvien.cores.bookmanager.entity.response.BmBookRes;
import com.example.quanlythuvien.cores.bookmanager.entity.response.BmListBookRes;
import com.example.quanlythuvien.entities.Book;
import com.example.quanlythuvien.entities.BookCategory;
import com.example.quanlythuvien.entities.BookDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BmBookMapper {
    @Mapping(target = "bookCategoryName", source = "book.bookCategoryId.bookCategoryName")
    BmListBookRes toBmListBookRes(Book book);

    Book toBook(BmBookCreateReq req);

    BmBookRes toBmBookRes(Book book);

    BmBookCaregoryRes toBmBookCaregoryRes(BookCategory bookCategory);

    BmBookDetailRes toBmBookDetailRes(BookDetail bookDetail);
}
