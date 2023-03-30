package com.example.quanlythuvien.security.config;

import com.example.quanlythuvien.entities.Accounts;
import com.example.quanlythuvien.entities.RolesAccounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailCustomService implements UserDetailsService {
    @Autowired
    private ScAccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Accounts accounts = accountRepository.findByAccountName(username).orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy account " + username));
        List<RolesAccounts> rolesAccounts = accounts.getRolesAccounts().stream().toList();
        List<GrantedAuthority> authorities = getAuthorities(rolesAccounts);
        return buildAccountForAuthentication(accounts, authorities);
    }

    private List<GrantedAuthority> getAuthorities(List<RolesAccounts> rolesAccounts) {
        return rolesAccounts.stream().map(r -> new SimpleGrantedAuthority
                (r.getRolesId().getRoleName())).collect(Collectors.toList());

    }

    private UserDetails buildAccountForAuthentication(Accounts accounts, List<GrantedAuthority> list) {
        return new org.springframework.security.core.userdetails.User(accounts.getAccountName(), accounts.getAcountPass(), list);
    }
}
