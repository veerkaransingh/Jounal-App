package com.RestApiwithKaran.journalApp.controller;


import com.RestApiwithKaran.journalApp.Service.UserService;
import com.RestApiwithKaran.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController // this class became a component with additional functionality
//whatever endpoints or URL paths will be there, whatever they will return, will be automatically convert to JSON format
@RequestMapping("/public")
public class PublicController {


    @Autowired
    private UserService userService;
    //We need to map this function to a path via @GetMapping

    @GetMapping("/health-check")
    public String healthCheck(){ //this function will get mapped to /health-check endpoint
        // @GetMapping simply means the call will be of get type
        return "Ok";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);

    }
}
