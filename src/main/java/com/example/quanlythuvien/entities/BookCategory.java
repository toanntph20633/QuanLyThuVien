package com.example.quanlythuvien.entities;

import com.example.quanlythuvien.entities.base.CommonEntity;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "book_category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class BookCategory extends CommonEntity {

    @Column(name = "book_category_code", nullable = false, unique = true)
    private String bookCategoryCode;

    @Column(name = "book_category_name")
    private String bookCategoryName;
    @Column(name = "book_category_description")
    private String bookCategoryDescription;

    @OneToMany(mappedBy = "bookCategoryId", fetch = FetchType.LAZY)
    private Set<Book> books;
}
