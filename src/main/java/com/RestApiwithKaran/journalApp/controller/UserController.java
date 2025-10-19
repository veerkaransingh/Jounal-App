package com.RestApiwithKaran.journalApp.controller;

import com.RestApiwithKaran.journalApp.Repository.UserRepository;
import com.RestApiwithKaran.journalApp.Service.UserService;
import com.RestApiwithKaran.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
// adds mapping specifically to this class
public class UserController {

   @Autowired
   private UserService userService;

   @Autowired
   private UserRepository userRepository;
   @PutMapping //now Authenticated
   public ResponseEntity<?> updateUser(@RequestBody User user){

      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      /* Whenever a user is authenticated, his/her details are saved in SecurityContextHolder
      Now in postman, a user will type username and password, password will be matched with one in the db, if same,
      user will be allowed to do modifications!
       */
      String userName = authentication.getName();
      User userInDb = userService.findByUserName(userName); // will find inside db and then updates it
      userInDb.setUserName(user.getUserName());
      userInDb.setPassword(user.getPassword());
      userService.saveNewUser(userInDb);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }


   @DeleteMapping
   public ResponseEntity<?> deleteUserById(){
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      userRepository.deleteByUserName(authentication.getName());

      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
}