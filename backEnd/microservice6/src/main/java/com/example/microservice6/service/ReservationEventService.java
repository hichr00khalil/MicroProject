package com.example.microservice6.service;

import com.example.microservice6.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReservationEventService {

    @Autowired
    private NotificationService notificationService;
    
    @Autowired
    private RestTemplate restTemplate;
    
    // This method will be called when a reservation is created
    public Notification handleReservationCreated(Long reservationId) {
        return notificationService.createReservationNotification(reservationId);
    }
}
