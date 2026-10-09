package com.mayur.journalApp.repository;

import com.mayur.journalApp.scheduler.UserScheduler;
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

    @Autowired
    private UserScheduler userScheduler;

    @Test
    public void testSentimentalAnalysisUsers(){
        userScheduler.fetchUserAndSendSaEmail();
    }
}
