package com.bridgelabz.BookStoreBackend.services;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bridgelabz.BookStoreBackend.dto.BookDTO;
import com.bridgelabz.BookStoreBackend.model.Book;
import com.bridgelabz.BookStoreBackend.model.User;
import com.bridgelabz.BookStoreBackend.repo.BookRepository;
import com.bridgelabz.BookStoreBackend.util.Token;

@Service
public class BookService implements IBookService {

    @Autowired
    BookRepository bookRepo;

    @Autowired
    Token myToken;

    @Autowired
    UserService userService;

    @Override
    public List<Book> getBooks() {
        return bookRepo.findAll();
    }

    @Override
    public Book getBookById(Long book_id) {
        return bookRepo.findById(book_id).orElse(null);
    }

    @Override
    public List<Book> getBookByName(String name) {
        List<Book> books;
        try {
            books = this.getBooks().stream().filter(i -> i.getName().contains(name)
                    || i.getAuthor().contains(name)
                    || i.getPrice().equals(Float.parseFloat(name)) ).toList();

        }catch (Exception e) {
            books = this.getBooks().stream().filter(i -> i.getName().contains(name)
                    || i.getAuthor().contains(name)).toList();
        }
        return books;
    }

    @Override
    public Book addBook(BookDTO bookDTO) {
        Book book = new Book(bookDTO);
        return bookRepo.save(book);
    }

    @Override
    public Book updateBook(Long book_id, BookDTO bookDTO) {
        Book book = this.getBookById(book_id);
        book.updateBook(bookDTO);
        return bookRepo.save(book);
    }

    @Override
    public void deleteBook(Long book_id) {
        Book book = this.getBookById(book_id);
        bookRepo.delete(book);
    }

    @Override
    public void changeBookQuantity(String token, Long book_id, Long quantity) {
        Long id = myToken.decodeToken(token);
        Optional<User> user = userService.getUserById(id);
        if(user.isPresent()) {
            Book book = this.getBookById(book_id);
            book.setQuantity(book.getQuantity()+ quantity);
            bookRepo.save(book);
        }
    }

    @Override
    public void changeBookPrice(String token, Long book_id, Float price) {
        Long id = myToken.decodeToken(token);
        Optional<User> user = userService.getUserById(id);
        if(user.isPresent()) {
            Book book = this.getBookById(book_id);
            book.setPrice(price);
            bookRepo.save(book);
        }
    }

    @Override
    public Long getBookPriceById(Long book_id) {
        return null;
    }

    @Override
    public List<Book> sortByPriceHighToLOw(String name) {
        ;
        // TODO Auto-generated method stub
        return bookRepo.findAll(Sort.by(name).descending());
    }

    @Override
    public List<Book> sortByPriceLowToHigh(String name) {
        // TODO Auto-generated method stub
        return bookRepo.findAll(Sort.by(name).ascending());
    }

    @Override
    public List<Book> sortById() {
        return bookRepo.sortByIDDesc();
    }
}
