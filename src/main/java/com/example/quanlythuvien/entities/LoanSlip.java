package com.example.quanlythuvien.entities;

import com.example.quanlythuvien.entities.base.CommonEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "loan_slip")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class LoanSlip extends CommonEntity {

    @Column(name = "loan_slip_code")
    private String loanSlipCode;
    @ManyToOne
    @JoinColumn(name = "sinh_vien_id")
    private Student sinhVienId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users userId;

    @Column(name = "last_date")
    private LocalDate lastDate;


}
