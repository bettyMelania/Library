package com.session.library.libraryofbooks.service;

import com.session.library.libraryofbooks.dto.BookUserDTO;
import com.session.library.libraryofbooks.model.Book;
import com.session.library.libraryofbooks.model.Role;
import com.session.library.libraryofbooks.model.User;
import com.session.library.libraryofbooks.repository.BookRepository;
import com.session.library.libraryofbooks.repository.RoleRepository;
import com.session.library.libraryofbooks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("userService")
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepo.findOneByName("ROLE_USER");
        user.getRoles().add(userRole);
        userRepo.save(user);
    }

    public boolean getUser(String username, String password) {
        User foundUser = userRepo.findOneByUsername(username);
        if (foundUser != null) {
            System.out.println("user: " + foundUser);
            if (foundUser.getPassword().equals(bCryptPasswordEncoder.encode(password))) {
                System.out.println("login ok");
                return true;
            } else
                return false;
        } else {
            return false;
        }

    }

    public List<BookUserDTO> findAllUsersBooks() {
        List<User> users = userRepo.findAll();
        List<BookUserDTO> booksOfUsers = new ArrayList<>();
        for (User u : users) {
            for (Book b : u.getBooks()) {
                BookUserDTO bu = new BookUserDTO();
                bu.setUser(u);
                bu.setBook(b);
                booksOfUsers.add(bu);
            }
        }

        return booksOfUsers;
    }

    public List<BookUserDTO> findAllUserBooks(int userId) {
        User foundUser = userRepo.findById(userId).orElse(null);
        if (foundUser == null) {
            return null;
        }
        List<BookUserDTO> bookOfUser = new ArrayList<>();
        for (Book b : foundUser.getBooks()) {
            BookUserDTO bu = new BookUserDTO();
            bu.setUser(foundUser);
            bu.setBook(b);
            bookOfUser.add(bu);
        }

        return bookOfUser;
    }


    public boolean deleteBorrowing(int userId, int bookId) {
        User foundUser = userRepo.findById(userId).orElse(null);
        if (foundUser == null) {
            return false;
        }
        for (Book b : foundUser.getBooks()) {
            if (b.getId() == bookId) {
                b.setNumbooks(b.getNumbooks() + 1);
                foundUser.getBooks().remove(b);
                userRepo.save(foundUser);
                bookRepo.save(b);
                return true;
            }
        }
        return false;
    }
}

