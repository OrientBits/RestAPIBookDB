package com.restapibookdb.controllers;

import com.restapibookdb.bookServices.BookService;
import com.restapibookdb.entities.Author;
import com.restapibookdb.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    // get all books handler
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> list = this.bookService.getAllBooks();
        System.out.println(list);
        if (list.size() <= 0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    // get a books handler
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id){
        Book book = this.bookService.getBookById(id);
        if (book == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.of(Optional.of(book));
    }


    @GetMapping("/author")
    public ResponseEntity<List<Author>> getAuthor(){
        List<Author> authors = this.bookService.getAllAuthor();
        if (authors == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.of(Optional.of(authors));
    }

    // add books handler
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        try{
            Book book1 = this.bookService.addBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(book1);
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // delete book handler
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") int bookId){
        try {
            this.bookService.deleteBook(bookId);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //update or modify data
    @PutMapping("/books/{id}")
    public ResponseEntity<List<Book>> updateBook(@RequestBody Book book, @PathVariable("id") int id){
        try {
            List<Book> books =this.bookService.updateBook(book, id);
            return ResponseEntity.of(Optional.of(books));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




}
