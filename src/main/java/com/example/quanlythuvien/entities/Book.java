package com.example.quanlythuvien.entities;


import com.example.quanlythuvien.entities.base.CommonEntity;


import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
