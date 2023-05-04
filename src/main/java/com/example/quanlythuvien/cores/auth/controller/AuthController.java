package com.example.quanlythuvien.cores.auth.controller;

import com.example.quanlythuvien.cores.auth.entity.UserDetail;
import com.example.quanlythuvien.cores.auth.entity.request.LoginRequest;
import com.example.quanlythuvien.cores.auth.entity.request.RegisterReq;
import com.example.quanlythuvien.cores.auth.entity.respone.LoginRespone;
import com.example.quanlythuvien.cores.auth.security.JwtProvider;
import com.example.quanlythuvien.cores.auth.service.UserDetailCustomService;
import com.example.quanlythuvien.entities.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app-auth")
public class AuthController {
    @Autowired
    private UserDetailCustomService userDetailsService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/token")
    public String getToken(@RequestBody LoginRequest request) throws Exception {
        // Get user details
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getAccount());

        if (passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            // Generate token
            return jwtProvider.generateToken(request.getAccount());
        }

        throw new Exception("User details invalid.");
    }

    @PostMapping("/createUser")
    public String createUser(@RequestBody RegisterReq req) throws Exception {
        Accounts accounts = userDetailsService.saveUser(req);
        return getToken(new LoginRequest(accounts.getAccountName(), accounts.getPassword()));
    }
}
