import { Component } from '@angular/core';
import { Reservation } from '../model/reservation';
import { ReservationService } from '../services/reservation.service';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
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
