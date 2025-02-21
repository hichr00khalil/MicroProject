package esprit.microservice3.services;

import esprit.microservice3.entities.Chambre;
import esprit.microservice3.repositories.ChambreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChambreService {

    @Autowired
    private ChambreRepository chambreRepository;

    // Create
    public Chambre createChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    // Read
    public List<Chambre> getAllChambres() {
        return chambreRepository.findAll();
    }

    public Chambre getChambreById(Integer id) {
        return chambreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chambre not found with ID: " + id));
    }


    // Delete
    public void deleteChambre(Chambre chambre) {
        chambreRepository.delete(chambre);
    }

}
