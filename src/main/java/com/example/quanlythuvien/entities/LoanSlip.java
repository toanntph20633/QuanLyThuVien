package com.example.quanlythuvien.entities;

import com.example.quanlythuvien.entities.base.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

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
    @OneToMany(mappedBy = "loanSlipId", fetch = FetchType.LAZY)
    private Set<LoanSlipDetail> loanSlipDetails;
}
