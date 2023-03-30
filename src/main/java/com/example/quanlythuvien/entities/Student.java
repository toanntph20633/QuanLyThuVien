package com.example.quanlythuvien.entities;

import com.example.quanlythuvien.entities.base.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Student extends CommonEntity {

    @Column(name = "student_code")
    private String studentCode;

    @Column(name = "student_name")
    private String studentName;

}
