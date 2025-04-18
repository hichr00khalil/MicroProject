package com.example.microservice6.controller;

import com.example.microservice6.entities.Notification;
import com.example.microservice6.services.NotificationService;
import com.example.microservice6.services.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/notification")
@CrossOrigin("*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private IEmailService emailService;

    @Value("${app.email.enabled:false}")
    private boolean emailEnabled;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Notification Service";
    }

    // Get all notifications
    @GetMapping("/list")
    public List<Notification> getAllNotifications() {
        return notificationService.findAll();
    }

    // Get notification by ID
    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable("id") int id) {
        Notification notification = notificationService.findById(id);
        if (notification != null) {
            return new ResponseEntity<>(notification, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get notifications by email (user)
    @GetMapping("/user/{email}")
    public List<Notification> getNotificationsByEmail(@PathVariable("email") String email) {
        return notificationService.findByEmail(email);
    }

    // Get unread notifications by email
    @GetMapping("/user/{email}/unread")
    public List<Notification> getUnreadNotificationsByEmail(@PathVariable("email") String email) {
        return notificationService.findByEmailAndReadStatus(email, false);
    }

    // Get read notifications by email
    @GetMapping("/user/{email}/read")
    public List<Notification> getReadNotificationsByEmail(@PathVariable("email") String email) {
        return notificationService.findByEmailAndReadStatus(email, true);
    }

    // Create a new notification
    @PostMapping("/create")
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        Notification createdNotification = notificationService.createNotification(notification);
        return new ResponseEntity<>(createdNotification, HttpStatus.CREATED);
    }

    // Update a notification
    @PutMapping("/update")
    public ResponseEntity<Notification> updateNotification(@RequestBody Notification notification) {
        Notification updatedNotification = notificationService.updateNotification(notification);
        return new ResponseEntity<>(updatedNotification, HttpStatus.OK);
    }

    // Mark notification as read
    @PutMapping("/{id}/read")
    public ResponseEntity<Notification> markAsRead(@PathVariable("id") int id) {
        Notification notification = notificationService.markAsRead(id);
        if (notification != null) {
            return new ResponseEntity<>(notification, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Mark notification as unread
    @PutMapping("/{id}/unread")
    public ResponseEntity<Notification> markAsUnread(@PathVariable("id") int id) {
        Notification notification = notificationService.markAsUnread(id);
        if (notification != null) {
            return new ResponseEntity<>(notification, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a notification
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteNotification(@PathVariable("id") int id) {
        notificationService.deleteNotification(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Delete old notifications (older than specified days)
    @DeleteMapping("/delete-old/{days}")
    public ResponseEntity<HttpStatus> deleteOldNotifications(@PathVariable("days") int days) {
        notificationService.deleteOldNotifications(days);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Test d'envoi d'email pour une notification existante
    @GetMapping("/{id}/send-email")
    public ResponseEntity<String> testSendEmail(@PathVariable("id") int id) {
        try {
            // Vérifier si l'envoi d'emails est activé
            if (!emailEnabled) {
                return new ResponseEntity<>("L'envoi d'emails est désactivé dans la configuration. Activez-le pour tester cette fonctionnalité.",
                                        HttpStatus.SERVICE_UNAVAILABLE);
            }

            Notification notification = notificationService.findById(id);
            if (notification == null) {
                return new ResponseEntity<>("Notification non trouvée", HttpStatus.NOT_FOUND);
            }

            if (notification.getEmail() == null || notification.getEmail().trim().isEmpty()) {
                return new ResponseEntity<>("La notification n'a pas d'adresse email valide", HttpStatus.BAD_REQUEST);
            }

            emailService.sendNotificationEmail(notification);
            return new ResponseEntity<>("Email envoyé à " + notification.getEmail(), HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi de l'email: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>("Erreur lors de l'envoi de l'email: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Test de la configuration email
    @GetMapping("/test-email-config")
    public ResponseEntity<String> testEmailConfig() {
        try {
            // Vérifier si l'envoi d'emails est activé
            if (!emailEnabled) {
                return new ResponseEntity<>("L'envoi d'emails est désactivé dans la configuration. Activez-le pour tester cette fonctionnalité.",
                                        HttpStatus.SERVICE_UNAVAILABLE);
            }

            // Créer une notification de test
            Notification testNotification = new Notification();
            testNotification.setMessage("Ceci est un test de configuration email");
            testNotification.setEmail("test@example.com");
            testNotification.setDate(new Date());

            // Envoyer l'email
            emailService.sendNotificationEmail(testNotification);

            return new ResponseEntity<>("Configuration email testée avec succès. Vérifiez les logs pour plus de détails.", HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Erreur lors du test de la configuration email: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>("Erreur lors du test de la configuration email: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
