package esprit.microservice4.repositories;

import esprit.microservice4.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture, Long> {

    void deleteById(Long id);



}
