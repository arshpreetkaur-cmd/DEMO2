// language: java
package com.code.bookinventoryapp.service;

import com.code.bookinventoryapp.model.Book;
import com.code.bookinventoryapp.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public Book addBook(Book book) {
        return repo.save(book);
    }

    public Optional<Book> findById(Long id) {
        return repo.findById(id);
    }

    public List<Book> findByAuthor(String author) {
        return repo.findByAuthor(author);
    }
}
