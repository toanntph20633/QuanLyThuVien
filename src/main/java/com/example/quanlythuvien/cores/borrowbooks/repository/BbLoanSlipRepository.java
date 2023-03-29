package com.example.quanlythuvien.cores.borrowbooks.repository;

import com.example.quanlythuvien.entities.LoanSlip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import repositories.LoanSlipRepository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface BbLoanSlipRepository extends LoanSlipRepository {
    Optional<LoanSlip> findByLoanSlipCodeAndStatusActionInAndStatusLiveIn(String code, Collection<Integer> action, Collection<Integer> live);
}
