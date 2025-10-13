package com.leonard.databasePostgreSQL.repositories;

import com.leonard.databasePostgreSQL.domain.entities.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {
    Iterable<AuthorEntity> ageLessThan(int age);

    @Query(value = "SELECT * FROM authors WHERE age > ?1", nativeQuery = true)
    Iterable<AuthorEntity> findAuthorsWithAgeGreaterThan(int age);
}
