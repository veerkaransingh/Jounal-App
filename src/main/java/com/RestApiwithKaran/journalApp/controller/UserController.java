package com.RestApiwithKaran.journalApp.controller;

import com.RestApiwithKaran.journalApp.Service.JournalEntryService;
import com.RestApiwithKaran.journalApp.Service.UserService;
import com.RestApiwithKaran.journalApp.entity.JournalEntry;
import com.RestApiwithKaran.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
// adds mapping specifically to this class
public class UserController {

   @Autowired
   private UserService userService;

   @GetMapping
   public List<User> getAllUsers(){
      return userService.getAll();
   }
   @PostMapping
   public void createUser(@RequestBody User user){
      userService.saveEntry(user);
   }

   @PutMapping("/{userName}")
   public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String userName){
      User userInDb = userService.findByUserName(userName); // will find via PathVariable inside db and then updates it

      // Code for only updating
      if(userInDb != null){
         userInDb.setUserName(user.getUserName());
         userInDb.setPassword(user.getPassword());
         userService.saveEntry(userInDb);
      }
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);


   }

   }