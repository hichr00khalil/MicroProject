package esprit.microservice3.services;


import esprit.microservice3.entities.Payment;
import esprit.microservice3.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentRestApi {

    @Autowired
    private PaymentService paymentService;

    // Create a Payment
    @PostMapping("/create")
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    // Get all Payments
    @GetMapping("/list")
    public List<Payment> list() {
        return paymentService.getAllPayments();
    }

    // Get a Payment by ID
    @GetMapping("/get/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    // Update a Payment
    @PutMapping("/update/{id}")
    public Payment updatePayment(@PathVariable Long id, @RequestBody Payment paymentDetails) {
        return paymentService.updatePayment(id, paymentDetails);
    }

    // Delete a Payment
    @DeleteMapping("/delete/{id}")
    public void deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }
}
