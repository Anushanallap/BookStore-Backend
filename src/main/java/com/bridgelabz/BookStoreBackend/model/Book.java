package com.bridgelabz.BookStoreBackend.model;

import com.bridgelabz.BookStoreBackend.dto.BookDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "book_store")
public @Data class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) Long book_id;

    private String name;

    private String author;

    private String description;

    private String logo;

    private Float price;

    private Long quantity;

    public Book() {
    }

    public Book(BookDTO bookDTO) {
        this.updateBook(bookDTO);

    }

    public void updateBook(BookDTO bookDTO) {
        this.name = bookDTO.name;
        this.author = bookDTO.author;
        this.description = bookDTO.description;
        this.logo = bookDTO.logo;
        this.price = bookDTO.price;
        this.quantity = bookDTO.quantity;

    }


}
