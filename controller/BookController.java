// language: java
package com.code.bookinventoryapp.controller;

import com.code.bookinventoryapp.model.Book;
import com.code.bookinventoryapp.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService svc;

    public BookController(BookService svc) {
        this.svc = svc;
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        Book saved = svc.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable("id") Long id) {
        return svc.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchByAuthor(@RequestParam(name = "author", required = false) String author) {
        if (author == null || author.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(List.of());
        }
        List<Book> matches = svc.findByAuthor(author);
        return ResponseEntity.ok(matches);
    }
}
