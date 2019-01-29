package com.library.app.service;

import com.library.app.model.Book;
import com.library.app.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import java.util.List;

@Service("employeeService")
@Transactional
public class BookService {
    @Autowired
    private BookRepository bookRepo;

    public void saveBook(MultiValueMap<String, Object> formData,byte[] file) {
        Book book = new Book();
        book.setTitle((String)formData.get("title").get(0));
        book.setAuthor((String)formData.get("author").get(0));
        book.setNrBooks(Integer.parseInt((String)formData.get("nrBooks").get(0)));
        if(file!=null)
            book.setPdffile(file);
        bookRepo.saveBook(book);
    }

    public List<Book> findAllBooks() {
        return bookRepo.findAllBooks();
    }


    public Book getBook(Integer id) {
        return bookRepo.find(id);
    }
}
