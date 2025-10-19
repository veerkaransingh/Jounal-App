package com.RestApiwithKaran.journalApp.Service;

import com.RestApiwithKaran.journalApp.Repository.UserRepository;
import com.RestApiwithKaran.journalApp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

// Service class holds all the logic
// Controller will call the service
// Service will call the repository

@Component //now spring will create an object of this class and keep it inside
@Slf4j
public class UserService {
    @Autowired // We are injecting JournalEntryRepository in this class
    private UserRepository userRepository;
    /* As JournalEntryRepository is an interface, so there must be an implementation of that and Spring
    handles it by itself at runtime
     */

    public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveNewUser(User user){ //user is an object of type User
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
       userRepository.save(user);

    }

    public void saveUser(User user){
        userRepository.save(user);
    }


    public List<User> getAll(){
        return userRepository.findAll();
    }


    public Optional<User> findById(ObjectId id){ //Optional -- may contain some value or not
        return userRepository.findById(id);
    }
    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public  User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
