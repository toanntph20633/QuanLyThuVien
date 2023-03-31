package com.example.quanlythuvien.security.entity;

import com.example.quanlythuvien.entities.Accounts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class UserDetail implements UserDetails {
    private Accounts accounts;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return accounts.getRolesAccounts().stream()
                .map(x -> new SimpleGrantedAuthority(x.getRolesId().getRoleName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return accounts.getAcountPass();
    }

    @Override
    public String getUsername() {
        return accounts.getAccountName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
