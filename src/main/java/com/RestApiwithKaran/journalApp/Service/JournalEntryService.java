package com.RestApiwithKaran.journalApp.Service;

import com.RestApiwithKaran.journalApp.Repository.JournalEntryRepository;
import com.RestApiwithKaran.journalApp.entity.JournalEntry;
import com.RestApiwithKaran.journalApp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// Service class holds all the logic
// Controller will call the service
// Service will call the repository

@Component //now spring will create an object of this class and keep it inside
@Slf4j
public class JournalEntryService {
    @Autowired // We are injecting JournalEntryRepository in this class
    private JournalEntryRepository journalEntryRepository;
    /* As JournalEntryRepository is an interface, so there must be an implementation of that and Spring
    handles it by itself at runtime
     */

    @Autowired
    private UserService userService; // Injecting an instance of UserService class

    @Transactional
    /* Now everything under this annotation will be treated as single operation, and if any
    operation fails, then those ones which were successfull would also roll back.
     */

    public void saveEntry(JournalEntry journalEntry, String userName) { //journalEntry is an object of type JournalEntry
        /*This method saveEntry saves a new journal entry in MongoDB and also links that entry to a specific user in the DB
           saveEntry(new JournalEntry("My Day", "Learned Spring Boot"), "karan123");
         */
        try {
            User user = userService.findByUserName(userName);
        /*find the user who is creating this journal entry
        {
                "id": "u101",
                "userName": "karan123",
                "password": "karanexam",
                "journalEntries": []
         }

         */


            journalEntry.setDate(LocalDateTime.now());  //setting date in the coming journal entry

        /* Adds the current date and time to the journal entry before saving.
        {
         "title": "My Day",
         "content": "Learned Spring Boot",
         "date": "2025-10-05T12:30:00"
        }
         */


            JournalEntry saved = journalEntryRepository.save(journalEntry); //the entry saved in the db

        /* After saving, saved would look like
        {
          "id": "j102",
          "title": "My Day",
          "content": "Learned Spring Boot",
          "date": "2025-10-05T12:30:00"
        }
         */

            user.getJournalEntries().add(saved);// added saved journal entry to the list of entries of a user
        /* Every user likely has a list/array of their journal entries.
           (For example, List<JournalEntry> journalEntries; inside the User class.)
           This line adds the newly saved journal entry to that user’s list
           "journalEntries": [
            {
              "id": "j102",
              "title": "My Day",
              "content": "Learned Spring Boot",
              "date": "2025-10-05T12:30:00"
            }
            ]
         */


           // user.setUserName(null); // now this will throw null pointer exception as userName can't be null as we annotated in user class

            userService.saveUser(user);// added user to the db with added journal entries associated to it

        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occured while saving the entry." + e);
        }
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }


    public Optional<JournalEntry> findById(ObjectId id){ //Optional -- may contain some value or not
        return journalEntryRepository.findById(id);
    }
    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);

             removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }

        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occured while deleting the entry.", e);
        }
        return removed;
    }

    public List<JournalEntry> findByUserName(String userName) {
        User user = userService.findByUserName(userName);
        return user.getJournalEntries();
    }

}
