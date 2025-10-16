package com.leonard.databasePostgreSQL.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leonard.databasePostgreSQL.TestDataUtil;
import com.leonard.databasePostgreSQL.domain.dto.AuthorDto;
import com.leonard.databasePostgreSQL.domain.entities.AuthorEntity;
import com.leonard.databasePostgreSQL.services.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTests {

    private MockMvc mockMvc;

    private AuthorService authorService;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public AuthorControllerIntegrationTests(MockMvc mockMvc, AuthorService authorService) {
        this.mockMvc = mockMvc;
        this.authorService = authorService;
    }

    @Test
    public void testThatAuthorCanBeCreated() throws Exception {

        String authorJson = TestDataUtil.getAuthorJson();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }



    @Test
    public void testThatAuthorSuccessfullyReturnsSavedAuthor() throws Exception {

        String authorJson = TestDataUtil.getAuthorJson();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Leonard")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(25)
        );
    }

    @Test
    public void testThatGetAuthorsSuccessfullyReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetAuthorsSuccessfullyReturnsListOfAuthors() throws Exception {
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorA();
        authorService.saveAuthor(testAuthorEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("Leonard")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].age").value(25)
        );
    }

    @Test
    public void testThatGetAuthorByIdSuccessfullyReturnsHttpStatus200() throws Exception {
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorA();
        authorService.saveAuthor(testAuthorEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetAuthorByIdSuccessfullyReturnsHttpStatus404() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatGetAuthorByIdSuccessfullyReturnsCorrectBody() throws Exception {
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorA();
        authorService.saveAuthor(testAuthorEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Leonard")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(25)
        );
    }

    @Test
    public void testThatUpdateAuthorByIdSuccessfullyReturnsHttpStatus404WhenNoAuthorExists() throws Exception {
       AuthorDto testAuthorDtoA = TestDataUtil.createTestAuthorDtoA();
       String authorDtoJson = objectMapper.writeValueAsString(testAuthorDtoA);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatUpdateAuthorByIdSuccessfullyReturnsHttpStatus200WhenAuthorExists() throws Exception {

        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorA();
        AuthorEntity savedAuthor = authorService.saveAuthor(testAuthorEntityA);

        AuthorDto testAuthorDtoA = TestDataUtil.createTestAuthorDtoA();
        String authorDtoJson = objectMapper.writeValueAsString(testAuthorDtoA);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/" + savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatFullUpdateUpdatesExistingAuthor() throws Exception {
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorA();
        AuthorEntity savedAuthor = authorService.saveAuthor(testAuthorEntityA);

        AuthorEntity authorDto = TestDataUtil.createTestAuthorB();
        authorDto.setId(savedAuthor.getId());
        String authorUpdateBodyJson = objectMapper.writeValueAsString(authorDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/" + savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorUpdateBodyJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(authorDto.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(authorDto.getAge())
        );
    }

}
