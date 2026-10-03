package com.mayur.journalApp.service;

import com.mayur.journalApp.entity.User;
import com.mayur.journalApp.repository.UserRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Slf4j
@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//    public static final Logger logger = LoggerFactory.getLogger(UserService.class); -- instead of using this you can use lombok annotation: @Slfj4

    public void saveNewUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
        }
        catch (Exception e){
            log.error("Error occurred for {} :",user.getUserName(),e);
            log.warn("This is warning");
            log.info("This is info");
            log.debug("Debugging");
            log.trace("This is trace");
        }
    }
    public void saveNewAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public User findByUserName(@NonNull String userName){
        return userRepository.findByUserName(userName);
    }
}
