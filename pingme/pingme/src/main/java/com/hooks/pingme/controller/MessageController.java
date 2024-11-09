package com.hooks.pingme.controller;

import com.hooks.pingme.model.Message;
import com.hooks.pingme.model.Users;
import com.hooks.pingme.repo.MessageRepo;
import com.hooks.pingme.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    UsersRepo repo;

    @Autowired
    MessageRepo repo1;

    @GetMapping("/sample")
    public List<Users> sample(){
        return repo.findAll();
    }

     @PostMapping("/sendMessage")
     public ResponseEntity<Message> sendMessage(@RequestBody Message message){
        repo1.save(message);
         return new ResponseEntity<>(message, HttpStatus.OK);
     }


}
