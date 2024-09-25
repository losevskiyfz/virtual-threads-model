package com.losevskiyfz.servletstackvirtualthreads.controller;

import com.losevskiyfz.servletstackvirtualthreads.domain.Book;
import com.losevskiyfz.servletstackvirtualthreads.domain.PostBookDto;
import com.losevskiyfz.servletstackvirtualthreads.mapper.BookMapper;
import com.losevskiyfz.servletstackvirtualthreads.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @Autowired
    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @PostMapping
    ResponseEntity<Book> save(@Valid @RequestBody PostBookDto bookRecord) {
        Book bookToSave = bookMapper.toBook(bookRecord);
        Book savedBook = bookService.save(bookToSave);
        return ResponseEntity
                .created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(savedBook.id())
                                .toUri()
                )
                .body(savedBook);
    }

    @PutMapping("/{id}")
    ResponseEntity<Book> update(@PathVariable String id, @Valid @RequestBody PostBookDto bookRecord) {
        if(bookService.findById(id).isPresent()) {
            Book bookToSave = bookMapper.toBook(bookRecord).withId(id);
            bookService.save(bookToSave);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Page<Book>> get(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookService.getPaginated(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
