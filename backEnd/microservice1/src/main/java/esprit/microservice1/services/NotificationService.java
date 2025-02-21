package esprit.microservice1.services;

import esprit.microservice1.entities.Notification;
import esprit.microservice1.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;
    public List<Notification> findAll() {
        return NotificationRepository.findAll();
    }
}
