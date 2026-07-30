import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})

export class AuthService {
    private authenticated = true;

    isAuthenticated(): boolean {
        return this.authenticated;
    }
}
