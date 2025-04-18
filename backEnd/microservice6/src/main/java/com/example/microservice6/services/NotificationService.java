package com.example.microservice6.services;

import com.example.microservice6.entities.Notification;
import com.example.microservice6.repositories.NotificationRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.microservice6.services.WebSocketService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private NotificationRespository notificationRespository;

    @Autowired
    private WebSocketService webSocketService;

    @Autowired
    private IEmailService emailService;

    @Value("${app.email.enabled:false}")
    private boolean emailEnabled;

    // Get all notifications
    public List<Notification> findAll() {
        return notificationRespository.findAll();
    }

    // Get notification by ID
    public Notification findById(int id) {
        return notificationRespository.findById(id).orElse(null);
    }

    // Get notifications by email (user)
    public List<Notification> findByEmail(String email) {
        return notificationRespository.findByEmailOrderByDateDesc(email);
    }

    // Get notifications by read status
    public List<Notification> findByReadStatus(boolean read) {
        return notificationRespository.findByRead(read);
    }

    // Get notifications by email and read status
    public List<Notification> findByEmailAndReadStatus(String email, boolean read) {
        return notificationRespository.findByEmailAndRead(email, read);
    }

    // Get count of unread notifications by email
    public long countUnreadByEmail(String email) {
        return notificationRespository.countByEmailAndRead(email, false);
    }

    // Get notifications by reservation ID
    public List<Notification> findByReservationId(Long reservationId) {
        return notificationRespository.findByReservationId(reservationId);
    }

    // Create a new notification
    @Transactional
    public Notification createNotification(Notification notification) {
        Notification savedNotification = notificationRespository.save(notification);

        // Envoyer une notification en temps réel via WebSocket
        webSocketService.sendNotification(savedNotification);

        // Envoyer un email à l'utilisateur qui a créé la notification (si activé)
        if (emailEnabled) {
            try {
                emailService.sendNotificationEmail(savedNotification);
            } catch (Exception e) {
                // Log l'erreur mais continue l'exécution
                System.err.println("Erreur lors de l'envoi de l'email, mais la notification a été créée: " + e.getMessage());
            }
        } else {
            System.out.println("L'envoi d'emails est désactivé. Aucun email envoyé pour la notification #" + savedNotification.getId());
        }

        return savedNotification;
    }

    // Update a notification
    @Transactional
    public Notification updateNotification(Notification notification) {
        // Vérifier si la notification existe
        return notificationRespository.findById(notification.getId())
            .map(existingNotification -> {

        // Mettre à jour les champs modifiables
        existingNotification.setMessage(notification.getMessage());
        existingNotification.setRead(notification.isRead());

        // Si la date est fournie, la mettre à jour
        if (notification.getDate() != null) {
            existingNotification.setDate(notification.getDate());
        }

        // Si l'email est fourni et valide, le mettre à jour
        if (notification.getEmail() != null && !notification.getEmail().trim().isEmpty()) {
            existingNotification.setEmail(notification.getEmail());
        }

        // Si reservationId est fourni, le mettre à jour
        if (notification.getReservationId() != null) {
            existingNotification.setReservationId(notification.getReservationId());
        }

                return notificationRespository.save(existingNotification);
            })
            .orElse(null);
    }

    // Mark notification as read
    public Notification markAsRead(int id) {
        return notificationRespository.findById(id)
            .map(notification -> {
                notification.setRead(true);
                return notificationRespository.save(notification);
            })
            .orElse(null);
    }

    // Mark notification as unread
    public Notification markAsUnread(int id) {
        return notificationRespository.findById(id)
            .map(notification -> {
                notification.setRead(false);
                return notificationRespository.save(notification);
            })
            .orElse(null);
    }

    // Delete a notification
    public void deleteNotification(int id) {
        notificationRespository.findById(id)
            .ifPresent(notification -> notificationRespository.delete(notification));
    }

    // Delete old notifications (older than specified days)
    public void deleteOldNotifications(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -days);
        Date date = calendar.getTime();

        List<Notification> oldNotifications = notificationRespository.findOlderThan(date);
        notificationRespository.deleteAll(oldNotifications);
    }
}
