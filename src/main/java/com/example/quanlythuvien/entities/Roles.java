package com.example.quanlythuvien.entities;

import com.example.quanlythuvien.entities.base.CommonEntity;

import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Roles extends CommonEntity {

    @Column(name = "role_name")
    private String roleName;
    @OneToMany(mappedBy = "rolesId", fetch = FetchType.LAZY)
    private Set<RolesAccounts> rolesAccounts;

}
