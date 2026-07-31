import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly authenticated = signal(false);

  login(): void {
    this.authenticated.set(true);
  }

  logout(): void {
    this.authenticated.set(false);
  }

  isAuthenticated(): boolean {
    return this.authenticated();
  }
}
