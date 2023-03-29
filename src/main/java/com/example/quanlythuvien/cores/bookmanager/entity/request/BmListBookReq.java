package com.example.quanlythuvien.cores.bookmanager.entity.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BmListBookReq {
    private String keyword;
    private int limit;
    private int offset;
    private int statusAction;
}
