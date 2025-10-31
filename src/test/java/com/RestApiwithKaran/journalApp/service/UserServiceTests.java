package com.RestApiwithKaran.journalApp.service;

import com.RestApiwithKaran.journalApp.Repository.UserRepository;
import com.RestApiwithKaran.journalApp.entity.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //To start the entire application context
//Test driven development

public class UserServiceTests {
    @Autowired
    private UserRepository userRepository;

    /*@BeforeEach // if we want to initialize some things before running each test
    void setUp(){

    }*/

    @AfterAll //this chunk will run after all tests have been run
    void setUp(){

    }


    @Disabled // this will not be run now
    @Test
    public void testFindByUserName(){
        User user = userRepository.findByUserName("Starbucks");
        assertEquals(4,2 + 2); // Whether 2 + 2 is equals to 4
        assertNotNull(userRepository.findByUserName("Starbucks")); // Test to find that name found from the repository should not be null
        assertTrue(5>3);
        assertTrue(!user.getJournalEntries().isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings ={ //we need to mention what we are passing, strings in this case
            "Starbucks",
            "Macbook",
            "Diwali",
            "Amazon"
    })
    public void testFindByUserName(String name){
        assertNotNull(userRepository.findByUserName(name),"failed for" + name); // to get the name for which test has been failed
    }



    @Disabled
    @ParameterizedTest // for multiple arguments to test
    @CsvSource({
            "1,1,2", // a's value, b's value, expected
            "2,10, 12"

    })
    public void test(int a , int b, int expected){
        assertEquals(expected,a+b);
    }
}
