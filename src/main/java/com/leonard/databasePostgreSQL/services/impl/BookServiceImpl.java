package com.leonard.databasePostgreSQL.services.impl;

import com.leonard.databasePostgreSQL.domain.entities.BookEntity;
import com.leonard.databasePostgreSQL.repositories.BookRepository;
import com.leonard.databasePostgreSQL.services.BookService;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity createBook(String isbn, BookEntity bookEntity) {
        bookEntity.setIsbn(isbn);
        return bookRepository.save(bookEntity);
    }

    @Override
    public List<BookEntity> findAll() {
        return Streamable.of(bookRepository.findAll()).toList();
    }

    @Override
    public Optional<BookEntity> findOne(String isbn) {
        return bookRepository.findById(isbn);
    }
}
