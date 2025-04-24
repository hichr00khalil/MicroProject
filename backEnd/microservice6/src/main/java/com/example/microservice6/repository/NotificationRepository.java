package com.example.microservice6.repository;

import com.example.microservice6.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByReservationId(Long reservationId);
    List<Notification> findByIsRead(boolean isRead);
}
