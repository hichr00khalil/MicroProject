package esprit.microservice1.entities;


import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
public class Notification {
    @Id
    @GeneratedValue
    private int idNotif;
    private String message;
    private Date date;
    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Etudiant> etudiants;

    public Notification() {
    }

    public Notification(String message, Date date, String email) {
        this.message = message;
        this.date = date;
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIdNotif() {
        return idNotif;
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
}
