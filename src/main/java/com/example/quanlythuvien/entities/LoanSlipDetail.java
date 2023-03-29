package com.example.quanlythuvien.entities;

import com.example.quanlythuvien.entities.base.CommonEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Entity
@Table(name = "loan_slip_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class LoanSlipDetail extends CommonEntity {
    @ManyToOne
    @JoinColumn(name = "loan_slip_id")
    private LoanSlip loanSlipId;

    @ManyToOne
    @JoinColumn(name = "book_detail_id")
    private BookDetail bookDetailId;

}
