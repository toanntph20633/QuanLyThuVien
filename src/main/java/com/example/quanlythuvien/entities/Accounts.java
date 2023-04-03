package com.example.quanlythuvien.entities;


import com.example.quanlythuvien.entities.base.CommonEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Accounts extends CommonEntity implements UserDetails {
    @Column(name = "account_name")
    private String accountName;

    @Column(name = "acount_pass")
    private String acountPass;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Users customerId;

    @OneToMany(mappedBy = "accountsId", fetch = FetchType.EAGER)
    private Set<RolesAccounts> rolesAccounts;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.rolesAccounts.stream()
                .map(r -> new SimpleGrantedAuthority(r.getRolesId().getRoleName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.acountPass;
    }

    @Override
    public String getUsername() {
        return this.accountName;
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
