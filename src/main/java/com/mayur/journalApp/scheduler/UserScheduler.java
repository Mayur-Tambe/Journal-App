package com.mayur.journalApp.scheduler;

import com.mayur.journalApp.cache.AppCache;
import com.mayur.journalApp.entity.JournalEntry;
import com.mayur.journalApp.entity.User;
import com.mayur.journalApp.enums.Sentiment;
import com.mayur.journalApp.repository.UserRepository;
import com.mayur.journalApp.service.EmailService;
import com.mayur.journalApp.service.JournalEntryService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserScheduler {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AppCache appCache;

//    @Scheduled(cron = "0 0 9 * * SUN")
    @Transactional
    public void fetchUserAndSendSaEmail(){
        List<User> usersForSA = userRepository.getUsersForSA();
        for(User user: usersForSA){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for(Sentiment sentiment: sentiments){
                if(sentiment!=null){
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0)+1);
                }
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for(Map.Entry<Sentiment, Integer> entry: sentimentCounts.entrySet()){
                if(entry.getValue() > maxCount){
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }

            if(mostFrequentSentiment!=null){
                String body = user.getUserName() + " " +mostFrequentSentiment.toString();
                emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days", body);
                log.info("Sentiment email sent to {}", user.getEmail());
            }
            else {
                log.info(
                        "No sentiment found for user: {}. Email not sent.",
                        user.getUserName()
                );
            }
        }
    }

//    @Scheduled(cron="0 */10 * * * *")
    public void clearAppCache(){
        appCache.init();
    }

}
