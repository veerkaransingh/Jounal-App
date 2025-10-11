package com.RestApiwithKaran.journalApp.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.ArrayList;

import java.time.LocalDateTime;
// Users would be having multiple journal entries. One journal entry -- associated to a specific user
// Basically building a connection between a user and a journal entry
@Document(collection = "users")
@Data

public class User{
    @Id // will get mapped to an objectId in mongodb
    private ObjectId id;

    @Indexed(unique = true) // username should be unique, searching will also be fast
    @NonNull // should not be null
    private String userName;
    @NonNull //should not be null - annotation of lombok
    private String password;

    @DBRef // we are creating a reference in users collection, of journal entries
    //list (journalEntries) will keep reference of all those entries which are there in Journal Entru entity
    private List<JournalEntry> journalEntries = new ArrayList<>(); //whenever a user will be initiated, there will be an
    // empty list of Journal entries (non null)

    private List<String> roles; // What roles a user can have (corresponding to this entity) in a single document
}
