package com.example.microservice6.service;

import com.example.microservice6.entity.Notification;
import com.example.microservice6.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // Create a new notification
    public Notification createNotification(Notification notification) {
        notification.setCreatedAt(new Date());
        return notificationRepository.save(notification);
    }

    // Create a notification for a new reservation
    public Notification createReservationNotification(Long reservationId) {
        Notification notification = new Notification();
        notification.setMessage("New reservation created with ID: " + reservationId);
        notification.setCreatedAt(new Date());
        notification.setRead(false);
        notification.setReservationId(reservationId);
        return notificationRepository.save(notification);
    }

    // Get all notifications
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // Get notification by ID
    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepository.findById(id);
    }

    // Get notifications by reservation ID
    public List<Notification> getNotificationsByReservationId(Long reservationId) {
        return notificationRepository.findByReservationId(reservationId);
    }

    // Get unread notifications
    public List<Notification> getUnreadNotifications() {
        return notificationRepository.findByIsRead(false);
    }

    // Update a notification
    public Notification updateNotification(Long id, Notification notificationDetails) {
        Optional<Notification> notification = notificationRepository.findById(id);
        if (notification.isPresent()) {
            Notification existingNotification = notification.get();
            existingNotification.setMessage(notificationDetails.getMessage());
            existingNotification.setRead(notificationDetails.isRead());
            return notificationRepository.save(existingNotification);
        }
        return null;
    }

    // Mark notification as read
    public Notification markAsRead(Long id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        if (notification.isPresent()) {
            Notification existingNotification = notification.get();
            existingNotification.setRead(true);
            return notificationRepository.save(existingNotification);
        }
        return null;
    }

    // Delete a notification
    public boolean deleteNotification(Long id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        if (notification.isPresent()) {
            notificationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
