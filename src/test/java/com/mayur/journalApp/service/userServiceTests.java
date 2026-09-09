package com.mayur.journalApp.service;

import com.mayur.journalApp.entity.User;
import com.mayur.journalApp.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class userServiceTests {

    @Autowired
    private UserRepository userRepository;

//    @BeforeEach //run before each test case
//    void setup(){};
//    similarly @BeforeAll, @AfterEach, @AfterAll
//    Code coverage - it check how much source code is excersized by the test cases.


    @Test
    public void testFindByUserName(){
        assertNotNull(userRepository.findByUserName("Yardi"));
        User user = userRepository.findByUserName("Yardi");
        assertFalse(user.getJournalEntries().isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings ={
            "Yardi",
            "Mayur",
            "Abcd"
    })
    public void testFindByUserName1(String strings){
        assertNotNull(userRepository.findByUserName(strings), "failed for "+ strings);
        User user = userRepository.findByUserName(strings);
//        assertFalse(user.getJournalEntries().isEmpty(), "failed for"+ strings);
    }

    @ParameterizedTest
    @CsvSource({
            "3,1,2",
            "5,2,3",
            "2,2,2"
    })
    public void test(int expected, int a, int b){
        assertEquals(expected, a+b, "failed for"+expected);
    }
}
