package com.example.microservice6.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    private boolean isRead;
    
    private Long reservationId;
    
    // Constructors
    public Notification() {
    }

    public Notification(String message, Date createdAt, boolean isRead, Long reservationId) {
        this.message = message;
        this.createdAt = createdAt;
        this.isRead = isRead;
        this.reservationId = reservationId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }
}
