package com.leonard.databasePostgreSQL.repositories;

import com.leonard.databasePostgreSQL.domain.Book;
import org.springframework.data.repository.CrudRepository;


public interface BookRepository extends CrudRepository<Book, String> {
}
