package esprit.microservice1;

import esprit.microservice1.entities.Micro1;
import esprit.microservice1.repositories.Micro1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class Microservice1Application {

	public static void main(String[] args) {
		SpringApplication.run(Microservice1Application.class, args);
	}
    @Autowired
	private Micro1Repository micro1Repository;
	@Bean
	public ApplicationRunner init() {
		return (args) -> {
			micro1Repository.save(new Micro1("Badia","Bouhdid","badia@gmail.com"));
		};

	}
}
