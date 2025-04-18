package esprit.microservice1.services;

import esprit.microservice1.entities.Ttable;
import esprit.microservice1.repositories.TableRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class TableService implements ITableService{



    private TableRepository repository;

    public List<Ttable> getAllTables() {
        return repository.findAll();
    }

    public Ttable getTableById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table introuvable avec l'id: " + id));
    }

    public Ttable createTable(Ttable table) {
        return repository.save(table);
    }


    public void deleteTable(Long id) {
        repository.deleteById(id);
    }
}
