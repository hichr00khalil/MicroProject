import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { NotificationService } from '../../services/notification.service';
// import { QRCodeService } from '../../services/qrcode.service';
import { Notification } from '../../models/notification.model';

@Component({
  selector: 'app-notification-qr-page',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './notification-qr-page.component.html',
  styleUrls: ['./notification-qr-page.component.css']
})
export class NotificationQrPageComponent implements OnInit {
  notification: Notification | null = null;
  qrCodeUrl: string = '';
  loading: boolean = true;
  error: string | null = null;

  constructor(
    private route: ActivatedRoute,
    private notificationService: NotificationService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.loadNotification(parseInt(id, 10));
      } else {
        this.error = 'Notification ID not provided';
        this.loading = false;
      }
    });
  }

  loadNotification(id: number): void {
    this.notificationService.getNotificationById(id).subscribe({
      next: (notification) => {
        this.notification = notification;
        this.generateQRCode();
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load notification';
        this.loading = false;
        console.error(err);
      }
    });
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

      // Générer l'URL du QR code avec une taille plus grande
      const encodedData = encodeURIComponent(jsonData);
      this.qrCodeUrl = `https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=${encodedData}`;
    }
  }

  formatDate(date: Date): string {
    return new Date(date).toLocaleString();
  }
}
