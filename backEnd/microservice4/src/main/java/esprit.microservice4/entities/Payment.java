package esprit.microservice4.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.util.Date;

@Entity

public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private PAYMETHOD paymentMethod;

     private String carte;

     private String titulairecarte;

     private String cvv;

     private String jusqua;

    private Date paymentDate = new Date();

    @OneToOne
     @JoinColumn(name = "facture_id", referencedColumnName = "id")
    private Facture facture;


    public Payment() {
    }

    public Payment(Long id, Date paymentDate, PAYMETHOD paymentMethod) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
    }

    public Payment(Long id,  PAYMETHOD paymentMethod, String carte, String titulairecarte, String cvv, String jusqua, Date paymentDate) {
        this.id = id;
         this.paymentMethod = paymentMethod;
        this.carte = carte;
        this.titulairecarte = titulairecarte;
        this.cvv = cvv;
        this.jusqua = jusqua;
        this.paymentDate = paymentDate;
    }

    public String getJusqua() {
        return jusqua;
    }

    public void setJusqua(String jusqua) {
        this.jusqua = jusqua;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getTitulairecarte() {
        return titulairecarte;
    }

    public void setTitulairecarte(String titulairecarte) {
        this.titulairecarte = titulairecarte;
    }

    public String getCarte() {
        return carte;
    }

    public void setCarte(String carte) {
        this.carte = carte;
    }



    public Payment(Long id, Date paymentDate, PAYMETHOD paymentMethod, Facture facture) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.facture = facture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }


    public PAYMETHOD getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PAYMETHOD paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }
}


