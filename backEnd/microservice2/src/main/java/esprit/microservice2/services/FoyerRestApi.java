package esprit.microservice2.services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoyerRestApi {
    public String title="Hello Etudiant";

    @RequestMapping("/hello")
    public String sayHello(){
        return title;
    }

}
