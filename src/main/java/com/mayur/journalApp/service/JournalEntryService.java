package com.mayur.journalApp.service;

import com.mayur.journalApp.entity.JournalEntry;
import com.mayur.journalApp.entity.User;
import com.mayur.journalApp.repository.JournalEntryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username){
        User user = userService.findByUserName(username);
        journalEntry.setDate(LocalDateTime.now());
        journalEntry.setUser(user);
        journalEntryRepository.save(journalEntry);
    }
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAllEntries(){
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> getEntryById(Long id){ //Optional returns null if data not present else return data
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(Long id, String userName){
        boolean removed = false;
        try{
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x-> x.getId().equals(id));
            if(removed){
                journalEntryRepository.deleteById(id);
            }
        }
        catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting the entry.", e);
        }
        return removed;
    }
}
