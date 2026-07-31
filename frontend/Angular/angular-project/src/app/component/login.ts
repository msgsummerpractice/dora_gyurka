import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'login',
  templateUrl: './login.html',
})
export class LoginComponent {
  private readonly router = inject(Router);

  onLoginSuccess(): void {
    this.router.navigate(['/home']);
  }
  
}
