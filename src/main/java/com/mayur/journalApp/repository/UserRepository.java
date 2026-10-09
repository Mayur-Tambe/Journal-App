package com.mayur.journalApp.repository;

import com.mayur.journalApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    void deleteByUserName(String userName);

    @Query(value = """ 
             SELECT * FROM users u WHERE u.email is not null and u.email <> '' and u.email ~ '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$'  and u.sentimental_analysis is true
             """, nativeQuery = true)
    List<User> getUsersForSA();

}
