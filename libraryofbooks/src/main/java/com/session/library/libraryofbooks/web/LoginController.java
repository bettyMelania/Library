package com.session.library.libraryofbooks.web;

import com.session.library.libraryofbooks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;

@Controller
@Transactional
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/403")
    public String accessDenied() {
        return "/403";
    }

    @RequestMapping(value={"", "/", "/login"}, method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "login";
    }
}
