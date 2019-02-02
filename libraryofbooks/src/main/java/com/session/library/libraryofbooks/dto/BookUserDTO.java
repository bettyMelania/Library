package com.session.library.libraryofbooks.dto;

import com.session.library.libraryofbooks.model.Book;
import com.session.library.libraryofbooks.model.User;

public class BookUserDTO {

    public User user;
    public Book book;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BookUserDTO(User user, Book book) {
        this.user = user;
        this.book = book;
    }

    public BookUserDTO() {

    }
}
