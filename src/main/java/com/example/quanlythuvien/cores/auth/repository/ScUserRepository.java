package com.example.quanlythuvien.cores.auth.repository;

import com.example.quanlythuvien.entities.Users;
import org.springframework.stereotype.Repository;
import repositories.UserRepository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ScUserRepository extends UserRepository {
    Optional<Users> findByUserCodeAndStatusLiveIn(String code, Collection<Integer> live);
}
