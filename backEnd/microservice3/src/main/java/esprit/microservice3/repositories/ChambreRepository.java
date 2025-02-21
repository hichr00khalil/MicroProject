package esprit.microservice3.repositories;

import esprit.microservice3.entities.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Integer> {
}
