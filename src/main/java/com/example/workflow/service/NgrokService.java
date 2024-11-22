package com.example.workflow.service;

import com.ngrok.Session;
import com.ngrok.Http;
import lombok.var;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Service
public class NgrokService {

    private static String publicUrl;

    @PostConstruct
    public void startNgrok() {
        try {
            // Start ngrok session
            var sessionBuilder = Session.withAuthtokenFromEnv().metadata("Spring Boot Ngrok Session");

            try (var session = sessionBuilder.connect()) {
                var listenerBuilder = session.httpEndpoint().metadata("Spring Boot App")
                        .oauthOptions(new Http.OAuth("google"));
                try (var listener = listenerBuilder.listen()) {
                    publicUrl = listener.getUrl();
                    System.out.println("Ngrok is running at: " + publicUrl);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error starting ngrok.");
        }
    }

    @PreDestroy
    public void stopNgrok() {
        // Handle stopping ngrok on application shutdown
        System.out.println("Ngrok stopped.");
    }

    public static String getPublicUrl() {
        return publicUrl;
    }
}
