package com.RestApiwithKaran.journalApp.controller;

import com.RestApiwithKaran.journalApp.entity.JournalEntry;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/journal")
// adds mapping specifically to this class

public class JournalEntryController {

    private Map<Long, JournalEntry> journalEntriesMap = new HashMap<>();
    // will keep journal entries stored in the memory as a key-value collection,
    // where the key is the entry’s ID, and the value is the entry object itself.


    @GetMapping //("/abc")
    public List<JournalEntry> getAll(){ //list of journal entry
       /* journalEntriesMap.put(1L, new JournalEntry(1L, "First", "MyFirstJournal"));
        journalEntriesMap.put(2L, new JournalEntry(2L, "Second", "MySecondJournal"));
        journalEntriesMap.put(3L, new JournalEntry(3L, "Third", "MyThirdJournal")); */


        return new ArrayList<>(journalEntriesMap.values());
        // .values gives all the values in the map, ignoring id's
        // values() returns a Collection, not a List.
        // wrap it with new ArrayList<>(...) to convert it into a List

    }

    @PostMapping
    public void createEntry(@RequestBody){
        //Hey Spring, please take the data from the request and turn it into a java object that i can use in my code

    }

}
