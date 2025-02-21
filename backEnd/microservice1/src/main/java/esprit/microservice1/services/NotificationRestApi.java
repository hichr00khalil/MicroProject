package esprit.microservice1.services;

import esprit.microservice1.entities.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationRestApi {
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World";
    }
    @Autowired
    private NotificationService notificationService;
    @GetMapping("/list")
    public List<Notification> list(){
        return notificationService.findAll();
    }
}
