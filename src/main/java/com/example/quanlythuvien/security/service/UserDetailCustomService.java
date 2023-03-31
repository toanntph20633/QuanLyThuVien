package com.example.quanlythuvien.security.service;

import com.example.quanlythuvien.entities.Accounts;
import com.example.quanlythuvien.entities.RolesAccounts;
import com.example.quanlythuvien.security.entity.UserDetail;
import com.example.quanlythuvien.security.repository.ScAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserDetailCustomService implements UserDetailsService {
    @Autowired
    private ScAccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Accounts accounts = accountRepository.findByAccountName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy account " + username));
        return new UserDetail(accounts);
    }


    public UserDetails loadUserById(UUID id) {
        Accounts accounts = accountRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy account "));
        return new UserDetail(accounts);
    }
}
