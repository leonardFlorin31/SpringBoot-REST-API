package com.leonard.databasePostgreSQL.services;

import com.leonard.databasePostgreSQL.domain.entities.AuthorEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorService {
    AuthorEntity createAuthor(AuthorEntity authorEntity);
}
