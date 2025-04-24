import { Component, OnInit } from '@angular/core';
import { Notification } from '../../model/notification';
import { NotificationService } from '../../services/notification.service';

@Component({
  selector: 'app-notification-list',
  templateUrl: './notification-list.component.html',
  styleUrls: ['./notification-list.component.css']
})
export class NotificationListComponent implements OnInit {

  notifications: Notification[] = [];
  loading = false;
  error = '';

  constructor(private notificationService: NotificationService) { }

  ngOnInit(): void {
    this.loadNotifications();
  }

  loadNotifications(): void {
    this.loading = true;
    this.notificationService.getAllNotifications().subscribe({
      next: (data) => {
        this.notifications = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Error loading notifications';
        this.loading = false;
        console.error(err);
      }
    });
  }

  markAsRead(id: number): void {
    this.notificationService.markAsRead(id).subscribe({
      next: () => {
        // Update the notification in the list
        const notification = this.notifications.find(n => n.id === id);
        if (notification) {
          notification.isRead = true;
        }
      },
      error: (err) => {
        this.error = 'Error marking notification as read';
        console.error(err);
      }
    });
  }

  deleteNotification(id: number): void {
    this.notificationService.deleteNotification(id).subscribe({
      next: () => {
        // Remove the notification from the list
        this.notifications = this.notifications.filter(n => n.id !== id);
      },
      error: (err) => {
        this.error = 'Error deleting notification';
        console.error(err);
      }
    });
  }
}
