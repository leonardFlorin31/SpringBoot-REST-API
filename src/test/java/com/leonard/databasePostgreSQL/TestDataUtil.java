package com.leonard.databasePostgreSQL;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leonard.databasePostgreSQL.domain.dto.AuthorDto;
import com.leonard.databasePostgreSQL.domain.dto.BookDto;
import com.leonard.databasePostgreSQL.domain.entities.AuthorEntity;
import com.leonard.databasePostgreSQL.domain.entities.BookEntity;

public final class TestDataUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private TestDataUtil() {

    }

    public static AuthorDto createTestAuthorDtoA() {
        return AuthorDto.builder()
                .name("Leonard")
                .age(25)
                .build();
    }

    public static AuthorEntity createTestAuthorA() {
        return AuthorEntity.builder()
                .name("Leonard")
                .age(25)
                .build();
    }

    public static AuthorEntity createTestAuthorB() {
        return AuthorEntity.builder()
                .name("Leonard3")
                .age(5)
                .build();
    }

    public static AuthorEntity createTestAuthorC() {
        return AuthorEntity.builder()
                .name("Leonard4")
                .age(225)
                .build();
    }

    public static BookDto createTestBookDtoA(final AuthorDto authorDto) {
        return BookDto.builder()
                .isbn("1234567891")
                .title("The Hobbit1")
                .author(authorDto)
                .build();
    }

    public static BookEntity createTestBookA(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("1234567891")
                .title("The Hobbit1")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookEntity createTestBookB(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("1234567892")
                .title("The Hobbit2")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookEntity createTestBookC(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("1234567893")
                .title("The Hobbit3")
                .authorEntity(authorEntity)
                .build();
    }

    public static String getAuthorJson() throws JsonProcessingException {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        testAuthorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthorA);
        return authorJson;
    }
}
