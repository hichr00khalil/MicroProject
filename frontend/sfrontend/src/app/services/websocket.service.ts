import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, interval } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { Notification } from '../models/notification.model';
import { NotificationService } from './notification.service';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  private notificationsSubject: BehaviorSubject<Notification[]>;
  private pollingInterval = 5000; // 5 secondes
  private userEmail: string = '';

  constructor(private notificationService: NotificationService) {
    this.notificationsSubject = new BehaviorSubject<Notification[]>([]);
  }

  // Simuler une connexion WebSocket avec un polling
  initializeWebSocketConnection() {
    console.log('Initializing notification polling service');
    // Rien à faire ici, le polling est configuré lors de l'abonnement
  }

  // S'abonner aux notifications pour un utilisateur spécifique
  subscribeToUserNotifications(email: string) {
    if (!email) return;

    this.userEmail = email;
    console.log(`Setting up polling for notifications for user: ${email}`);

    // Charger les notifications immédiatement
    this.loadNotifications(email);

    // Configurer le polling pour les mises à jour régulières
    interval(this.pollingInterval).subscribe(() => {
      this.loadNotifications(email);
    });
  }

  // Charger les notifications depuis le serveur
  private loadNotifications(email: string) {
    this.notificationService.getNotificationsByEmail(email).subscribe({
      next: (notifications) => {
        console.log(`Received ${notifications.length} notifications for ${email}`);
        this.notificationsSubject.next(notifications);

        // Vérifier s'il y a de nouvelles notifications non lues
        const unreadNotifications = notifications.filter(n => !n.read);
        if (unreadNotifications.length > 0) {
          // Afficher une notification pour la première notification non lue
          this.showBrowserNotification(unreadNotifications[0]);
        }
      },
      error: (err) => {
        console.error('Error fetching notifications:', err);
      }
    });
  }

  // Afficher une notification dans le navigateur
  private showBrowserNotification(notification: Notification) {
    // Vérifier si les notifications du navigateur sont supportées
    if (!('Notification' in window)) {
      console.log('Browser notifications not supported');
      return;
    }

    // Vérifier si les permissions sont déjà accordées
    if (Notification.permission === 'granted') {
      this.createBrowserNotification(notification);
    } else if (Notification.permission !== 'denied') {
      // Demander la permission
      Notification.requestPermission().then(permission => {
        if (permission === 'granted') {
          this.createBrowserNotification(notification);
        }
      });
    }
  }

  // Créer une notification dans le navigateur
  private createBrowserNotification(notification: Notification) {
    try {
      new Notification('Nouvelle notification', {
        body: notification.message,
        icon: 'assets/notification-icon.png'
      });
    } catch (error) {
      console.error('Error creating browser notification:', error);
    }
  }

  // Observer les notifications
  getNotifications(): Observable<Notification[]> {
    return this.notificationsSubject.asObservable();
  }
}
