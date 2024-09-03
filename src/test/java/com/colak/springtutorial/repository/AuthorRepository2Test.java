package com.colak.springtutorial.repository;

import com.colak.springtutorial.jpa.Author;
import com.colak.springtutorial.jpa.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
class AuthorRepository2Test {

    @Autowired
    private AuthorRepository2 repository;

    @Test
    @Transactional
    void findById() {
        Optional<Author> optional = repository.findById(1L);
        optional.ifPresentOrElse(author -> {
                    for (Book book : author.getBooks()) {
                        log(author, book);
                    }
                },
                () -> Assertions.fail("Test failed")
        );
    }

    @Test
    @Transactional
    void findByAll() {
        List<Author> authorList = repository.findAll();
        for (Author author : authorList) {
            for (Book book : author.getBooks()) {
                log(author, book);
            }
        }
    }

    private void log(Author author, Book book) {
        log.info("Author : {} Book title : {}", author.getName(), book.getTitle());
    }
}
