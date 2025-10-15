package com.RestApiwithKaran.journalApp.Repository;


import com.RestApiwithKaran.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

//an interface has been provided with Spring data mongodb
//Journal EntryRepository (our created interface) runs a query from db
/* MongoRepository: a pre given interface provided by Spring Data MongoDb which we added into pom.xml and it is
performing standard CRUD operations*/

public interface UserRepository extends MongoRepository <User, ObjectId> {
    /*Inside generics, we need to give two parameters, 1st one is that on which we want to perform
    an operation, User(POJO/entity) in our case and 2nd is the type of id, ObjectId in our case for id
     */

    User findByUserName(String username); //Declared a method which will find username in the db which has been modified

    void deleteByUserName(String username);
}


