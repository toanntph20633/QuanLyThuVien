package com.example.quanlythuvien.cores.auth.entity.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterReq {
    private String userName;
    private LocalDate userDate;

    private String userCode;

    private String citizen;

    private boolean sex;

    private String address;

    private String phoneNumber;

    private String accountName;

    private String password;
}
