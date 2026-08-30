package com.mayur.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //Needs to add this to execute transactions.
public class JournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}

	// @Bean
	// public PlatformTransactionManager add(){
	// 	return new JpaTransactionManager();
	// }

}

// @EnableTransactionManagement
//This is get implemented from manager/interface PlatformTransactionManager
//And actual implementation is present in JpaTransactionManager 
// EnableTransactionManagement - manager finds all methods having @Trasactional and create one context (box) for each separate method
// All these opearations are done by PlatformTransactionManager