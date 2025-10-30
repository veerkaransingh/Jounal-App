package com.RestApiwithKaran.journalApp.service;

import com.RestApiwithKaran.journalApp.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest //To start the entire application context
//Test driven development

public class UserServiceTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUserName(){
        assertEquals(4,2 + 2); // Whether 2 + 2 is equals to 4
        assertNotNull(userRepository.findByUserName("Starbucks")); // Test to find that name found from the repository should not be null


    }
}
