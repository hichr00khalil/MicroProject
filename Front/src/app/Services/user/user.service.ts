import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Company } from 'src/app/models/Company';
import { Student } from 'src/app/models/Student';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = 'http://localhost:9091/user';


  private clientId = 'Ov23limpyRruv12hNEAT';
  private redirectUri = 'http://localhost:4200/postulations';
  private backendTokenUrl = 'http://localhost:9091/user/token';
  private githubUserUrl = 'https://api.github.com/user';
  constructor(private http: HttpClient) { }


    getCompanyById(id: number): Observable<Company> {
       return this.http.get<Company>(`${this.baseUrl}/company/${id}`);
    }
    
  
    getStudentById(id: number): Observable<Student> {
      return this.http.get<Student>(`${this.baseUrl}/student/${id}`);
   }
   




   redirectToGitHubLogin() {
    const githubAuthUrl = 'https://github.com/login/oauth/authorize';
    const scope = 'read:user user:email';

    window.location.href = `${githubAuthUrl}?client_id=${this.clientId}&redirect_uri=${this.redirectUri}&scope=${scope}`;
  }

  exchangeCodeForToken(code: string) {
    return this.http.get<{ access_token: string }>(this.backendTokenUrl, {
      params: new HttpParams().set('code', code),
    });
  }

  fetchGitHubUser(token: string) {
    const headers = { Authorization: `Bearer ${token}` };
    return this.http.get(this.githubUserUrl, { headers });
  }
  

}
