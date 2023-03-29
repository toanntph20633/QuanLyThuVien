package com.example.quanlythuvien.cores.bookmanager.repository;

import com.example.quanlythuvien.entities.Book;
import com.example.quanlythuvien.entities.BookDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import repositories.BookDetailRepository;

import java.util.Optional;

@Repository
public interface BmBookDetailRepository extends BookDetailRepository {
    Optional<BookDetail> findByBookDetailCode(String code);

    Integer countBookDetailByBookIdAndStatusAction(Book book, int status);
}
