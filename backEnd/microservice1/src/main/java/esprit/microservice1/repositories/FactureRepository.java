package esprit.microservice1.repositories;

import esprit.microservice1.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture, Long> {}
