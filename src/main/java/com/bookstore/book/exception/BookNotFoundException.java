package com.bookstore.book.exception;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(Long id){
        super("can not found the user by id"+id);
    }


}
