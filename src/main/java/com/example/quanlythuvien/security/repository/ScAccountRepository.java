package com.example.quanlythuvien.security.repository;

import com.example.quanlythuvien.entities.Accounts;
import org.springframework.stereotype.Repository;
import repositories.AccountRepository;

import java.util.Optional;

@Repository
public interface ScAccountRepository extends AccountRepository {
    Optional<Accounts> findByAccountName(String name);
}
