package com.alex.controller;

import org.springframework.web.bind.annotation.*;

@RestController("/")
@CrossOrigin(origins = "http://127.0.0.1:5500/")
public class UserController {

    @PostMapping
    public String insertUser(@RequestBody String body) {
        return body;
    }

    @GetMapping("{user}/{name}")
    public String getUser(@PathVariable String user, @PathVariable String name) {
        System.out.println(user);
        System.out.println(name);
        return user;
    }
}
