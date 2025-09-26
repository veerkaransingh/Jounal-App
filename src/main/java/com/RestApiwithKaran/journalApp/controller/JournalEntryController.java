package com.RestApiwithKaran.journalApp.controller;

import com.RestApiwithKaran.journalApp.entity.JournalEntry;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.error.MarkedYAMLException;

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
        journalEntriesMap.put(3L, new JournalEntry(3L, "Third", "MyThirdJournal"));*/

        return new ArrayList<>(journalEntriesMap.values());
        // .values gives all the values in the map, ignoring id's
        // values() returns a Collection, not a List.
        // wrap it with new ArrayList<>(...) to convert it into a List
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry) { // myEntry is an instance of journal entry
        //Hey Spring, please take the data from the request and turn it into a java object that i can use in my code
        System.out.println("Create Entry method is called");
        System.out.println("I am getting this data from postman - body" + myEntry.toString());
        journalEntriesMap.put(myEntry.getId(), myEntry);
        System.out.println("Data from the post request has been added to map");
        return true;
    }
    @GetMapping("id/{myId}")// to get any specific id which we ask via postman
    public JournalEntry getJournalEntryById(@PathVariable Long myId){
        return journalEntriesMap.get(myId); //whatever id will be passed via url from postman, will go here in(myId)
    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteJournalEntryById(@PathVariable Long myId){
        return journalEntriesMap.remove(myId);
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateJournalById(@PathVariable Long id, @RequestBody JournalEntry myEntry){
       return journalEntriesMap.put(id, myEntry);
    }
}


