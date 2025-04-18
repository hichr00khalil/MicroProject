package com.example.microservice6.controller;

import com.example.microservice6.model.WebSocketMessage;
import com.example.microservice6.services.WebSocketService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Contrôleur pour gérer les communications WebSocket entrantes
 */
@Controller
public class WebSocketController {

    @Autowired
    private WebSocketService webSocketService;

    /**
     * Méthode pour traiter les messages entrants des clients
     * Cette méthode est utilisée pour les tests et ne crée pas de notification
     */
    @MessageMapping("/notification-message")
    @SendTo("/topic/notifications")
    public WebSocketMessage processMessage(WebSocketMessage message) {
        // Simplement renvoyer le message pour les tests
        return message;
    }
}
