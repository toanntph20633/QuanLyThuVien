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

import java.util.Set;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Accounts extends CommonEntity {
    @Column(name = "account_name")
    private String accountName;

    @Column(name = "acount_pass")
    private String acountPass;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Users customerId;

    @OneToMany(mappedBy = "accountsId", fetch = FetchType.LAZY)
    private Set<RolesAccounts> rolesAccounts;


}
