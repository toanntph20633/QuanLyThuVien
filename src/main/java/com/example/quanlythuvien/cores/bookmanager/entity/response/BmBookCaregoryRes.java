package com.example.quanlythuvien.cores.bookmanager.entity.response;

import com.example.quanlythuvien.entities.base.CommonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class BmBookCaregoryRes extends CommonDTO {
    private String bookCategoryCode;
    private String bookCategoryName;
    private String bookCategoryDescription;
}
