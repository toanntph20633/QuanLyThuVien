package com.example.quanlythuvien.cores.borrowbooks.entity.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrrowBookReq {
    private UUID StudentId;
    private String loanSlipCode;
    private List<UUID> bookDetailIds;
    private LocalDate lastDate;
}
