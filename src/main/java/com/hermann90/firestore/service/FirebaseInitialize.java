/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermann90.firestore.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hermann90
 */
@Service
public class FirebaseInitialize {

    @PostConstruct
    public void initialize() {

        try {
            FileInputStream serviceAccount = 
                    new FileInputStream("./serviceAccountKey.json");
            
                FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://fir-spring-ac52e.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
        
        } catch (IOException ex) {
            Logger.getLogger(FirebaseInitialize.class.getName()).log(Level.SEVERE, null, ex);
        }

    

    }
}
