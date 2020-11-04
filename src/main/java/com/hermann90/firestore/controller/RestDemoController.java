package com.hermann90.firestore.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.hermann90.firestore.objects.Person;
import com.hermann90.firestore.service.FirebaseService;

@RestController
public class RestDemoController {

    @Autowired
    FirebaseService firebaseService;
    
    @GetMapping("/getUserDetails")
    public Person getExample(@RequestHeader() String name) throws InterruptedException, ExecutionException {
        return firebaseService.getUserDetail(name);
    }

    @PostMapping("/createUser")
    public String postExample(@RequestBody Person person) throws InterruptedException, ExecutionException {
        String result = firebaseService.saveUserDetail(person);
        return "Created User " + person.getName() + " At "+result;
    }

    @PutMapping("/updateUser")
    public String putExample(@RequestBody Person person) throws InterruptedException, ExecutionException {
        return firebaseService.updateUserDetail(person);
    }

    @DeleteMapping("/deleteUser")
    public String deleteExample(@RequestHeader String name) throws InterruptedException, ExecutionException {
        return firebaseService.delete(name);
    }

}
