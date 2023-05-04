package com.example.quanlythuvien;

import com.example.quanlythuvien.cores.auth.repository.ScAccountRepository;
import com.example.quanlythuvien.cores.borrowbooks.repository.BbLoanSlipDetailRepository;
import com.example.quanlythuvien.entities.Accounts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class QuanLyThuVienApplicationTests {
    @Autowired
    private BbLoanSlipDetailRepository repository;

    @Autowired
    private ScAccountRepository accountRepository;

    @Test
    void contextLoads() {
//        System.out.println(repository.countByLoanSlipIdAndStatusActionIn(LoanSlip.builder()
//                        .id(UUID.fromString("7e29de12-a777-4b3c-9268-c7b529cd9726")).build()
//                , List.of(StatusAction.LOANSLIPDETAIL_DANGMUON.getValue())));


        Accounts accounts = accountRepository.findByAccountName("ly123").get();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        accounts.setAcountPass(passwordEncoder.encode(accounts.getAcountPass()));
        accountRepository.save(accounts);
    }

}
