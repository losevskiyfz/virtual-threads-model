package com.losevskiyfz.servletstackvirtualthreads.service;

import com.losevskiyfz.servletstackvirtualthreads.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookService {
    Book save(Book book);

    void delete(String bookId);

    Page<Book> getPaginated(Pageable pageable);

    Optional<Book> findById(String id);
}
