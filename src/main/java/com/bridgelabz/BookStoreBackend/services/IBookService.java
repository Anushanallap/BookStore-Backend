package com.bridgelabz.BookStoreBackend.services;


import com.bridgelabz.BookStoreBackend.dto.BookDTO;
import com.bridgelabz.BookStoreBackend.model.Book;
import com.bridgelabz.BookStoreBackend.util.Token;

import java.util.List;


public interface IBookService {

    List<Book> getBooks();

    List<Book> sortByPriceHighToLOw(String name);

    List<Book> sortByPriceLowToHigh(String name);

    List<Book> sortById();

    Book getBookById(Long book_id);

    List<Book> getBookByName(String name);

    Long getBookPriceById(Long book_id);

    Book addBook(BookDTO bookDTO);

    Book updateBook(Long book_id, BookDTO bookDTO);

    void changeBookQuantity(String token, Long book_id, Long quantity);

    void changeBookPrice(String token, Long book_id, Float price);

    void deleteBook(Long book_id);

}