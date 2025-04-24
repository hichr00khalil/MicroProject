package com.example.microservice6.listener;

import com.example.microservice6.entity.Notification;
import com.example.microservice6.service.NotificationService;
import com.example.microservice6.service.ReservationEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ReservationEventListener {

    @Autowired
    private NotificationService notificationService;
    
    @Autowired
    private ReservationEventService reservationEventService;
    
    /**
     * This method will be called when a reservation is created.
     * It creates a notification for the new reservation.
     * 
     * @param reservationId The ID of the newly created reservation
     * @return The created notification
     */
    public Notification onReservationCreated(Long reservationId) {
        return reservationEventService.handleReservationCreated(reservationId);
    }
}
