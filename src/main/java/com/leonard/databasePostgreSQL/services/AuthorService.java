package com.leonard.databasePostgreSQL.services;

import com.leonard.databasePostgreSQL.domain.entities.AuthorEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorService {
    AuthorEntity createAuthor(AuthorEntity authorEntity);

    List<AuthorEntity> findAll();
}
