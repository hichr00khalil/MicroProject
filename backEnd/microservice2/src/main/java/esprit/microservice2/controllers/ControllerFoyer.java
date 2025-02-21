package esprit.microservice2.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import esprit.microservice2.entities.Foyer;
import esprit.microservice2.services.FoyerService;

@RestController
@RequestMapping("/foyers")
@RequiredArgsConstructor
public class ControllerFoyer {

    private final FoyerService foyerService;

    // Get all foyers
    @GetMapping
    public List<Foyer> getAllFoyers() {
        return foyerService.getAllFoyers();
    }

    // Get a foyer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Foyer> getFoyerById(@PathVariable Long id) {
        Optional<Foyer> foyer = foyerService.getFoyerById(id);
        return foyer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new foyer
    @PostMapping
    public ResponseEntity<Foyer> createFoyer(@RequestBody Foyer foyer) {
        Foyer createdFoyer = foyerService.createFoyer(foyer);
        return ResponseEntity.status(201).body(createdFoyer); // 201 Created status
    }

    // Delete a foyer by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFoyer(@PathVariable Long id) {
        if (foyerService.deleteFoyer(id)) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found if the foyer doesn't exist
        }
    }
}
