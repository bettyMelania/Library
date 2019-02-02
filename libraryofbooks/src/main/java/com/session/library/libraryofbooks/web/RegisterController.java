package com.session.library.libraryofbooks.web;

import com.session.library.libraryofbooks.model.User;
import com.session.library.libraryofbooks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;

@Controller
@Transactional
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping(value={"/register"}, method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "register";
    }

    @RequestMapping(value="/register",params="action=finishRegistration",method= RequestMethod.POST)
    public String validateUsr(@RequestParam("username")String username, @RequestParam("password")String password, @RequestParam("repPassword")String repPassword) {

        if (username == null || password == null || repPassword == null) {
            return "register";
        }
        if (password.compareTo(repPassword) == 0) {
            User u = new User();
            u.setPassword(password);
            u.setUsername(username);
            userService.saveUser(u);
            return "redirect:/login";
        }
        else {
            return "register";
        }
    }

    @RequestMapping(value="/register",params="action=goBack",method= RequestMethod.POST)
    public String goBack() {

        return "redirect:/login";
    }

    @RequestMapping(value="/login",params="action=register",method= RequestMethod.POST)
    public String register() {

        return "redirect:/register";
    }
}
