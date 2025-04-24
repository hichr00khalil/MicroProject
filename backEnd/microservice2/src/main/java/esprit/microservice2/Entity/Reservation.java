package esprit.microservice2.Entity;

import jakarta.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private Time heure;

    private int nbrPersonne;

    // Constructeur vide
    public Reservation() {}

    // Constructeur avec tous les champs
    public Reservation(Long id, Date date, Time heure, int nbrPersonne) {
        this.id = id;
        this.date = date;
        this.heure = heure;
        this.nbrPersonne = nbrPersonne;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHeure() {
        return heure;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }

    public int getNbrPersonne() {
        return nbrPersonne;
    }

    public void setNbrPersonne(int nbrPersonne) {
        this.nbrPersonne = nbrPersonne;
    }
}
