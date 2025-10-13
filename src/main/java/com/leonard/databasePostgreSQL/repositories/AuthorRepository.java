package com.leonard.databasePostgreSQL.repositories;

import com.leonard.databasePostgreSQL.domain.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Iterable<Author> ageLessThan(int age);

    @Query(value = "SELECT * FROM authors WHERE age > ?1", nativeQuery = true)
    Iterable<Author> findAuthorsWithAgeGreaterThan(int age);
}
