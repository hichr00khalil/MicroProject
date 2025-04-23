import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Table } from '../models/table.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TableService {
  private apiUrl = 'http://localhost:8087/mic1';
  constructor(private http: HttpClient) { }

  getAllTables(): Observable<Table[]> {
    return this.http.get<Table[]>(`${this.apiUrl}/getall`);
  }

  getTableById(id: number): Observable<Table> {
    return this.http.get<Table>(`${this.apiUrl}/gettable/${id}`);
  }

  createTable(table: Table): Observable<Table> {
    return this.http.post<Table>(`${this.apiUrl}/createtab`, table);
  }

  updateTable(id: number, table: Table): Observable<Table> {
    return this.http.put<Table>(`${this.apiUrl}/${id}`, table);
  }

  deleteTable(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
