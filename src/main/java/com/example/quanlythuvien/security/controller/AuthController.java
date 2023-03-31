package com.example.quanlythuvien.security.controller;

import com.example.quanlythuvien.security.config.JwtTokenProvider;
import com.example.quanlythuvien.security.entity.UserDetail;
import com.example.quanlythuvien.security.entity.request.LoginRequest;
import com.example.quanlythuvien.security.entity.respone.LoginRespone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<LoginRespone> Login(@Validated @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getAccount(), request.getPassword()));
        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((UserDetail) authentication.getPrincipal());
        return ResponseEntity.ok(new LoginRespone(jwt));
    }


    @GetMapping("/random")
    public String randomStuff() {
        return "hhihhii";
    }


}
