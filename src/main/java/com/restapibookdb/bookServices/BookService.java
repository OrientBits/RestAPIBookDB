package com.restapibookdb.bookServices;

import com.restapibookdb.dao.BookRepository;
import com.restapibookdb.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookService {
    @Autowired
    private BookRepository bookRepository;


    public List<Book> getAllBooks(){
        List<Book> list = (List<Book>) bookRepository.findAll();
        return list;
    }

    public Book getBookById(int id){
        Book book1 = null;
        try {
           // book1= list.stream().filter(book -> book.getId()==id).findFirst().get();
            book1 = bookRepository.findById(id);
        }catch (Exception e){
            System.out.println(e);
        }
        return book1;
    }

    public Book addBook(Book book){
        Book result = bookRepository.save(book);
        return result;
    }

    public void deleteBook(int id){
        bookRepository.deleteById(id);
        System.out.println("successfully deleted");
    }


//update the book
    public List<Book> updateBook(Book book, int id) {

        book.setId(id);
        bookRepository.save(book);
        List<Book> list = (List<Book>) bookRepository.findAll();
        return list;

    }



}
