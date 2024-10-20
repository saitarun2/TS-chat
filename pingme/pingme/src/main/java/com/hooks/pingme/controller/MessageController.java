package com.hooks.pingme.controller;

import com.hooks.pingme.model.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;

@RestController
@RequestMapping("/api")
public class MessageController {

    @GetMapping("/sample")
    public String sample(){
        return "sample";
    }
     @PostMapping("/sendMessage")
     public ResponseEntity<Message> sendMessage(@RequestBody Message message){
         return new ResponseEntity<>(message, HttpStatus.OK);
     }
}
