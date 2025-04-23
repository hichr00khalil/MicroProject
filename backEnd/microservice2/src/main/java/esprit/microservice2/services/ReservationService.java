package esprit.microservice2.services;

import esprit.microservice2.Entity.Reservation;
import esprit.microservice2.reopsitory.ReservationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    public Reservation save(Reservation reservation){ return reservationRepository.save(reservation); }

    public List<Reservation> getAll(){
        return reservationRepository.findAll();
    }
}
