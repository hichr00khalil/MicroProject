package esprit.microservice3.services;
import esprit.microservice3.entities.Chambre;
import esprit.microservice3.services.ChambreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChambreRestApi {
    public String title="Hello Etudiant";

    @RequestMapping("/hello")
    public String sayHello(){
        return title;
    }

}
