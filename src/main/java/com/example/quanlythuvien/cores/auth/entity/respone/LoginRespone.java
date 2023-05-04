package com.example.quanlythuvien.cores.auth.entity.respone;

import com.example.quanlythuvien.entities.Accounts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRespone {
    private String token;

    private UserDetails userDetails;
}
