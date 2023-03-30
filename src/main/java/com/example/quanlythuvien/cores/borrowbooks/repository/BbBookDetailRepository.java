package com.example.quanlythuvien.cores.borrowbooks.repository;

import com.example.quanlythuvien.entities.LoanSlipDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import repositories.BookDetailRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BbBookDetailRepository extends BookDetailRepository {
    @Query("select l from  LoanSlipDetail l where l.loanSlipId.sinhVienId.id = :id")
    Optional<LoanSlipDetail> findByStudentId(@Param("id") UUID id);
}
