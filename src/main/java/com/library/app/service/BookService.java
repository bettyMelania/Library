package com.library.app.service;

import com.library.app.model.Book;
import com.library.app.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("employeeService")
@Transactional
public class BookService {
    @Autowired
    private BookRepository bookRepo;

    public void saveBook(Book book) {
        bookRepo.saveBook(book);
    }

    public List<Book> findAllBooks() {
        return bookRepo.findAllBooks();
    }


}
