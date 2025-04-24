package esprit.microservice4.controller;
import esprit.microservice4.entities.Facture;
import esprit.microservice4.entities.Payment;
import esprit.microservice4.entities.StatutPay;
import esprit.microservice4.repositories.FactureRepository;
import esprit.microservice4.repositories.PaymentRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private FactureRepository factureRepository;

    // CREATE

    @PostMapping
    public ResponseEntity<?> createPayment(@Valid @RequestBody Payment payment) {
        if (payment.getFacture() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Facture is missing in the payment request."));
        }

        Optional<Facture> factureOpt = factureRepository.findById(payment.getFacture().getId());

        if (factureOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Facture not found."));
        }

        // Update payment status and associate the facture with payment
        Facture facture = factureOpt.get();
        facture.setStatutPaiement(StatutPay.PAYE);

        // Save facture after updating the payment status
        factureRepository.save(facture);

        // Set the facture in the payment object
        payment.setFacture(facture);

        // Save the payment entity
        paymentRepository.save(payment);

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
                        // Update the payment status of the facture if it exists
                        facture.setStatutPaiement(StatutPay.NON_PAYE);
                        factureRepository.save(facture); // Save the updated facture
                    }

                    paymentRepository.delete(payment); // Delete the payment
                    return ResponseEntity.noContent().build(); // Return success response
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Payment not found"))); // Return error if payment not found
    }



    @Transactional
    public void deleteFacture(Long factureId) {
        factureRepository.deleteById(factureId);
    }

}


