package com.example.quanlythuvien.cores.bookmanager.entity.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BmBookUpdateReq {
    private UUID id;
    private String bookName;
    private String bookDescription;
    private UUID idBookCategory;
}
