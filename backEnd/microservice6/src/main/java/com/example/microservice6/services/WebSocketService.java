package com.example.microservice6.services;

import com.example.microservice6.entities.Notification;
import com.example.microservice6.model.WebSocketMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendNotification(Notification notification) {
        WebSocketMessage message = WebSocketMessage.createNotificationMessage(notification);
        
        // Envoyer à un topic spécifique pour cet utilisateur
        messagingTemplate.convertAndSend("/topic/notifications/" + notification.getEmail(), message);
        
        // Envoyer également au topic général des notifications
        messagingTemplate.convertAndSend("/topic/notifications", message);
        
        System.out.println("WebSocket notification sent to: " + notification.getEmail());
    }
}
