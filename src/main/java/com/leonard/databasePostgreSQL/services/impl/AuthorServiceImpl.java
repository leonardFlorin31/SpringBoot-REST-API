package com.leonard.databasePostgreSQL.services.impl;

import com.leonard.databasePostgreSQL.domain.dto.AuthorDto;
import com.leonard.databasePostgreSQL.domain.entities.AuthorEntity;
import com.leonard.databasePostgreSQL.repositories.AuthorRepository;
import com.leonard.databasePostgreSQL.services.AuthorService;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {


    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity saveAuthor(AuthorEntity authorEntity) {
       return authorRepository.save(authorEntity);
    }

    @Override
    public List<AuthorEntity> findAll() {
        return Streamable.of(authorRepository.findAll()).toList();
    }

    @Override
    public Optional<AuthorEntity> findOne(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return authorRepository.existsById(id);
    }

    @Override
    public AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity) {
        authorEntity.setId(id);

        return authorRepository.findById(id).map(existingAuthor ->{
            Optional.ofNullable(authorEntity.getName()).ifPresent(existingAuthor::setName);
            Optional.ofNullable(authorEntity.getAge()).ifPresent(existingAuthor::setAge);
            return authorRepository.save(existingAuthor);
        }).orElseThrow(() -> new RuntimeException("Author not found"));
    }
}
