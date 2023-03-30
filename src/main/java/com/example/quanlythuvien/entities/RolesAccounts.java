package com.example.quanlythuvien.entities;

import com.example.quanlythuvien.entities.base.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "roles_accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RolesAccounts extends CommonEntity {
    @ManyToOne
    @JoinColumn(name = "roles_id")
    private Roles rolesId;
    @ManyToOne
    @JoinColumn(name = "accounts_id")
    private Accounts accountsId;


}
