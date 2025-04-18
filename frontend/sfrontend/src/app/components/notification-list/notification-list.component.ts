import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NotificationService } from '../../services/notification.service';
import { Notification } from '../../models/notification.model';
import { NotificationItemComponent } from '../notification-item/notification-item.component';
// import { NotificationDetailComponent } from '../notification-detail/notification-detail.component';

@Component({
  selector: 'app-notification-list',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule, NotificationItemComponent],
  templateUrl: './notification-list.component.html',
  styleUrls: ['./notification-list.component.css']
})
export class NotificationListComponent implements OnInit {
  notifications: Notification[] = [];
  userEmail: string = '';
  filterType: string = 'all'; // 'all', 'read', 'unread'
  loading: boolean = false;
  error: string | null = null;
  selectedNotification: Notification | null = null;

  constructor(private notificationService: NotificationService) { }

  ngOnInit(): void {
    this.loadAllNotifications();
  }

  loadAllNotifications(): void {
    this.loading = true;
    this.notificationService.getAllNotifications().subscribe({
      next: (data) => {
        this.notifications = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load notifications';
        this.loading = false;
        console.error(err);
      }
    });
  }

  loadUserNotifications(): void {
    if (!this.userEmail) {
      this.error = 'Please enter an email address';
      return;
    }

    this.loading = true;
    this.error = null;

    switch (this.filterType) {
      case 'read':
        this.notificationService.getReadNotificationsByEmail(this.userEmail).subscribe({
          next: (data) => {
            this.notifications = data;
            this.loading = false;
          },
          error: (err) => {
            this.error = 'Failed to load read notifications';
            this.loading = false;
            console.error(err);
          }
        });
        break;
      case 'unread':
        this.notificationService.getUnreadNotificationsByEmail(this.userEmail).subscribe({
          next: (data) => {
            this.notifications = data;
            this.loading = false;
          },
          error: (err) => {
            this.error = 'Failed to load unread notifications';
            this.loading = false;
            console.error(err);
          }
        });
        break;
      default:
        this.notificationService.getNotificationsByEmail(this.userEmail).subscribe({
          next: (data) => {
            this.notifications = data;
            this.loading = false;
          },
          error: (err) => {
            this.error = 'Failed to load notifications';
            this.loading = false;
            console.error(err);
          }
        });
        break;
    }
  }

  deleteNotification(id: number): void {
    if (confirm('Are you sure you want to delete this notification?')) {
      this.notificationService.deleteNotification(id).subscribe({
        next: () => {
          this.notifications = this.notifications.filter(n => n.id !== id);
          // Si la notification sélectionnée est supprimée, la désélectionner
          if (this.selectedNotification && this.selectedNotification.id === id) {
            this.selectedNotification = null;
          }
        },
        error: (err) => {
          this.error = 'Failed to delete notification';
          console.error(err);
        }
      });
    }
  }

  selectNotification(notification: Notification): void {
    this.selectedNotification = notification;
  }

  formatDate(date: Date): string {
    return new Date(date).toLocaleString();
  }

  markAsRead(id: number): void {
    this.notificationService.markAsRead(id).subscribe({
      next: (updatedNotification) => {
        const index = this.notifications.findIndex(n => n.id === id);
        if (index !== -1) {
          this.notifications[index] = updatedNotification;
        }
      },
      error: (err) => {
        this.error = 'Failed to mark notification as read';
        console.error(err);
      }
    });
  }

  markAsUnread(id: number): void {
    this.notificationService.markAsUnread(id).subscribe({
      next: (updatedNotification) => {
        const index = this.notifications.findIndex(n => n.id === id);
        if (index !== -1) {
          this.notifications[index] = updatedNotification;
        }
      },
      error: (err) => {
        this.error = 'Failed to mark notification as unread';
        console.error(err);
      }
    });
  }

  deleteOldNotifications(): void {
    if (confirm('Are you sure you want to delete notifications older than 30 days?')) {
      this.notificationService.deleteOldNotifications(30).subscribe({
        next: () => {
          this.loadAllNotifications();
        },
        error: (err) => {
          this.error = 'Failed to delete old notifications';
          console.error(err);
        }
      });
    }
  }
}
