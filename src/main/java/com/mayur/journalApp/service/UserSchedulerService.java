package com.mayur.journalApp.service;

import com.mayur.journalApp.JournalApplication;
import com.mayur.journalApp.cache.AppCache;
import com.mayur.journalApp.entity.JournalEntry;
import com.mayur.journalApp.entity.User;
import com.mayur.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSchedulerService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AppCache appCache;

    @Autowired
    private SentimentalAnalysisService sentimentalAnalysisService;

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUserAndSendSaEmail(){
        List<User> usersForSA = userRepository.getUsersForSA();
        for(User user: usersForSA){
            List<JournalEntry> allEntries = journalEntryService.getAllEntries();
            List<String> filteredEntries = allEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getTitle()).collect(Collectors.toList());
            String join = String.join(" ",filteredEntries);
            String sentiment = sentimentalAnalysisService.getSentiment(join);
            emailService.JavaMailSender(user.getEmail(), "Sentiment for last 7 days", sentiment);
        }
    }

    @Scheduled(cron="0 */10 * * * *")
    public void clearAppCache(){
        appCache.init();
    }

}
