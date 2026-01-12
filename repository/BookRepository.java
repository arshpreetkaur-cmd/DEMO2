// language: java
package com.code.bookinventoryapp.repository;

import com.code.bookinventoryapp.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BookRepository {
    private final Map<Long, Book> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(idGenerator.getAndIncrement());
        }
        store.put(book.getId(), book);
        return book;
    }

    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Book> findAll() {
        return new ArrayList<>(store.values());
    }

    public List<Book> findByAuthor(String authorQuery) {
        if (authorQuery == null) return List.of();
        String q = authorQuery.trim().toLowerCase();
        if (q.isEmpty()) return List.of();
        return store.values().stream()
                .filter(b -> b.getAuthor() != null && b.getAuthor().toLowerCase().contains(q))
                .collect(Collectors.toList());
    }
}
