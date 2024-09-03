package com.colak.springtutorial.repository;

import com.colak.springtutorial.jpa.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository2 extends JpaRepository<Author, Long> {


    // See https://medium.com/jpa-java-persistence-api-guide/springdata-n-1-solution-with-namedentitygraph-8b101292261a
    // Use Spring EntityGraph
    // select a*,b.* from author a left join book b on a.id=b.author_id where a.id=?
    @EntityGraph(value = "author[books]")
    Optional<Author> findById(Long authorId);

    // See https://medium.com/@aurigaaristo/8637a9a2399d
    // Use Spring EntityGraph
    // select * from author a left join book b on a.id=b.author_id
    @EntityGraph(attributePaths = "books")
    List<Author> findAll();

}
