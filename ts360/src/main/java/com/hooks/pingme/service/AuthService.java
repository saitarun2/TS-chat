package com.hooks.pingme.service;


import com.hooks.pingme.model.Users;
import com.hooks.pingme.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UsersRepo repo;


    public ResponseEntity<String> login(Users user){

    Users userNameFlag = repo.findByusername(user.getUsername());
        if(userNameFlag==null)

    {
        return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);

    }
        if(!user.getPassword().

    equals(userNameFlag.getPassword()))

    {
        return new ResponseEntity<>("Invalid Password", HttpStatus.UNAUTHORIZED);
    }

        return new ResponseEntity<>("Logged in Successfully",HttpStatus.OK);
}



    public ResponseEntity<String> register(Users user){
        repo.save(user);
        return new ResponseEntity<>("User registered successfully",HttpStatus.OK);
    }

}
