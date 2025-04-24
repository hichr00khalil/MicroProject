package esprit.microservice4.repositories;

import esprit.microservice4.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    void deleteByFactureId(Long factureId);



}
