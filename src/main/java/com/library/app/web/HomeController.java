package com.library.app.web;

import com.library.app.model.Book;
import com.library.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    @Autowired
    BookService bookService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        //Book b=new Book();b.setTitle("a");b.setAuthor("b");b.setNrBooks(10);
        //bookService.saveBook(b);
        model.addAttribute("books", bookService.findAllBooks());
        return "home";
    }
}
