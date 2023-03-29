package com.example.quanlythuvien.cores.bookmanager.entity.response;

import com.example.quanlythuvien.entities.base.CommonDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BmListBookRes extends CommonDTO {
    private String bookCode;
    private String bookName;
    private String bookCategoryName;

    private int quantity;

}
