package com.session.library.libraryofbooks.web;

import com.session.library.libraryofbooks.model.Book;
import com.session.library.libraryofbooks.model.User;
import com.session.library.libraryofbooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;

@Controller
@Transactional
public class DetailController {

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/home/bookDetail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable Integer id, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isNotBorrowed=bookService.checkIfNotBorrowed(user,id);
        model.addAttribute("isNotBorrowed",isNotBorrowed);
        model.addAttribute("book", bookService.getBook(id));
        return "detail";
    }

    @RequestMapping(value = "home/bookDetail/downloadFile/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadBook(@PathVariable Integer id) {

        Book book = bookService.getBook(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/pdf"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + book.getTitle() + ".pdf\"")
                .body(new ByteArrayResource(book.getPdffile()));
    }

    @RequestMapping(value = "home/bookDetail/borrow/{id}", method = RequestMethod.GET)
    public String borrowBook(@PathVariable Integer id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        bookService.borrowBook(user, id);
        return "redirect:/home/bookDetail/" + id;

    }
}
