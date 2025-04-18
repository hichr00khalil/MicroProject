import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Notification } from '../../models/notification.model';
import { NotificationQrcodeComponent } from '../notification-qrcode/notification-qrcode.component';

@Component({
  selector: 'app-notification-detail',
  standalone: true,
  imports: [CommonModule, RouterModule, NotificationQrcodeComponent],
  templateUrl: './notification-detail.component.html',
  styleUrls: ['./notification-detail.component.css']
})
export class NotificationDetailComponent {
  @Input() notification!: Notification;
  showQRCode: boolean = false;

  toggleQRCode(): void {
    this.showQRCode = !this.showQRCode;
  }

  formatDate(date: Date): string {
    return new Date(date).toLocaleString();
  }
}