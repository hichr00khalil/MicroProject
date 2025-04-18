import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reclamation } from 'src/model/reclamation';
import { HttpClient } from '@angular/common/http';



@Injectable({
  providedIn: 'root'
})
export class ReclamationService {

 
  private apiUrl = 'http://localhost:8085/mic5'; 


  constructor(private http: HttpClient) { }

  createReclamation(reclamation: Reclamation): Observable<Reclamation> {
    return this.http.post<Reclamation>(`${this.apiUrl}/createReclamation`, reclamation);
  }

  // Récupérer toutes les réclamations
  getAllReclamations(): Observable<Reclamation[]> {
    return this.http.get<Reclamation[]>(`${this.apiUrl}/getAllReclamations`);
  }

  // Récupérer une réclamation par ID
  getReclamationById(id: number): Observable<Reclamation> {
    return this.http.get<Reclamation>(`${this.apiUrl}/getReclamation/${id}`);
  }

  // Supprimer une réclamation
  deleteReclamation(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/deleteReclamation/${id}`);
  }
}
