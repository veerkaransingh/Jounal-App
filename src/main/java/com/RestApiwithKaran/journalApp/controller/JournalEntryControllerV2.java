package com.RestApiwithKaran.journalApp.controller;

import com.RestApiwithKaran.journalApp.Service.JournalEntryService;
import com.RestApiwithKaran.journalApp.Service.UserService;
import com.RestApiwithKaran.journalApp.entity.JournalEntry;
import com.RestApiwithKaran.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
// adds mapping specifically to this class
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService; //an instance of JournalEntryService
    //

    @Autowired
    private UserService userService;


    @GetMapping() //("/abc")
    public ResponseEntity<?> getAllJournalEntriesofUser(){ //list of journal entry
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         /* Whenever a user is authenticated, his/her details are saved in SecurityContextHolder
      Now in postman, a user will type username and password, password will be matched with one in the db, if same,
      user will be allowed to do modifications!
       */
        String userName = authentication.getName();
        User user = userService.findByUserName(userName); // will look for userName
        List<JournalEntry> all = user.getJournalEntries(); // will get all the journal entries of this particular user
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){ // myEntry is an instance of journal entry

        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
             /* Whenever a user is authenticated, his/her details are saved in SecurityContextHolder
      Now in postman, a user will type username and password, password will be matched with one in the db, if same,
      user will be allowed to do modifications!
       */
            String userName = authentication.getName();
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry, userName); // now two things will go into saveEntry myEntry ( from Request Body), userName
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("id/{myId}")// to get any specific id which we ask via postman
    //ResponseEntity lets you send both data and the correct HTTP code.
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){
        //For sending a response code
        /*Optional is a container (a wrapper box) introduced in Java 8.
            It can either:
                 Contain a value (JournalEntry) → means "found".
                 Be empty → means "not found".*/
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);  // Now the variable journalEntry doesn’t hold the object directly.Instead, it holds an Optional box around that object
        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //return journalEntryService.findById(myId).orElse(null); ----- if got the id, then we are sending it, and if not, we are sending null!!!
    }

    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId, @PathVariable String userName){
        journalEntryService.deleteById(myId, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry){

        // if only title is getting changed, then only title would be modified in the db,
        // and if only content is getting changed ,then only content would be changed in the database.

        JournalEntry old = journalEntryService.findById(id).orElse(null);
       /* if(old != null){
            old.setTitle(newEntry.getTitle()!= null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent()!= null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        } */
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}