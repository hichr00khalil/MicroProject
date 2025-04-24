import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../../services/notification.service';
import { interval } from 'rxjs';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  unreadCount = 0;

  constructor(private notificationService: NotificationService) { }

  ngOnInit(): void {
    // Initial load
    this.loadUnreadCount();

    // Refresh unread count every 30 seconds
    interval(30000)
      .pipe(
        switchMap(() => this.notificationService.getUnreadNotifications())
      )
      .subscribe({
        next: (notifications) => {
          this.unreadCount = notifications.length;
        },
        error: (err) => {
          console.error('Error fetching unread notifications:', err);
        }
      });
  }

  loadUnreadCount(): void {
    this.notificationService.getUnreadNotifications().subscribe({
      next: (notifications) => {
        this.unreadCount = notifications.length;
      },
      error: (err) => {
        console.error('Error fetching unread notifications:', err);
      }
    });
  }
}
