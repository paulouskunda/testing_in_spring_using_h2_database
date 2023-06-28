package com.paulous.controller;

import com.paulous.pojo.ResponsePojo;
import com.paulous.pojo.UsersPojo;
import com.paulous.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/fetchUserByUsername/{username}")
    public ResponseEntity<?> fetchUserByUsernameEndpoint(@PathVariable String username) {
        return ResponseEntity.ok(new ResponsePojo(HttpStatus.OK.value(), usersService.fetchUserByUsername(username)));
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUserEndpoint(@RequestBody UsersPojo usersPojo) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponsePojo(HttpStatus.CREATED.value(), usersService.addNewUser(usersPojo)));
    }

    @GetMapping("/fetchAllUsers")
    public ResponseEntity<?> fetchAllUsersEndpoint() {
        return ResponseEntity.ok(new ResponsePojo(HttpStatus.OK.value(), usersService.fetchAllUsers()));
    }
}
