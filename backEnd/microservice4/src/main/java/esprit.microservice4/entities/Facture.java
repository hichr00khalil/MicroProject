package esprit.microservice4.entities;


import jakarta.persistence.*;


@Entity

public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;


    private Double montantTotale;

    @Enumerated(EnumType.STRING)
    private StatutPay statutPaiement;

    public Facture() {
    }

    public Facture(Long id, String numero, Double montantTotale, StatutPay statutPaiement) {
        this.id = id;
        this.numero = numero;
        this.montantTotale = montantTotale;
        this.statutPaiement = statutPaiement;
    }

    public Facture(Long id, String numero, Double montantTotale, StatutPay statutPaiement, Payment payment) {
        this.id = id;
        this.numero = numero;
        this.montantTotale = montantTotale;
        this.statutPaiement = statutPaiement;
     }





    public StatutPay getStatutPaiement() {
        return statutPaiement;
    }

    public void setStatutPaiement(StatutPay statutPaiement) {
        this.statutPaiement = statutPaiement;
    }

    public Double getMontantTotale() {
        return montantTotale;
    }

    public void setMontantTotale(Double montantTotale) {
        this.montantTotale = montantTotale;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
