package esprit.microservice1.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
public class Etudiant {
    @Id
    @GeneratedValue
    private long idEtudiant;

    private String nomET;
    private String prenomEt;
    private long cin;
    private String ecole;
    private Date DateNaissance;

    @ManyToMany(mappedBy="etudiants", cascade = CascadeType.ALL)
    private Set<Notification> Reservations;




    public Etudiant(String khalil, String nermine, int i, String yosser) {
    }

    public Etudiant() {

    }

    public long getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(long idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public String getNomET() {
        return nomET;
    }

    public void setNomET(String nomET) {
        this.nomET = nomET;
    }

    public String getPrenomEt() {
        return prenomEt;
    }

    public void setPrenomEt(String prenomEt) {
        this.prenomEt = prenomEt;
    }

    public long getCin() {
        return cin;
    }

    public void setCin(long cin) {
        this.cin = cin;
    }

    public String getEcole() {
        return ecole;
    }

    public void setEcole(String ecole) {
        this.ecole = ecole;
    }

    public Date getDateNaissance() {
        return DateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        DateNaissance = dateNaissance;
    }
}
