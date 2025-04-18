import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Notification } from '../../models/notification.model';
// import { QRCodeService } from '../../services/qrcode.service';

@Component({
  selector: 'app-notification-qrcode',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './notification-qrcode.component.html',
  styleUrls: ['./notification-qrcode.component.css']
})
export class NotificationQrcodeComponent implements OnChanges {
  @Input() notification!: Notification;

  qrCodeUrl: string = '';

  constructor() { }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['notification'] && this.notification) {
      this.generateQRCode();
    }
  }

  generateQRCode(): void {
    if (this.notification) {
      // Créer un objet JSON avec les informations de la notification
      const notificationData = {
        id: this.notification.id,
        message: this.notification.message,
        date: this.notification.date,
        email: this.notification.email,
        read: this.notification.read
      };

      // Convertir l'objet en chaîne JSON
      const jsonData = JSON.stringify(notificationData);

      // Générer l'URL du QR code
      const encodedData = encodeURIComponent(jsonData);
      this.qrCodeUrl = `https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=${encodedData}`;
    }
  }
}
