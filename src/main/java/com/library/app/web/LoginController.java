package com.library.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {


    @RequestMapping(value={"", "/", "/login"}, method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model) {
        //if credentials ok then go to home
        return "redirect:/home";
    }
}
