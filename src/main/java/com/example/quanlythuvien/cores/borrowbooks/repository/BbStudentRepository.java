package com.example.quanlythuvien.cores.borrowbooks.repository;

import com.example.quanlythuvien.entities.Student;
import org.springframework.stereotype.Repository;
import repositories.StudentRepository;

import java.util.Optional;

@Repository
public interface BbStudentRepository extends StudentRepository {
    Optional<Student> findByStudentCode(String code);
}
