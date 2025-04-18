package esprit.microservice1.controller;
import esprit.microservice1.entities.Facture;
import esprit.microservice1.entities.StatutPay;
import esprit.microservice1.repositories.FactureRepository;
import esprit.microservice1.services.MailCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/factures")
public class FactureController {

    @Autowired
    private FactureRepository factureRepository;


    @Autowired
    private MailCheckService emailService;


    // CREATE
    @PostMapping
    public ResponseEntity<?> createFacture(@RequestBody Facture facture) {
        // Save and return the created facture
        return ResponseEntity.status(HttpStatus.CREATED).body(factureRepository.save(facture));
    }

    // READ all
    @GetMapping
    public ResponseEntity<List<Facture>> getAllFactures() {

        System.out.println("CORS headers are being sent");

        return ResponseEntity.ok(factureRepository.findAll());
    }

    // READ by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getFactureById(@PathVariable Long id) {
        Optional<Facture> factureOpt = factureRepository.findById(id);
        if (factureOpt.isPresent()) {
            return ResponseEntity.ok(factureOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Facture not found"));
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFacture(@PathVariable Long id, @RequestBody Facture updatedFacture) {
        Optional<Facture> factureOpt = factureRepository.findById(id);
        if (factureOpt.isPresent()) {
            Facture facture = factureOpt.get();
            facture.setNumero(updatedFacture.getNumero());
            facture.setMontantTotale(updatedFacture.getMontantTotale());
            facture.setStatutPaiement(updatedFacture.getStatutPaiement());

            // Save the updated facture
            return ResponseEntity.ok(factureRepository.save(facture));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Facture not found"));
        }
    }


    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFacture(@PathVariable Long id) {
        Optional<Facture> factureOpt = factureRepository.findById(id);
        if (factureOpt.isPresent()) {
            factureRepository.delete(factureOpt.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Facture not found"));
        }
    }




    @PostMapping("/mail")
    public String sendFactureConfirmation(@RequestBody Facture facture) {
        try {
            sendConfirmationEmail(facture);
            return "Email sent successfully.";
        } catch (Exception e) {
            return "Error while sending email: " + e.getMessage();
        }
    }


    @PostMapping("/maill")
    public String sendFactureConfirmation(@RequestParam String numero, @RequestParam Double montantTotale) {
        try {
            Facture facture = new Facture();
            facture.setNumero(numero);
            facture.setMontantTotale(montantTotale);
            facture.setStatutPaiement(StatutPay.PAYE);  // You can set this statically or as another parameter if needed
            sendConfirmationEmail(facture);
            return "Email sent successfully.";
        } catch (Exception e) {
            return "Error while sending email: " + e.getMessage();
        }
    }





    private void sendConfirmationEmail(Facture facture) {

            String toEmail = "hichrikhalilo06@gmail.com";
            String subject = "Confirmation of your facture";
            String body = "Hello khalil hichri ,\n " +
                    "Your facture "+ facture.getNumero() + "  is now ready. \n The total amount of : " + facture.getMontantTotale()
                    + " DT has been processed successfully. \n Thank you for your business.\n";
            emailService.sendMail(toEmail, subject, body);

    }



}
