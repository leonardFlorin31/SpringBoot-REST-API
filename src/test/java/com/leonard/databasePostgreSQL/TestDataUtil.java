package com.leonard.databasePostgreSQL;

import com.leonard.databasePostgreSQL.domain.Author;
import com.leonard.databasePostgreSQL.domain.Book;

public final class TestDataUtil {
    private TestDataUtil() {
    }

    public static Author createTestAuthorA() {
        return Author.builder()
                .name("Leonard")
                .age(25)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .name("Leonard3")
                .age(5)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .name("Leonard4")
                .age(225)
                .build();
    }

    public static Book createTestBookA(final Author author) {
        return Book.builder()
                .isbn("1234567891")
                .title("The Hobbit1")
                .author( author)
                .build();
    }

    public static Book createTestBookB(final Author author) {
        return Book.builder()
                .isbn("1234567892")
                .title("The Hobbit2")
                .author(author)
                .build();
    }

    public static Book createTestBookC(final Author author) {
        return Book.builder()
                .isbn("1234567893")
                .title("The Hobbit3")
                .author(author)
                .build();
    }
}
