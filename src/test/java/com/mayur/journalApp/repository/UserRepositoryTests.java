package com.mayur.journalApp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
public class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testSentimentalAnalysisUsers(){
        assertFalse(userRepository.getUsersForSA() == null || userRepository.getUsersForSA().isEmpty());
    }
}
