package com.example.quanlythuvien.entities;


import com.example.quanlythuvien.entities.base.CommonEntity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Book extends CommonEntity {
    @Column(name = "book_code")
    private String bookCode;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "book_description")
    private String bookDescription;


    @ManyToOne
    @JoinColumn(name = "book_category_id")
    private BookCategory bookCategoryId;


    @OneToMany(mappedBy = "bookId", fetch = FetchType.LAZY)
    private Set<BookDetail> bookDetails;


}
