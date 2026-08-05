import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { inject } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly authenticated = signal(true);
  private readonly api = 'http://localhost:8080/api/auth';
  private readonly http = inject(HttpClient);
  private username: string | null = null;
  private password: string | null = null;
  private showOtp = false;
  private token: string | null = null;

  login(username: string, password: string) {
    return this.http.post(`${this.api}/login`, { username, password });
  }

  verifyOtp(username: string, otp: string): Observable<{ accessToken: string }> {
    return this.http.post<{ accessToken: string }>(`${this.api}/verify-otp`, { username, otp });
  }
  logout(): void {
    this.authenticated.set(false);
  }

  isAuthenticated(): boolean {
    return this.authenticated();
  }

  setUsername(username: string): void {
    this.username = username;
    localStorage.setItem('username', username);
  }

  setPassword(password: string): void {
    this.password = password;
  }

  setToken(token: string): void {
    this.token = token;
  }
  getToken(): string | null {
    return this.token;
  }
}
