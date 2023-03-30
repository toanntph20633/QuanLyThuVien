package com.example.quanlythuvien.entities;

import com.example.quanlythuvien.entities.base.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Nationalized;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Users extends CommonEntity {


    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_date")
    private LocalDate userDate;

    @Column(name = "user_code")
    private String userCode;

    @Column(name = "citizen_identification")
    private String citizenIdentification;

    @Column(name = "sex")
    private Boolean sex;

    @Column(name = "address")
    @Nationalized
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;


}
