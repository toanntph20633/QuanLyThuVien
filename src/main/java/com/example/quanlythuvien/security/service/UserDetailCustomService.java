package com.example.quanlythuvien.security.service;

import com.example.quanlythuvien.entities.Accounts;
import com.example.quanlythuvien.entities.Roles;
import com.example.quanlythuvien.entities.RolesAccounts;
import com.example.quanlythuvien.entities.Users;
import com.example.quanlythuvien.exceptions.exception.BadRequestException;
import com.example.quanlythuvien.exceptions.exception.NotFoundException;
import com.example.quanlythuvien.security.entity.UserDetail;
import com.example.quanlythuvien.security.entity.request.LoginRequest;
import com.example.quanlythuvien.security.entity.request.RegisterReq;
import com.example.quanlythuvien.security.entity.respone.LoginRespone;
import com.example.quanlythuvien.security.repository.ScAccountRepository;
import com.example.quanlythuvien.security.repository.ScRoleAccountRepository;
import com.example.quanlythuvien.security.repository.ScRoleRepository;
import com.example.quanlythuvien.security.repository.ScUserRepository;
import com.example.quanlythuvien.utilities.enumclass.StatusAction;
import com.example.quanlythuvien.utilities.enumclass.StatusLive;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserDetailCustomService {
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
    @Autowired
    private AuthenticationManager authenticationManager;

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

    @Transactional
    public void saveUser(RegisterReq req) {
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
    }

    public LoginRespone login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getAccount(), request.getPassword()
                )
        );
        Accounts accounts = accountRepository.findByAccountName(request.getAccount()).orElseThrow();
        var jwtToken = jwtService.generateToken(accounts);
        return LoginRespone.builder().token(jwtToken).build();
    }
}
