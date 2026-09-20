package com.mayur.journalApp.service;

import com.mayur.journalApp.repository.UserRepository;
import com.mayur.journalApp.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTests {

    @InjectMocks
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup(){ //initialized userRepository
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsernameTest(){
        User entityUser = new User();
        entityUser.setUserName("Mayur");
        entityUser.setPassword("asdlkf");
        entityUser.setRoles(new ArrayList<>());
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(entityUser);
        UserDetails user = userDetailsServiceImpl.loadUserByUsername("Mayur");
        Assertions.assertNotNull(user);
    }

}
