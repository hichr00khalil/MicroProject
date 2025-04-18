import { Component } from '@angular/core';
import { ReservationService } from '../services/reservation.service';
import { Reservation } from '../model/reservation';

@Component({
  selector: 'app-reservation',
  imports: [],
  templateUrl: './reservation.component.html',
  styleUrl: './reservation.component.css'
})
export class ReservationComponent {

  reservations: Reservation[] = [];

  newReservation: Reservation = {
    date: '2025-04-20',
    heure: '14:30:00',
    nbrPersonne: 4
  };

  constructor(private reservationService: ReservationService) {}

  ngOnInit() {
    this.getReservations();
  }

  getReservations() {
    this.reservationService.getAllReservations().subscribe(data => {
      this.reservations = data;
    });
  }

  addReservation() {
    this.reservationService.createReservation(this.newReservation).subscribe(res => {
      this.getReservations(); // Refresh the list
    });
  }

}
