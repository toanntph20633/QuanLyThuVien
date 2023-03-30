package com.example.quanlythuvien.cores.borrowbooks.repository;

import com.example.quanlythuvien.entities.BookDetail;
import com.example.quanlythuvien.entities.LoanSlip;
import com.example.quanlythuvien.entities.LoanSlipDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import repositories.LoanSlipDetailRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BbLoanSlipDetailRepository extends LoanSlipDetailRepository {
    Optional<LoanSlipDetail> findByBookDetailIdAndAndLoanSlipId(BookDetail bookDetail, LoanSlip loanSlip);

    Long countByLoanSlipIdAndStatusActionIn(LoanSlip loanSlip, Collection<Integer> status);
}
