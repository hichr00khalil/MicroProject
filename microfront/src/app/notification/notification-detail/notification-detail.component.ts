import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Notification } from '../../model/notification';
import { NotificationService } from '../../services/notification.service';

@Component({
  selector: 'app-notification-detail',
  templateUrl: './notification-detail.component.html',
  styleUrls: ['./notification-detail.component.css']
})
export class NotificationDetailComponent implements OnInit {

  notification: Notification | null = null;
  loading = false;
  error = '';
  editMode = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private notificationService: NotificationService
  ) { }

  ngOnInit(): void {
    this.loadNotification();
  }

  loadNotification(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.loading = true;
      this.notificationService.getNotificationById(+id).subscribe({
        next: (data) => {
          this.notification = data;
          this.loading = false;
        },
        error: (err) => {
          this.error = 'Error loading notification';
          this.loading = false;
          console.error(err);
        }
      });
    }
  }

  toggleEditMode(): void {
    this.editMode = !this.editMode;
  }

  updateNotification(): void {
    if (!this.notification) return;
    
    this.loading = true;
    this.notificationService.updateNotification(this.notification.id!, this.notification).subscribe({
      next: (data) => {
        this.notification = data;
        this.loading = false;
        this.editMode = false;
      },
      error: (err) => {
        this.error = 'Error updating notification';
        this.loading = false;
        console.error(err);
      }
    });
  }

  markAsRead(): void {
    if (!this.notification || this.notification.isRead) return;
    
    this.loading = true;
    this.notificationService.markAsRead(this.notification.id!).subscribe({
      next: (data) => {
        this.notification = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Error marking notification as read';
        this.loading = false;
        console.error(err);
      }
    });
  }

  deleteNotification(): void {
    if (!this.notification) return;
    
    if (confirm('Are you sure you want to delete this notification?')) {
      this.loading = true;
      this.notificationService.deleteNotification(this.notification.id!).subscribe({
        next: () => {
          this.loading = false;
          this.router.navigate(['/notifications']);
        },
        error: (err) => {
          this.error = 'Error deleting notification';
          this.loading = false;
          console.error(err);
        }
      });
    }
  }
}
