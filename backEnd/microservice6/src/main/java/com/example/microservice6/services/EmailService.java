package com.example.microservice6.services;

import com.example.microservice6.entities.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod") // Utiliser ce service uniquement en production
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender emailSender;

    // Méthode pour obtenir l'hôte du serveur SMTP
    private String getHost() {
        if (emailSender instanceof JavaMailSenderImpl) {
            return ((JavaMailSenderImpl) emailSender).getHost();
        }
        return "unknown";
    }

    // Méthode pour obtenir le port du serveur SMTP
    private int getPort() {
        if (emailSender instanceof JavaMailSenderImpl) {
            return ((JavaMailSenderImpl) emailSender).getPort();
        }
        return 0;
    }

    @Value("${app.email.enabled:false}")
    private boolean emailEnabled;

    /**
     * Envoie un email de notification à l'utilisateur
     * @param notification La notification à envoyer par email
     */
    public void sendNotificationEmail(Notification notification) {
        // Vérifier si l'envoi d'emails est activé
        if (!emailEnabled) {
            System.out.println("L'envoi d'emails est désactivé. Email non envoyé à " + notification.getEmail());
            return;
        }

        // Vérifier si l'email est valide
        if (notification.getEmail() == null || notification.getEmail().isEmpty()) {
            System.err.println("Impossible d'envoyer l'email: adresse email manquante");
            return;
        }

        try {
            System.out.println("Tentative d'envoi d'email à " + notification.getEmail());
            System.out.println("Configuration SMTP: " + getHost() + ":" + getPort());

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(notification.getEmail());
            message.setSubject("Nouvelle notification");
            message.setText("Bonjour,\n\nVous avez reçu une nouvelle notification :\n\n" +
                            notification.getMessage() + "\n\nDate : " + notification.getDate() +
                            "\n\nCordialement,\nL'équipe de notification");

            // Envoyer l'email
            emailSender.send(message);
            System.out.println("Email envoyé avec succès à " + notification.getEmail());

            // Afficher un message pour indiquer où vérifier l'email
            if (getHost().contains("mailtrap")) {
                System.out.println("==> Vérifiez votre boîte de réception Mailtrap pour voir l'email envoyé");
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi de l'email : " + e.getMessage());
            System.err.println("Cause: " + (e.getCause() != null ? e.getCause().getMessage() : "inconnue"));

            // Afficher des conseils de dépannage
            if (e.getMessage().contains("Authentication failed")) {
                System.err.println("\nConseils de dépannage pour l'authentification:\n" +
                                   "1. Vérifiez que le nom d'utilisateur et le mot de passe sont corrects\n" +
                                   "2. Si vous utilisez Gmail, assurez-vous d'utiliser un mot de passe d'application\n" +
                                   "3. Essayez d'utiliser Mailtrap pour les tests (voir la documentation)\n");
            }

            e.printStackTrace();
        }
    }
}
