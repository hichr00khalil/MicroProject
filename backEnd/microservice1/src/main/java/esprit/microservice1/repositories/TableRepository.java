package esprit.microservice1.repositories;

import esprit.microservice1.entities.Ttable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Ttable,Long> {


}
