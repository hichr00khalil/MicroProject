package esprit.microservice2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mic2/")
public class ClientRestApi {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello I'm Microservice 2 : Client ";
    }
}
