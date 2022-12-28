package com.bookstore.book.controller;

import com.bookstore.book.exception.BookNotFoundException;
import com.bookstore.book.model.Book;
import com.bookstore.book.repository.BookRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class BookController {

    @Autowired
    private BookRepository bookRepository;


    //create book
    @PostMapping("/book")
    Book newBook(@RequestBody Book newBook){
        return bookRepository.save(newBook);
    }

    //read all books
    @GetMapping("/books")
    List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    //read by id
    @GetMapping("/book/{id}")
    Book getBookById(@PathVariable Long id){
        return bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
    }

    @PutMapping("/book/{id}")
    //update book
    Book updatebook (@RequestBody Book newBook, @PathVariable Long id){
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(newBook.getTitle());
                    book.setAuthor(newBook.getAuthor());
                    book.setCost(newBook.getCost());
                    return bookRepository.save(book);
                }).orElseThrow(()-> new BookNotFoundException(id));

    }

    //Delete Book

    @DeleteMapping("/book/{id}")
    String deleteBook (@PathVariable Long id){
        if (!bookRepository.existsById(id)){
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
        return "Book " +id+"has been deleted";
    }
}
