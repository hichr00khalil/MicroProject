package esprit.microservice1.services;

import esprit.microservice1.entities.Etudiant;
import esprit.microservice1.repositories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EtudiantService {

    public static void main(String[] args) {
        SpringApplication.run(EtudiantService.class, args);
    }
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Bean
    ApplicationRunner init(){
        return (args -> {
            etudiantRepository.save(new Etudiant("Khalil","yosser", 11408705, "nermine"));
            etudiantRepository.findAll();
        });
    }

}
