package esprit.microservice1;

import esprit.microservice1.repositories.CandidatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Microservice1Application {

	public static void main(String[] args) {
		SpringApplication.run(Microservice1Application.class, args);
	}
    @Autowired
	private CandidatRepo candidatRepo;
	@Bean
	public Candidat addCandidat(){
		return  this.candidatRepo.save(new Candidat("badia","bouhdid","badia@gmail.com"));

	}
}
