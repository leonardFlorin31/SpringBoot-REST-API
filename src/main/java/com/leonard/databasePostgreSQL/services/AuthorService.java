package com.leonard.databasePostgreSQL.services;

import com.leonard.databasePostgreSQL.domain.dto.AuthorDto;
import com.leonard.databasePostgreSQL.domain.entities.AuthorEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorService {
    AuthorEntity saveAuthor(AuthorEntity authorEntity);

    List<AuthorEntity> findAll();

    Optional<AuthorEntity> findOne(Long id);

    boolean isExists(Long id);

    AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity);

}
