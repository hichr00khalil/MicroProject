package esprit.microservice1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mic1/")
public class RestaurantRestApi {


    @GetMapping("/hello")
    public String sayHello() {
        return "Hello I'm Microservice 1 : Restaurant ";
    }
}
