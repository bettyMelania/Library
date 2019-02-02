package com.session.library.libraryofbooks.service;

import com.session.library.libraryofbooks.model.Book;
import com.session.library.libraryofbooks.model.Role;
import com.session.library.libraryofbooks.model.User;
import com.session.library.libraryofbooks.repository.BookRepository;
import com.session.library.libraryofbooks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("employeeService")
@Transactional
public class BookService {
    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private UserRepository userRepo;

    public void saveBook(MultiValueMap<String, Object> formData, byte[] file) {
        Book book = new Book();
        book.setTitle((String)formData.get("title").get(0));
        book.setAuthor((String)formData.get("author").get(0));

        book.setNopages(Integer.parseInt((String)formData.get("nopages").get(0)));
        book.setYear(Integer.parseInt((String)formData.get("year").get(0)));

        book.setCountry((String)formData.get("country").get(0));
        book.setSummary((String)formData.get("summary").get(0));

        book.setNumbooks(Integer.parseInt((String)formData.get("numbooks").get(0)));
        if(file!=null)
            book.setPdffile(file);
        bookRepo.save(book);
    }

    public List<Book> findAllBooks() {
        List<Book> allBooks = bookRepo.findAll();
        for (Book b : allBooks) {
            if (b.getNumbooks() <= 0) {
                allBooks.remove(b);
            }
        }

        return allBooks;
    }


    public Book getBook(Integer id) {
        return bookRepo.findById(id).get();
    }

    public boolean borrowBook(User user, int bookId) {

        Book book = bookRepo.findById(bookId).get();
        Set<User> bookUsers = book.getUsers();
        bookUsers.add(user);
        book.setUsers(bookUsers);
        int bookNo = book.getNumbooks();
        bookNo--;
        book.setNumbooks(bookNo);
        bookRepo.save(book);
        return true;
    }

    public boolean checkIfNotBorrowed(User user, Integer id) {
        Set<Book> books=user.getBooks();
        for (Book b:books) {
            if(b.getId()==id)
                return false;
        }

        return true;
    }
}

