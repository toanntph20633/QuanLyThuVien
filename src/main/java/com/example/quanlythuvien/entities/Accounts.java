package com.example.quanlythuvien.entities;


import com.example.quanlythuvien.entities.base.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
