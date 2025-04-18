import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError, BehaviorSubject } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Notification } from '../models/notification.model';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  // URL du backend
  private apiUrl = 'http://localhost:8086/notification';

  // URL alternative si le proxy est utilisé
  // private apiUrl = '/notification';

  // Subject pour stocker les notifications en cache
  private notificationsCache = new BehaviorSubject<Notification[]>([]);

  constructor(private http: HttpClient) { }

  // Ajouter une notification au cache local
  addToCache(notification: Notification) {
    const currentNotifications = this.notificationsCache.value;
    this.notificationsCache.next([...currentNotifications, notification]);
  }

  // Mettre à jour le cache avec les notifications du serveur
  updateCache(notifications: Notification[]) {
    this.notificationsCache.next(notifications);
  }

  // Headers for HTTP requests
  private getHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    });
  }

  // Error handling
  private handleError(error: HttpErrorResponse) {
    console.error('API Error:', error);

    // Vérifier si c'est une erreur de connexion (status 0)
    if (error.status === 0) {
      console.error('Connection error - Server unreachable');
      return throwError(() => new Error(`Connection error: Server unreachable`));
    }

    if (error.error instanceof ErrorEvent) {
      // Client-side error
      console.error('Client-side error:', error.error.message);
      return throwError(() => new Error(`Client-side error: ${error.error.message}`));
    } else {
      // Server-side error
      console.error('Server-side error:', error);
      return throwError(() => error);
    }
  }

  // Get all notifications
  getAllNotifications(): Observable<Notification[]> {
    return this.http.get<Notification[]>(`${this.apiUrl}/list`)
      .pipe(
        tap(response => {
          console.log('Get all notifications response:', response);
          this.updateCache(response);
        }),
        catchError(this.handleError)
      );
  }

  // Get cached notifications
  getCachedNotifications(): Observable<Notification[]> {
    return this.notificationsCache.asObservable();
  }

  // Get notification by ID
  getNotificationById(id: number): Observable<Notification> {
    return this.http.get<Notification>(`${this.apiUrl}/${id}`)
      .pipe(
        tap(response => console.log(`Get notification ${id} response:`, response)),
        catchError(this.handleError)
      );
  }

  // Get notifications by email (user)
  getNotificationsByEmail(email: string): Observable<Notification[]> {
    return this.http.get<Notification[]>(`${this.apiUrl}/user/${email}`)
      .pipe(
        tap(response => console.log(`Get notifications for ${email} response:`, response)),
        catchError(this.handleError)
      );
  }

  // Get unread notifications by email
  getUnreadNotificationsByEmail(email: string): Observable<Notification[]> {
    return this.http.get<Notification[]>(`${this.apiUrl}/user/${email}/unread`)
      .pipe(
        tap(response => console.log(`Get unread notifications for ${email} response:`, response)),
        catchError(this.handleError)
      );
  }

  // Get read notifications by email
  getReadNotificationsByEmail(email: string): Observable<Notification[]> {
    return this.http.get<Notification[]>(`${this.apiUrl}/user/${email}/read`)
      .pipe(
        tap(response => console.log(`Get read notifications for ${email} response:`, response)),
        catchError(this.handleError)
      );
  }

  // Create a new notification
  createNotification(notification: Notification): Observable<Notification> {
    console.log('Creating notification:', notification);
    return this.http.post<Notification>(`${this.apiUrl}/create`, notification, { headers: this.getHeaders() })
      .pipe(
        tap(response => {
          console.log('Create notification response:', response);
          this.addToCache(response);
        }),
        catchError(this.handleError)
      );
  }

  // Update a notification
  updateNotification(notification: Notification): Observable<Notification> {
    return this.http.put<Notification>(`${this.apiUrl}/update`, notification, { headers: this.getHeaders() })
      .pipe(
        tap(response => console.log('Update notification response:', response)),
        catchError(this.handleError)
      );
  }

  // Mark notification as read
  markAsRead(id: number): Observable<Notification> {
    return this.http.put<Notification>(`${this.apiUrl}/${id}/read`, {}, { headers: this.getHeaders() })
      .pipe(
        tap(response => console.log(`Mark notification ${id} as read response:`, response)),
        catchError(this.handleError)
      );
  }

  // Mark notification as unread
  markAsUnread(id: number): Observable<Notification> {
    return this.http.put<Notification>(`${this.apiUrl}/${id}/unread`, {}, { headers: this.getHeaders() })
      .pipe(
        tap(response => console.log(`Mark notification ${id} as unread response:`, response)),
        catchError(this.handleError)
      );
  }

  // Delete a notification
  deleteNotification(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, { headers: this.getHeaders() })
      .pipe(
        tap(() => console.log(`Delete notification ${id} successful`)),
        catchError(this.handleError)
      );
  }

  // Delete old notifications (older than specified days)
  deleteOldNotifications(days: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete-old/${days}`, { headers: this.getHeaders() })
      .pipe(
        tap(() => console.log(`Delete notifications older than ${days} days successful`)),
        catchError(this.handleError)
      );
  }

  // Envoyer un email pour une notification spécifique
  sendEmail(id: number): Observable<string> {
    // Simuler l'envoi d'un email sans faire d'appel au serveur
    console.log(`Simulating email sending for notification ${id}`);
    return new Observable<string>(observer => {
      setTimeout(() => {
        observer.next(`Email envoyé avec succès pour la notification ${id}`);
        observer.complete();
      }, 500);
    }).pipe(
      tap(response => console.log(`Send email for notification ${id} response:`, response))
    );
  }
}
