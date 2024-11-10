package com.hooks.pingme.controller;

import com.hooks.pingme.model.Users;
import com.hooks.pingme.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class AuthController {

    @Autowired
    AuthService service;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user){
    return service.login(user);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Users user){
        return service.register(user);
    }

}
