import { Component, OnInit } from '@angular/core';
import { Reservation } from '../model/reservation';
import { ReservationService } from '../services/reservation.service';
import { NotificationService } from '../services/notification.service';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  reservations: Reservation[] = [];
  successMessage: string = '';
  errorMessage: string = '';

  newReservation: Reservation = {
    date: '2025-04-20',
    heure: '14:30:00',
    nbrPersonne: 4
  };

  constructor(
    private reservationService: ReservationService,
    private notificationService: NotificationService
  ) {}

  ngOnInit() {
    this.getReservations();
  }

  getReservations() {
    this.reservationService.getAllReservations().subscribe({
      next: (data) => {
        this.reservations = data;
      },
      error: (err) => {
        this.errorMessage = 'Error loading reservations';
        console.error(err);
      }
    });
  }

  addReservation() {
    this.successMessage = '';
    this.errorMessage = '';

    this.reservationService.createReservation(this.newReservation).subscribe({
      next: (res) => {
        this.getReservations(); // Refresh the list
        this.successMessage = 'Reservation created successfully!';

        // Create a notification for the new reservation
        if (res && res.id) {
          this.createNotificationForReservation(res.id);
        }

        // Reset form
        this.newReservation = {
          date: '',
          heure: '',
          nbrPersonne: 1
        };
      },
      error: (err) => {
        this.errorMessage = 'Error creating reservation';
        console.error(err);
      }
    });
  }

  createNotificationForReservation(reservationId: number) {
    this.notificationService.createReservationNotification(reservationId).subscribe({
      next: (notification) => {
        console.log('Notification created for reservation ID:', reservationId);
        // You could show a toast or other UI indication that a notification was created
      },
      error: (err) => {
        console.error('Error creating notification:', err);
        // Optionally show an error message to the user
        // this.errorMessage = 'Error creating notification';
      }
    });
  }
}
