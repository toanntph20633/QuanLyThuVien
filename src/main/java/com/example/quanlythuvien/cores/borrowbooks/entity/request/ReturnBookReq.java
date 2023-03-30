package com.example.quanlythuvien.cores.borrowbooks.entity.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ReturnBookReq {
    private List<UUID> bookDetails;
    private String maPhieu;

    private int trangThaiSach;
}
