package com.example.microservice6.model;

import com.example.microservice6.entities.Notification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebSocketMessage {
    private String type;
    private Notification notification;
    private String email;
    
    public static WebSocketMessage createNotificationMessage(Notification notification) {
        return new WebSocketMessage("NOTIFICATION", notification, notification.getEmail());
    }
}
