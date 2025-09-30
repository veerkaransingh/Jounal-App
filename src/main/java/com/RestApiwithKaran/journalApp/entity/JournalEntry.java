package com.RestApiwithKaran.journalApp.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries") //tells that this class is a mapped entity to a collection in mongodb
public class JournalEntry { // POJO - plain old java object
    //We need to map this class to a collection in Database -- ORM object relational mapping,
    /*An instance of journal entry will be equal to a document or a row in collection in mongodb*/
    @Id // A unique key for our documents in collection
    private ObjectId id; //ObjectId is a datatype of mongodb
    private String title;
    private String content;
    private LocalDateTime date;

    /*public JournalEntry(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }*/

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "JournalEntry{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public ObjectId getId(){
        return id;
    }
    public void setId(ObjectId id){
        this.id = id;
    }
    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }

}
