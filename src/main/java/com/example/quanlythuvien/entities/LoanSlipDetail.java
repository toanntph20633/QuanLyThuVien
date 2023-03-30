package com.example.quanlythuvien.entities;

import com.example.quanlythuvien.entities.base.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


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
