package com.example.quanlythuvien;

import com.example.quanlythuvien.cores.borrowbooks.repository.BbLoanSlipDetailRepository;
import com.example.quanlythuvien.entities.LoanSlip;
import com.example.quanlythuvien.utilities.enumclass.StatusAction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
class QuanLyThuVienApplicationTests {
    @Autowired
    private BbLoanSlipDetailRepository repository;

    @Test
    void contextLoads() {
        System.out.println(repository.countByLoanSlipIdAndStatusActionIn(LoanSlip.builder()
                        .id(UUID.fromString("7e29de12-a777-4b3c-9268-c7b529cd9726")).build()
                , List.of(StatusAction.LOANSLIPDETAIL_DANGMUON.getValue())));
    }

}
