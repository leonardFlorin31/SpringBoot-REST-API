package com.leonard.databasePostgreSQL.services.impl;

import com.leonard.databasePostgreSQL.domain.entities.BookEntity;
import com.leonard.databasePostgreSQL.repositories.BookRepository;
import com.leonard.databasePostgreSQL.services.BookService;
import org.springframework.stereotype.Service;

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
}
