package com.leonard.databasePostgreSQL.repositories;

import com.leonard.databasePostgreSQL.domain.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;


public interface BookRepository extends CrudRepository<BookEntity, String> {
}
