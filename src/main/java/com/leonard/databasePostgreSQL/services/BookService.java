package com.leonard.databasePostgreSQL.services;

import com.leonard.databasePostgreSQL.domain.entities.BookEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface BookService {
    BookEntity createBook(String isbn, BookEntity bookEntity);

}
