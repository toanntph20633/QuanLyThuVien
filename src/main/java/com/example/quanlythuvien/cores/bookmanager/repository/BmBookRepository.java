package com.example.quanlythuvien.cores.bookmanager.repository;

import com.example.quanlythuvien.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import repositories.BookRepository;

import java.util.Optional;

@Repository
public interface BmBookRepository extends BookRepository {
    @Query("select b from Book b where b.statusAction = :status and " +
            "(b.bookCode like :keyword or b.bookName like :keyword)")
    Page<Book> findByActive(@Param("status") int status,
                            @Param("keyword") String keyword,
                            Pageable pageable);

    Optional<Book> findByBookCode(String bookCode);

}
