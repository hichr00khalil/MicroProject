package com.example.microservice6.entities;

// Import supprimé car le package n'existe pas
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Notification {
        @Id
        @GeneratedValue
        private Integer id;
        private String message;
        private Date date;
        private String email;
        private boolean read = false;

        @CreationTimestamp
        @Temporal(TemporalType.TIMESTAMP)
        private Date createdAt;

        public Notification(String message, Date date, String email) {
            this.message = message;
            this.date = date;
            this.email = email;
            this.read = false;
        }

        public Notification(String message, Date date, String email, boolean read) {
            this.message = message;
            this.date = date;
            this.email = email;
            this.read = read;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        // Pour la compatibilité avec le code existant
        public int getIdNotif() {
            return id != null ? id : 0;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

    private Long reservationId;

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", date=" + date +
                ", email='" + email + '\'' +
                ", read=" + read +
                ", createdAt=" + createdAt +
                ", reservationId=" + reservationId +
                '}';
    }
}
