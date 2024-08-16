package com.colak.springtutorial.repository;

import com.colak.springtutorial.jpa.Author;
import com.colak.springtutorial.jpa.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository repository;

    @Test
    @Transactional
    void findByIdWithBooks() {
        Author author = repository.findByIdWithBooks2(1L);
        for (Book book : author.getBooks()) {
            log.info("Book title : {}", book.getTitle());
        }
    }
}
