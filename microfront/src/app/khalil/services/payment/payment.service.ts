
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Facture } from '../../models/facture';
import { map, Observable } from 'rxjs';
import { Payment } from '../../models/payment';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  private apiUrl = 'http://localhost:8084/payment';

  constructor(private http: HttpClient) {}

  // Get all  
  getAll(): Observable<Payment[]> {
    return this.http.get<Payment[]>(this.apiUrl);  
  }
  

  // Get   by ID
  getById(id: number): Observable<Payment> {
    return this.http.get<Payment>(`${this.apiUrl}/${id}`);
  }


  create(payment: Payment): Observable<Payment> {
    return this.http.post<Payment>(this.apiUrl, payment);
  }


 
 
  // Delete  
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

 

  
}
