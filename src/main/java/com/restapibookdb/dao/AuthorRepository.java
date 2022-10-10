package com.restapibookdb.dao;

import com.restapibookdb.entities.Author;
import com.restapibookdb.entities.Book;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
    public Author findById(int id);
}
