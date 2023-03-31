package com.example.quanlythuvien.cores.borrowbooks.controller;

import com.example.quanlythuvien.cores.borrowbooks.entity.request.BrrowBookReq;
import com.example.quanlythuvien.cores.borrowbooks.entity.request.ReturnBookReq;
import com.example.quanlythuvien.cores.borrowbooks.service.BmBookBrrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("brrow-books/api/user/v1")
public class BbLoanSlipController {
    @Autowired
    private BmBookBrrowService bmBookBrrowService;

    @PostMapping("/create-loan-slip")
    public ResponseEntity<String> createLoanSlip(@RequestBody BrrowBookReq req) {
        bmBookBrrowService.brrowBook(req);
        return ResponseEntity.ok("Crate dome!");
    }

    @PostMapping("/update-loan-slip")
    public ResponseEntity<String> updateLoanSlip(@RequestBody ReturnBookReq req) {
        bmBookBrrowService.returnBook(req);
        return ResponseEntity.ok("Update dome!");
    }
}
