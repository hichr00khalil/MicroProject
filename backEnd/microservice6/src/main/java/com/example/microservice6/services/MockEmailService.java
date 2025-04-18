package com.example.microservice6.services;

import com.example.microservice6.entities.Notification;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Service d'email simulé pour le développement
 * Ce service n'envoie pas réellement d'emails mais les affiche dans la console
 */
@Service
@Profile({"dev", "default"}) // Utiliser ce service par défaut
public class MockEmailService implements IEmailService {

    @Override
    public void sendNotificationEmail(Notification notification) {
        System.out.println("\n=== MOCK EMAIL SERVICE ===");
        System.out.println("To: " + notification.getEmail());
        System.out.println("Subject: Nouvelle notification");
        System.out.println("Body: ");
        System.out.println("Bonjour,\n");
        System.out.println("Vous avez reçu une nouvelle notification :");
        System.out.println(notification.getMessage());
        System.out.println("\nDate : " + notification.getDate());
        System.out.println("\nCordialement,");
        System.out.println("L'équipe de notification");
        System.out.println("=========================\n");
    }
}
