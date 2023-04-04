package com.example.quanlythuvien.security.controller;

import com.example.quanlythuvien.security.entity.request.LoginRequest;
import com.example.quanlythuvien.security.entity.request.RegisterReq;
import com.example.quanlythuvien.security.entity.respone.LoginRespone;
import com.example.quanlythuvien.security.service.UserDetailCustomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    private UserDetailCustomService userDetailCustomService;

    @PostMapping("/login")
    public ResponseEntity<LoginRespone> login(@Validated @RequestBody LoginRequest request) {
        return ResponseEntity.ok(userDetailCustomService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Validated @RequestBody RegisterReq request) {
        userDetailCustomService.saveUser(request);
        return ResponseEntity.ok("Create done!");
    }





}
