package com.reactive.app.entities;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("book_details")
public class Book {

    @Id
    @Column("book_id")
    private int bookId;
    private String name;
    @Column("book_desc")
    private String description;
    private String publisher;
    private String author;
}
