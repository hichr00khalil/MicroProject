package esprit.microservice1.services;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EtudiantRestApi {
    public String title="Hello Etudiant";

    @RequestMapping("/hello")
    public String sayHello(){
        return title;
    }

}
