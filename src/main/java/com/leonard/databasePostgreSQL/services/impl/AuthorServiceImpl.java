package com.leonard.databasePostgreSQL.services.impl;

import com.leonard.databasePostgreSQL.domain.entities.AuthorEntity;
import com.leonard.databasePostgreSQL.repositories.AuthorRepository;
import com.leonard.databasePostgreSQL.services.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {


    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity authorEntity) {
       return authorRepository.save(authorEntity);
    }
}
