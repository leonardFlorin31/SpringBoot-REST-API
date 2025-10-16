package com.leonard.databasePostgreSQL.controllers;

import com.leonard.databasePostgreSQL.domain.dto.BookDto;
import com.leonard.databasePostgreSQL.domain.entities.BookEntity;
import com.leonard.databasePostgreSQL.mappers.Mapper;
import com.leonard.databasePostgreSQL.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookController {

    BookService bookService;
    private Mapper<BookEntity, BookDto> bookMapper;

    public BookController(Mapper<BookEntity, BookDto> bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> createBook(
            @PathVariable("isbn") String isbn,
            @RequestBody BookDto bookDto)
    {
    BookEntity bookEntity = bookMapper.mapFrom(bookDto);
    BookEntity savedBookEntity = bookService.createBook(isbn, bookEntity);
    return new ResponseEntity<>(bookMapper.mapTo(savedBookEntity),HttpStatus.CREATED);
    }

    @GetMapping(path = "/books")
    public List<BookDto> getAllBooks() {
        List<BookEntity> books = bookService.findAll();
        return books.stream()
                .map(bookMapper::mapTo)
                .toList();
    }

    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> getBookByIsbn(@PathVariable("isbn") String isbn) {
        Optional<BookEntity> foundBook = bookService.findOne(isbn);
        return foundBook.map(bookEntity ->
                        new ResponseEntity<>(bookMapper.mapTo(bookEntity), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
