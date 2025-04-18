import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { NotificationService } from '../../services/notification.service';
import { WebSocketService } from '../../services/websocket.service';
import { Notification } from '../../models/notification.model';

@Component({
  selector: 'app-notification-create',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './notification-create.component.html',
  styleUrls: ['./notification-create.component.css']
})
export class NotificationCreateComponent {
  notification: Partial<Notification> = {
    message: '',
    email: '',
    read: false,
    date: new Date()
    // Les champs id et createdAt seront gérés par le backend
  };

  loading: boolean = false;
  error: string | null = null;
  success: string | null = null;

  constructor(
    private notificationService: NotificationService,
    private webSocketService: WebSocketService,
    private router: Router
  ) { }

  createNotification(): void {
    if (!this.notification.message || !this.notification.email) {
      this.error = 'Please fill in all required fields';
      return;
    }

    this.loading = true;
    this.error = null;
    this.success = null;

    // Créer un objet simplifié pour l'envoi au backend
    const notificationToSend = {
      message: this.notification.message,
      email: this.notification.email,
      read: this.notification.read,
      date: this.notification.date
    };

    // Créer la notification via le service HTTP
    this.notificationService.createNotification(notificationToSend as Notification).subscribe({
      next: () => {
        this.loading = false;
        this.success = 'Notification created successfully';

        // Reset form
        this.notification = {
          message: '',
          email: '',
          read: false,
          date: new Date()
        };

        // Navigate back to list after 2 seconds
        setTimeout(() => {
          this.router.navigate(['/notifications']);
        }, 2000);
      },
      error: (err) => {
        this.loading = false;

        // Gérer spécifiquement les erreurs de connexion (code 0)
        if (err.status === 0) {
          this.error = 'Connection error: Cannot reach the server. Please make sure the backend is running.';
        } else {
          this.error = `Failed to create notification: ${err.message || 'Unknown error'}`;
        }

        console.error('Create notification error:', err);
        console.log('Error status:', err.status);
        console.log('Error message:', err.message);

        // Log the notification object for debugging
        console.log('Notification object that failed:', JSON.stringify(this.notification));
      }
    });
  }
}
