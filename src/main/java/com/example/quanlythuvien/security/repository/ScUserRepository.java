package com.example.quanlythuvien.security.repository;

import com.example.quanlythuvien.entities.Users;
import com.example.quanlythuvien.utilities.enumclass.StatusLive;
import org.springframework.stereotype.Repository;
import repositories.UserRepository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ScUserRepository extends UserRepository {
    Optional<Users> findByUserCodeAndStatusLiveIn(String code, Collection<Integer> live);
}
