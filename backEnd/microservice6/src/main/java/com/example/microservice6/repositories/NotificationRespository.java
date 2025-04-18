package com.example.microservice6.repositories;

import com.example.microservice6.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface NotificationRespository extends JpaRepository<Notification, Integer> {

    // Find notifications by email
    List<Notification> findByEmail(String email);

    // Find notifications by read status
    List<Notification> findByRead(boolean read);

    // Find notifications by email and read status
    List<Notification> findByEmailAndRead(String email, boolean read);

    // Count unread notifications by email
    long countByEmailAndRead(String email, boolean read);

    // Find notifications by reservation ID
    List<Notification> findByReservationId(Long reservationId);

    // Find notifications by email ordered by date (most recent first)
    @Query("SELECT n FROM Notification n WHERE n.email = :email ORDER BY n.date DESC")
    List<Notification> findByEmailOrderByDateDesc(@Param("email") String email);

    // Find notifications older than a specific date
    @Query("SELECT n FROM Notification n WHERE n.createdAt < :date")
    List<Notification> findOlderThan(@Param("date") Date date);
}
