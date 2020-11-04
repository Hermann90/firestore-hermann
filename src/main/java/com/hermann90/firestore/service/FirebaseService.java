/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermann90.firestore.service;

import com.hermann90.firestore.objects.Person;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firestore.v1.Document;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hermann90
 */
@Service
public class FirebaseService {
    
    public String saveUserDetail(Person person) throws ExecutionException, InterruptedException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult>collectionsApiFuture = dbFirestore.collection("users")
                .document(person.getName()).set(person);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
    
    public Person getUserDetail(String name){
                   
        Person person = null;

        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            DocumentReference documentReference = dbFirestore.collection("users")
                    .document(name);
            ApiFuture<DocumentSnapshot>apiFuture = documentReference.get();
            DocumentSnapshot document = apiFuture.get();
            
            
            if(document.exists()){
                person = document.toObject(Person.class);
            }
            
//            return person;
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return person;
    }
    
     public String updateUserDetail(Person person) throws ExecutionException, InterruptedException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult>collectionsApiFuture = dbFirestore.collection("users")
                .document(person.getName()).set(person);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
     
     public String delete(String name) throws InterruptedException, ExecutionException{
         Firestore dbFirestore = FirestoreClient.getFirestore(); // Connexion to DB
         ApiFuture<WriteResult>apiFuture = dbFirestore.collection("users")
                 .document(name).delete();
         return " Document with ID : "+name+" are Deleted at "+apiFuture.get().getUpdateTime();
     }
}
