import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotificationService } from '../../services/notification.service';
import { Notification } from '../../models/notification.model';

@Component({
  selector: 'app-notification-item',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './notification-item.component.html',
  styleUrls: ['./notification-item.component.css']
})
export class NotificationItemComponent {
  @Input() notification!: Notification;
  @Output() delete = new EventEmitter<number>();
  @Output() markRead = new EventEmitter<number>();
  @Output() markUnread = new EventEmitter<number>();

  constructor(private notificationService: NotificationService) {}

  onDelete(event: MouseEvent): void {
    event.stopPropagation(); // Empêcher la propagation du clic
    this.delete.emit(this.notification.id);
  }

  onMarkAsRead(event: MouseEvent): void {
    event.stopPropagation(); // Empêcher la propagation du clic
    this.markRead.emit(this.notification.id);
  }

  onMarkAsUnread(event: MouseEvent): void {
    event.stopPropagation(); // Empêcher la propagation du clic
    this.markUnread.emit(this.notification.id);
  }

  formatDate(date: Date): string {
    return new Date(date).toLocaleString();
  }

  onSendEmail(event: MouseEvent): void {
    event.stopPropagation(); // Empêcher la propagation du clic
    if (this.notification && this.notification.id) {
      this.notificationService.sendEmail(this.notification.id).subscribe({
        next: (response) => {
          alert('Email envoyé avec succès!');
          console.log('Email sent successfully:', response);
        },
        error: (error) => {
          alert('Erreur lors de l\'envoi de l\'email. Consultez la console pour plus de détails.');
          console.error('Error sending email:', error);
        }
      });
    }
  }
}
