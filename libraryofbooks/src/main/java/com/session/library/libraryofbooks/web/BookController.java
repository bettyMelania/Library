package com.session.library.libraryofbooks.web;

import com.session.library.libraryofbooks.dto.BookUserDTO;
import com.session.library.libraryofbooks.model.Book;
import com.session.library.libraryofbooks.model.User;
import com.session.library.libraryofbooks.service.BookService;
import com.session.library.libraryofbooks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Controller
@Transactional
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;
    private byte[] file=null;

    @ModelAttribute("admin")
    public boolean admin() {
        if(!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return user.isAdmin();
        }
        return false;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        if(!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("userId", user.getId());
        }
        return "home";
    }


    @RequestMapping(value = "/admin/saveBook", method = RequestMethod.GET)
    public String savePage(Model model){
        model.addAttribute("book", new Book());
        return "saveBook";
    }

    @RequestMapping(value = "/admin/saveFile", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public String saveFile(Model model, @RequestParam("pdffile") MultipartFile f) throws IOException{
        if(f!=null)
            this.file=f.getBytes();
        System.out.println("pdffile: "+f);
        return savePage(model);
    }

    @RequestMapping(value = "/admin/saveBook", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public String save(Model model, @RequestParam("pdffile") MultipartFile f, @RequestParam MultiValueMap<String, Object> formData) throws IOException {
        if(f!=null)
            this.file=f.getBytes();
        bookService.saveBook(formData,this.file);
        file=null;
        return savePage(model);
    }
    @RequestMapping(value = "/admin/adminPage", method = RequestMethod.GET)
    public String adminPage(Model model){
        List<BookUserDTO> bookUser  = userService.findAllUsersBooks();
        model.addAttribute("booksUsers", bookUser);
        return "adminDetails";
    }

    @RequestMapping(value = "/admin/removeBorrowing/{userId}/{bookId}", method = RequestMethod.GET)
    public String removeBorrow(Model model, @PathVariable Integer userId, @PathVariable Integer bookId){
        boolean wasDeleted = userService.deleteBorrowing(userId,bookId);
        if (wasDeleted) {
            model.addAttribute("message", "Deleted succsefully");
        }
        else
        {
            model.addAttribute("message", "Error. No user or book was found");
        }
        return "redirect:/admin/adminPage";
    }

    @RequestMapping(value = "/home/userBooks/removeBorrowing/{userId}/{bookId}", method = RequestMethod.GET)
    public String removeUserBorrow(Model model, @PathVariable Integer userId, @PathVariable Integer bookId){
        boolean wasDeleted = userService.deleteBorrowing(userId,bookId);
        if (wasDeleted) {
            model.addAttribute("message", "Deleted succsefully");
        }
        else
        {
            model.addAttribute("message", "Error. No user or book was found");
        }

        return "redirect:/home/userBooks/" + userId;
    }

    @RequestMapping(value = "/home/userBooks/{userId}", method = RequestMethod.GET)
    public String userBooks(Model model, @PathVariable Integer userId) {
        model.addAttribute("booksUser", userService.findAllUserBooks(userId));
        return "userDetail";
    }

}
