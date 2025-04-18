package com.example.microservice6.services;

import com.example.microservice6.entities.Notification;

/**
 * Interface pour les services d'email
 */
public interface IEmailService {
    
    /**
     * Envoie un email de notification à l'utilisateur
     * @param notification La notification à envoyer par email
     */
    void sendNotificationEmail(Notification notification);
}
