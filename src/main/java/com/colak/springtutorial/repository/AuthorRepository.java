package com.colak.springtutorial.repository;

import com.colak.springtutorial.jpa.Author;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * This repository uses EntityManager API to solve N+1 Select Problem
 */
@Repository
@RequiredArgsConstructor
public class AuthorRepository {

    private final EntityManager entityManager;


    // See https://medium.com/jpa-java-persistence-api-guide/hibernate-lazyinitializationexception-solutions-7b32bfc0ce98
    // USe  Entity Graphs
    public Author findByIdWithBooks2(@Param("authorId") Long authorId) {
        EntityGraph<Author> graph = entityManager.createEntityGraph(Author.class);
        graph.addAttributeNodes("books");
        Map<String, Object> hints = new HashMap<>();
        hints.put("jakarta.persistence.loadgraph", graph);

        return entityManager.find(Author.class, authorId, hints);
    }

}
