import { Component } from '@angular/core';
import { Notification } from '../../model/notification';
import { NotificationService } from '../../services/notification.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-notification-create',
  templateUrl: './notification-create.component.html',
  styleUrls: ['./notification-create.component.css']
})
export class NotificationCreateComponent {

  notification: Notification = {
    message: '',
    isRead: false
  };

  loading = false;
  error = '';
  success = '';

  constructor(
    private notificationService: NotificationService,
    private router: Router
  ) { }

  createNotification(): void {
    this.loading = true;
    this.error = '';
    this.success = '';

    this.notificationService.createNotification(this.notification).subscribe({
      next: () => {
        this.loading = false;
        this.success = 'Notification created successfully!';
        // Reset form
        this.notification = {
          message: '',
          isRead: false
        };
        // Navigate to notification list after a short delay
        setTimeout(() => {
          this.router.navigate(['/notifications']);
        }, 2000);
      },
      error: (err) => {
        this.loading = false;
        this.error = 'Error creating notification';
        console.error(err);
      }
    });
  }
}
