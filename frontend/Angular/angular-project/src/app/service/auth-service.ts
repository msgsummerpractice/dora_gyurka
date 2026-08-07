import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { inject } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly authenticated = signal(true);
  private readonly api = 'https://dorabackend.internal.salmonflower-bd9abe31.westeurope.azurecontainerapps.io/api/auth';
  private readonly http = inject(HttpClient);
  private username = signal<string | null>(null);
  private password = signal<string | null>(null);
  private showOtp = signal(false);
  private token = signal<string | null>(null);

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
    this.username.set(username);
    localStorage.setItem('username', username);
  }

  setPassword(password: string): void {
    this.password.set(password);
  }

  setToken(token: string): void {
    this.token.set(token);
  }
  getToken(): string | null {
    return this.token();
  }
}
