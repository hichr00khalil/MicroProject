package esprit.microservice1.entities;

import jakarta.persistence.*;

@Entity
public class Ttable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numTab;
    private int capacite;

    @Enumerated(EnumType.STRING)
    private Statut statut;

    // Constructeur vide
    public Ttable() {}

    // Constructeur avec tous les champs
    public Ttable(Long id, int numTab, int capacite, Statut statut) {
        this.id = id;
        this.numTab = numTab;
        this.capacite = capacite;
        this.statut = statut;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumTab() {
        return numTab;
    }

    public void setNumTab(int numTab) {
        this.numTab = numTab;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
}
