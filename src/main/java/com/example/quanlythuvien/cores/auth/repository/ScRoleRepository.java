package com.example.quanlythuvien.cores.auth.repository;

import com.example.quanlythuvien.entities.Roles;
import org.springframework.stereotype.Repository;
import repositories.RoleRepository;

import java.util.Optional;
@Repository
public interface ScRoleRepository extends RoleRepository {
    Optional<Roles> findByRoleName(String name);
}
