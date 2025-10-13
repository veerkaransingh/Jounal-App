package com.RestApiwithKaran.journalApp.Service;

import com.RestApiwithKaran.journalApp.Repository.UserRepository;
import com.RestApiwithKaran.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
 public class UserDetailsServiceImpl implements UserDetailsService { //this(UserDetailsService) is the class Spring Security calls whenever a user tries to log in

    @Autowired
    private  UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Spring Security receives the username (karan123) from the login form or Basic Auth header.
        User user = userRepository.findByUserName(username); //You look up that user(karan123) in MongoDB
        if(user != null){
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    /*
                    you’re using a built-in helper class called User (from Spring Security, not your own entity).
                    It helps you build a UserDetails object, which Spring Security understands
                     */
                    .username(user.getUserName()) //Set username for Spring -- "karan123"
                    .password(user.getPassword())  //Set encoded password --   $2a$10$ABC123...
                    .roles(user.getRoles().toArray(new String[0])) //Give roles/permissions  -- ["USER"]
                    .build(); //Create final UserDetails object ready for spring
            return userDetails; //Give this object to Spring ---used for authentication
        }
        throw new UsernameNotFoundException("User not found with username: "+ username);
    }
}
