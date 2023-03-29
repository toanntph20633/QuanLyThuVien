package com.example.quanlythuvien.cores.bookmanager.entity.response;

import com.example.quanlythuvien.entities.BookCategory;
import com.example.quanlythuvien.entities.base.CommonDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BmBookRes extends CommonDTO {
    private String bookCode;
    private String bookName;
    private String bookDescription;
    private String quantity;
    private Set<BmBookDetailRes> bmBookDetailRes;
    private BmBookCaregoryRes bookCategoryId;

    private int sumBookBorrow;

    private int sumLostBook;

    private int sumBrokenBook;
    private int sumActiveBook;

}
