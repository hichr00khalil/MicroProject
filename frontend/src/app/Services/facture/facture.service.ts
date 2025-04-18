import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Facture } from 'src/app/models/Facture';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FactureService {
  private apiUrl = 'http://localhost:8082/factures';

  constructor(private http: HttpClient) {}

  // Get all factures
  getAll(): Observable<Facture[]> {
    return this.http.get<Facture[]>(this.apiUrl);  
  }
  

  // Get facture by ID
  getById(id: number): Observable<Facture> {
    return this.http.get<Facture>(`${this.apiUrl}/${id}`);
  }

  // Create new facture
  create(facture: Facture): Observable<Facture> {
    return this.http.post<Facture>(this.apiUrl, facture);
  }

  // Update facture
  update(id: number, facture: Facture): Observable<Facture> {
    return this.http.put<Facture>(`${this.apiUrl}/${id}`, facture);
  }

  // Delete facture
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  // Send facture confirmation email with full object
  sendEmail(facture: Facture): Observable<string> {
    return this.http.post(`${this.apiUrl}/mail`, facture, { responseType: 'text' });
  }


  
}
