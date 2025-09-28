package com.RestApiwithKaran.journalApp.controller;

import com.RestApiwithKaran.journalApp.Service.JournalEntryService;
import com.RestApiwithKaran.journalApp.entity.JournalEntry;
import org.springframework.beans.factory.annotation.Autowired;
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
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService; //an instance of JournalEntryService


    @GetMapping //("/abc")
    public List<JournalEntry> getAll(){ //list of journal entry
        return journalEntryService.getAll();
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry) { // myEntry is an instance of journal entry
        journalEntryService.saveEntry(myEntry);
        return true;
    }
    @GetMapping("id/{myId}")// to get any specific id which we ask via postman
    public JournalEntry getJournalEntryById(@PathVariable String myId){
        return null;
    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteJournalEntryById(@PathVariable String myId){
        return null;
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateJournalById(@PathVariable Long id, @RequestBody JournalEntry myEntry){
        return null;
    }
}