package esprit.microservice1.controller;
import esprit.microservice1.entities.Facture;
import esprit.microservice1.entities.Payment;
import esprit.microservice1.entities.StatutPay;
import esprit.microservice1.repositories.FactureRepository;
import esprit.microservice1.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("payment/")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private FactureRepository factureRepository;

    // CREATE
    @PostMapping
    public ResponseEntity<?> createPayment(@RequestBody Payment payment) {
        Optional<Facture> factureOpt = factureRepository.findById(payment.getFacture().getId());

        if (factureOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Facture not found."));
        }

        Facture facture = factureOpt.get();
        facture.setStatutPaiement(StatutPay.PAYE);

        payment.setFacture(facture);
        facture.setPayment(payment);

        factureRepository.save(facture); // Cascade will save payment too

        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }

    // READ all
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentRepository.findAll());
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable Long id) {
        Optional<Payment> paymentOpt = paymentRepository.findById(id);

        if (paymentOpt.isPresent()) {
            return ResponseEntity.ok(paymentOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Payment not found"));
        }
    }



    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePayment(@PathVariable Long id, @RequestBody Payment updatedPayment) {
        return paymentRepository.findById(id)
                .map(payment -> {
                    payment.setPaymentDate(updatedPayment.getPaymentDate());
                    payment.setPaymentMethod(updatedPayment.getPaymentMethod());

                    if (updatedPayment.getFacture() != null) {
                        Optional<Facture> factureOpt = factureRepository.findById(updatedPayment.getFacture().getId());
                        if (factureOpt.isEmpty()) {
                            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                    .body(Map.of("error", "Facture not found."));
                        }

                        Facture facture = factureOpt.get();
                        facture.setStatutPaiement(StatutPay.PAYE);
                        payment.setFacture(facture);
                        facture.setPayment(payment);

                        factureRepository.save(facture);
                    }

                    paymentRepository.save(payment);
                    return ResponseEntity.ok(payment);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Payment not found")));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable Long id) {
        return paymentRepository.findById(id)
                .map(payment -> {
                    Facture facture = payment.getFacture();
                    if (facture != null) {
                        facture.setPayment(null);
                        facture.setStatutPaiement(StatutPay.NON_PAYE);
                        factureRepository.save(facture);
                    }

                    paymentRepository.delete(payment);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Payment not found")));
    }
}


