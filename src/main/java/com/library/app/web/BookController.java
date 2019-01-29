package com.library.app.web;

import com.library.app.model.Book;
import com.library.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class BookController {
    @Autowired
    BookService bookService;
    private byte[] file=null;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "home";
    }

    @RequestMapping(value = "/saveBook", method = RequestMethod.GET)
    public String savePage(Model model){
        model.addAttribute("book", new Book());
        return "saveBook";
    }

    @RequestMapping(value = "/saveFile", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public String saveFile(Model model, @RequestParam("pdffile") MultipartFile f)throws IOException{
        if(f!=null)
            this.file=f.getBytes();
        System.out.println("pdffile: "+f);
        return savePage(model);
    }

    @RequestMapping(value = "/saveBook", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public String save( Model model,@RequestParam MultiValueMap<String, Object> formData){//} @RequestPart("title") String title, @RequestPart("author") String author, @RequestPart("nrBooks") Integer nrBooks, @RequestPart("file") MultipartFile file){
        bookService.saveBook(formData,this.file);
        file=null;
        return savePage(model);
    }
    @RequestMapping(value = "/downloadFile/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource> savePage(@PathVariable Integer id){
        Book book=bookService.getBook(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/pdf"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +  book.getTitle()+ ".pdf\"")
                .body(new ByteArrayResource(book.getPdffile()));
    }
}
