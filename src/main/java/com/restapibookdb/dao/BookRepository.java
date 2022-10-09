package com.restapibookdb.dao;

import com.restapibookdb.entities.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Integer> {

    // creating custom method
    public Book findById(int id);
}
