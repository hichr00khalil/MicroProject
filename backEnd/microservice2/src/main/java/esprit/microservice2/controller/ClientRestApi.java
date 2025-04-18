package esprit.microservice2.controller;

import esprit.microservice2.Entity.Reservation;
import esprit.microservice2.services.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mic2/")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ClientRestApi {

    private final ReservationService reservationService;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello I'm Microservice 2 : Client ";
    }



    @PostMapping("/add")
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.save(reservation);
    }

    @GetMapping("/getAll")
    public List<Reservation> getAllReservations() {
        return reservationService.getAll();
    }
}
