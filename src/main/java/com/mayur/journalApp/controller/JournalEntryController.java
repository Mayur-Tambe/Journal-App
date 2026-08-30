//REST - Representational State Transfer
//HTTP verbs - GET, PUT, POST, DELETE
//REST API -> http verb + URL
//Controller -> special type of classes/components which handles HTTP requests.
//Here we write endpoints.
//Whatever present in class e.g. Strings that converts into JSON.

package com.mayur.journalApp.controller;

import com.mayur.journalApp.entity.JournalEntry;
import com.mayur.journalApp.entity.User;
import com.mayur.journalApp.repository.JournalEntryRepository;
import com.mayur.journalApp.service.JournalEntryService;
import com.mayur.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@RequestMapping -> add mapping on entire class.
//E.g. @RequestingMapping("/journal")
//
//And then for method
//@GetMapping("/abc")
//
//Then url for this method will be /journal/abc
// Methods inside a controller class should be public so that they can be accessed and invoked by the spring framework or external http requests.
@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAllJournalEntiresforUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUserName(username);
        List<JournalEntry> all = user.getJournalEntries();
        if(!all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // myId - pathvariable from the URL
//    @PathVariable ->
//    To access the variable from url in the code.
    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable Long myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUserName(username);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        if (!collect.isEmpty())    {
            Optional<JournalEntry> journalEntry = journalEntryService.getEntryById(myId);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    // JournalEntry below is Entity class
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) { // localhost:8080/journal
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        try {
            journalEntryService.saveEntry(myEntry,username);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(myEntry, HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable Long myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        boolean removed = journalEntryService.deleteById(myId, username);
        if (removed)
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<JournalEntry> updateEntry(@PathVariable Long myId, @RequestBody JournalEntry newEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUserName(username);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        if (!collect.isEmpty())    {
            Optional<JournalEntry> journalEntry = journalEntryService.getEntryById(myId);
            if(journalEntry.isPresent()){
                JournalEntry entry = journalEntry.get();
                entry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle():entry.getTitle());
                entry.setContent(newEntry.getContent()!=null && !newEntry.getContent().isEmpty() ? newEntry.getContent():entry.getContent());
                journalEntryService.saveEntry(entry);
                return new ResponseEntity<>(entry, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
}