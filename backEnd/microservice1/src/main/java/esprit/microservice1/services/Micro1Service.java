package esprit.microservice1.services;

import esprit.microservice1.entities.Micro1;
import esprit.microservice1.repositories.Micro1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Micro1Service {
    @Autowired
    private Micro1Repository candidatRepository;
    public List<Micro1> findAll() {
        return candidatRepository.findAll();
    }
}
