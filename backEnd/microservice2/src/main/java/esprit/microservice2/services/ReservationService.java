package esprit.microservice2.services;

import esprit.microservice2.Entity.Reservation;
import esprit.microservice2.reopsitory.ReservationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RestTemplate restTemplate;

    // Constructor with all required dependencies
    public ReservationService(ReservationRepository reservationRepository, RestTemplate restTemplate) {
        this.reservationRepository = reservationRepository;
        this.restTemplate = restTemplate;
    }

    public Reservation save(Reservation reservation) {
        // Save the reservation
        Reservation savedReservation = reservationRepository.save(reservation);

        // Notify microservice6 about the new reservation
        try {
            restTemplate.postForEntity(
                "http://localhost:8086/mic6/events/reservation-created/" + savedReservation.getId(),
                null,
                Object.class
            );
            log.info("Notification sent for reservation ID: {}", savedReservation.getId());
        } catch (Exception e) {
            log.error("Failed to send notification for reservation: {}", e.getMessage());
        }

        return savedReservation;
    }

    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }
}
