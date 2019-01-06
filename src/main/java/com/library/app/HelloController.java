package com.library.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(Model model) {
        String name="BETTY";
        model.addAttribute("name", name);
        return "hello";
    }
}