package com.RestApiwithKaran.journalApp.Service;

import com.RestApiwithKaran.journalApp.Repository.JournalEntryRepository;
import com.RestApiwithKaran.journalApp.entity.JournalEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

// Service class holds all the logic
// Controller will call the service
// Service will call the repository

@Component //now spring will create an object of this class and keep it inside
public class JournalEntryService {
    @Autowired // We are injecting JournalEntryRepository in this class
    private JournalEntryRepository journalEntryRepository;
    /* As JournalEntryRepository is an interface, so there must be an implementation of that and Spring
    handles it by itself at runtime
     */

    public void saveEntry(JournalEntry journalEntry){ //journalEntry is an object of type JournalEntry
        journalEntryRepository.save(journalEntry);

    }

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();

    }
}
