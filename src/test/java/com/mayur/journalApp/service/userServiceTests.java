package com.mayur.journalApp.service;

import com.mayur.journalApp.entity.JournalEntry;
import com.mayur.journalApp.entity.User;
import com.mayur.journalApp.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest //disabled tests
public class userServiceTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

//    @BeforeEach //run before each test case
//    void setup(){};
//    similarly @BeforeAll, @AfterEach, @AfterAll
//    Code coverage - it check how much source code is excersized by the test cases.

    @BeforeEach
    void setup(){ //initialized userRepository
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testFindByUserName(){
        User entityUser = new User();
        entityUser.setUserName("Yardi");
        entityUser.setPassword("Yardi");
        entityUser.setRoles(new ArrayList<>());

        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setTitle("Test Entry");

        entityUser.setJournalEntries(new ArrayList<>());
        entityUser.getJournalEntries().add(journalEntry);

        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(entityUser);
        assertNotNull(userRepository.findByUserName("Yardi"));
        User user = userRepository.findByUserName("Yardi");
        assertFalse(user.getJournalEntries().isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings ={
            "Yardi",
            "Mayur"
//            ,"Abcd" // test will fail if this
    })
    public void testFindByUserName1(String strings){
        User entityUser = new User();
        entityUser.setUserName("Yardi");
        entityUser.setPassword("Yardi");
        entityUser.setRoles(new ArrayList<>());

        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(entityUser);

        assertNotNull(userRepository.findByUserName(strings), "failed for "+ strings);
        //User user = userRepository.findByUserName(strings);
//        assertFalse(user.getJournalEntries().isEmpty(), "failed for"+ strings);
    }

    @ParameterizedTest
    @CsvSource({
            "3,1,2",
            "5,2,3"
//            ,"2,2,2" // test will fail if this
    })
    public void test(int expected, int a, int b){
        assertEquals(expected, a+b, "failed for"+expected);
    }
}
