package com.hooks.pingme.controller;

import com.hooks.pingme.model.Message;
import com.hooks.pingme.model.Users;
import com.hooks.pingme.repo.MessageRepo;
import com.hooks.pingme.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class MessageController {



    @Autowired
    MessageRepo repo;
    @Autowired
    private Environment environment;
    @PostMapping("/sendMessage")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message){
        String age=environment.getProperty("app.age");
        repo.save(message);
        System.out.println(age);
        return new ResponseEntity<>(message, HttpStatus.OK);
     }
}
