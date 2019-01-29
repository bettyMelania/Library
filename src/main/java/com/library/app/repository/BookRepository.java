package com.library.app.repository;

import com.library.app.model.Book;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bookRepo")
public class BookRepository extends AbstractRepository {

    public void saveBook(Book book) {
        persist(book);
    }

    public List<Book> findAllBooks() {
        Criteria criteria = getSession().createCriteria(Book.class);
        return (List<Book>) criteria.list();
    }

    public Book find(Integer id) {
        return (Book) getSession().get(Book.class, id);
    }

}
