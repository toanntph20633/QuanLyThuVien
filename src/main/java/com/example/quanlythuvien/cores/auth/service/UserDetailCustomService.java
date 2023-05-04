package com.example.quanlythuvien.cores.auth.service;

import com.example.quanlythuvien.cores.auth.entity.UserDetail;
import com.example.quanlythuvien.cores.auth.entity.request.LoginRequest;
import com.example.quanlythuvien.cores.auth.entity.request.RegisterReq;
import com.example.quanlythuvien.cores.auth.entity.respone.LoginRespone;
import com.example.quanlythuvien.cores.auth.repository.ScAccountRepository;
import com.example.quanlythuvien.cores.auth.repository.ScRoleAccountRepository;
import com.example.quanlythuvien.cores.auth.repository.ScRoleRepository;
import com.example.quanlythuvien.cores.auth.repository.ScUserRepository;
import com.example.quanlythuvien.entities.Accounts;
import com.example.quanlythuvien.entities.Roles;
import com.example.quanlythuvien.entities.RolesAccounts;
import com.example.quanlythuvien.entities.Users;
import com.example.quanlythuvien.exceptions.exception.BadRequestException;
import com.example.quanlythuvien.exceptions.exception.NotFoundException;
import com.example.quanlythuvien.utilities.enumclass.StatusAction;
import com.example.quanlythuvien.utilities.enumclass.StatusLive;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserDetailCustomService implements UserDetailsService {
    @Autowired
    private ScAccountRepository accountRepository;
    @Autowired
    private ScUserRepository scUserRepository;

    @Autowired
    private ScRoleRepository scRoleRepository;

    @Autowired
    private ScRoleAccountRepository scRoleAccountRepository;
    @Autowired
    private JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Accounts accounts = accountRepository.findByAccountName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy account " + username));
        List<SimpleGrantedAuthority> authorities = accounts.getRolesAccounts()
                .stream().map(o -> new SimpleGrantedAuthority(o.getRolesId().getRoleName())).collect(Collectors.toList());
        return new User(accounts.getUsername(), accounts.getPassword(), authorities);
    }


    public UserDetails loadUserById(UUID id) {
        Accounts accounts = accountRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy account "));
        return new UserDetail(accounts);
    }

    @Transactional
    public Accounts saveUser(RegisterReq req) {
        Users users = Users.builder()
                .userCode(req.getUserCode())
                .address(req.getAddress())
                .userDate(req.getUserDate())
                .citizenIdentification(req.getCitizen())
                .sex(req.isSex())
                .userName(req.getUserName())
                .phoneNumber(req.getPhoneNumber())
                .statusAction(StatusAction.ACTIVE.getValue())
                .statusLive(StatusLive.CREATED.getValue())
                .build();
        if (scUserRepository.findByUserCodeAndStatusLiveIn(req.getUserCode(), List.of(StatusLive.CREATED.getValue(), StatusLive.UPDATED.getValue())).isPresent()) {
            throw new BadRequestException("Đã tồn tại user");
        }
        if (accountRepository.findByAccountName(req.getAccountName()).isPresent()) {
            throw new BadRequestException("Đã tồn tại account");
        }
        scUserRepository.save(users);
        Accounts accounts = Accounts.builder()
                .accountName(req.getAccountName())
                .acountPass(new BCryptPasswordEncoder().encode(req.getPassword()))
                .customerId(users)
                .statusAction(StatusAction.ACTIVE.getValue())
                .statusLive(StatusLive.CREATED.getValue()).build();

        accountRepository.save(accounts);

        Roles roles = scRoleRepository.findByRoleName("user").orElseThrow(() -> new NotFoundException("Không tìm thấy role"));
        RolesAccounts rolesAccounts = RolesAccounts.builder()
                .rolesId(roles)
                .accountsId(accounts)
                .statusAction(StatusAction.ACTIVE.getValue())
                .statusLive(StatusLive.CREATED.getValue()).build();
        scRoleAccountRepository.save(rolesAccounts);
        return accounts;
    }
}
