package com.utm.prhttp.rest;

import com.utm.prhttp.dao.DAO;
import com.utm.prhttp.model.Role;
import com.utm.prhttp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class Auth {

    @Autowired
    private DAO dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/user/add")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            dao.save(user);
            return new ResponseEntity<>("user saved", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/get")
    public ResponseEntity<?> getUsers() {
        try {
            return new ResponseEntity<>(dao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> mainResponse() {
        try {
            if (dao.count() == 0) {
                User user = new User("dluncasu", passwordEncoder.encode("password"), Arrays.asList(new Role("ACTUATOR"), new Role("USER")));
                dao.save(user);
            }
            return new ResponseEntity<>("Hello", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
