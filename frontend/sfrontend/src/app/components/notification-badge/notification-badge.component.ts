import { Component, Input, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Subscription } from 'rxjs';
import { NotificationService } from '../../services/notification.service';
import { WebSocketService } from '../../services/websocket.service';

@Component({
  selector: 'app-notification-badge',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './notification-badge.component.html',
  styleUrls: ['./notification-badge.component.css']
})
export class NotificationBadgeComponent implements OnInit, OnDestroy {
  @Input() email: string = '';
  unreadCount: number = 0;
  private subscription?: Subscription;

  constructor(
    private notificationService: NotificationService,
    private webSocketService: WebSocketService
  ) { }

  ngOnInit(): void {
    if (this.email) {
      this.loadUnreadCount();

      // S'abonner aux notifications pour cet utilisateur
      this.webSocketService.subscribeToUserNotifications(this.email);

      // S'abonner aux mises à jour des notifications
      this.subscription = this.webSocketService.getNotifications().subscribe(notifications => {
        // Filtrer les notifications non lues pour cet utilisateur
        const unreadNotifications = notifications.filter(n => !n.read && n.email === this.email);
        this.unreadCount = unreadNotifications.length;
      });
    }
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  loadUnreadCount(): void {
    this.notificationService.getUnreadNotificationsByEmail(this.email).subscribe({
      next: (notifications) => {
        this.unreadCount = notifications.length;
      },
      error: (err) => {
        console.error('Failed to load unread notifications count', err);
      }
    });
  }
}
