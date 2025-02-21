package esprit.microservice2.services;

import esprit.microservice2.entities.Foyer;
import esprit.microservice2.repositories.FoyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoyerService {

    @Autowired
    private FoyerRepository foyerRepository;

    // Create a Foyer
    public Foyer createFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }

    // Get all Foyers
    public List<Foyer> getAllFoyers() {
        return foyerRepository.findAll();
    }

    // Get a Foyer by ID
    public Optional<Foyer> getFoyerById(Long id) {
        return foyerRepository.findById(id);
    }

    // Delete a Foyer by ID
    public boolean deleteFoyer(Long id) {
        Optional<Foyer> foyer = foyerRepository.findById(id);
        if (foyer.isPresent()) {
            foyerRepository.delete(foyer.get());
            return true;
        }
        return false;
    }
}
