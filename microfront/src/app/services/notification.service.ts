import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Notification } from '../model/notification';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  private baseUrl = 'http://localhost:8086/mic6/notifications'; // Adjust port if different

  constructor(private http: HttpClient) { }

  // Get all notifications
  getAllNotifications(): Observable<Notification[]> {
    return this.http.get<Notification[]>(`${this.baseUrl}`);
  }

  // Get notification by ID
  getNotificationById(id: number): Observable<Notification> {
    return this.http.get<Notification>(`${this.baseUrl}/${id}`);
  }

  // Get notifications by reservation ID
  getNotificationsByReservationId(reservationId: number): Observable<Notification[]> {
    return this.http.get<Notification[]>(`${this.baseUrl}/reservation/${reservationId}`);
  }

  // Get unread notifications
  getUnreadNotifications(): Observable<Notification[]> {
    return this.http.get<Notification[]>(`${this.baseUrl}/unread`);
  }

  // Create a new notification
  createNotification(notification: Notification): Observable<Notification> {
    return this.http.post<Notification>(`${this.baseUrl}`, notification);
  }

  // Create a notification for a reservation
  createReservationNotification(reservationId: number): Observable<Notification> {
    return this.http.post<Notification>(`${this.baseUrl}/reservation/${reservationId}`, {});
  }

  // Update a notification
  updateNotification(id: number, notification: Notification): Observable<Notification> {
    return this.http.put<Notification>(`${this.baseUrl}/${id}`, notification);
  }

  // Mark notification as read
  markAsRead(id: number): Observable<Notification> {
    return this.http.put<Notification>(`${this.baseUrl}/${id}/read`, {});
  }

  // Delete a notification
  deleteNotification(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
