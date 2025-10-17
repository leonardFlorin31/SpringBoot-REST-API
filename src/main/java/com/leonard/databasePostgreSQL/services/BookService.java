package com.leonard.databasePostgreSQL.services;

import com.leonard.databasePostgreSQL.domain.entities.BookEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookService {
    BookEntity createUpdateBook(String isbn, BookEntity bookEntity);

    List<BookEntity> findAll();

    Optional<BookEntity> findOne(String isbn);

    boolean isExist(String isbn);
}
