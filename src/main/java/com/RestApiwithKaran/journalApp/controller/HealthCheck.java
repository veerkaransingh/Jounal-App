package com.RestApiwithKaran.journalApp.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // this class became a component with additional functionality
//whatever endpoints or URL paths will be there, whatever they will return, will be automatically convert to JSON format
public class HealthCheck {

    //We need to map this function to a path via @GetMapping

    @GetMapping("/health-check")
    public String healthCheck(){ //this function will get mapped to /health-check endpoint
        // @GetMapping simply means the call will be of get type
        return "Ok";
    }
}
