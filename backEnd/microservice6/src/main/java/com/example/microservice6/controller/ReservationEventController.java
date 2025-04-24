package com.example.microservice6.controller;

import com.example.microservice6.entity.Notification;
import com.example.microservice6.listener.ReservationEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mic6/events")
@CrossOrigin(origins = "*")
public class ReservationEventController {

    @Autowired
    private ReservationEventListener reservationEventListener;

    /**
     * Endpoint to handle reservation creation events.
     * This can be called by microservice2 when a new reservation is created.
     * 
     * @param reservationId The ID of the newly created reservation
     * @return The created notification
     */
    @PostMapping("/reservation-created/{reservationId}")
    public ResponseEntity<Notification> handleReservationCreated(@PathVariable Long reservationId) {
        Notification notification = reservationEventListener.onReservationCreated(reservationId);
        return new ResponseEntity<>(notification, HttpStatus.CREATED);
    }
}
