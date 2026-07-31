import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { RouterLink } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';

@Component({
  selector: 'login',
  imports: [RouterLink, MatButtonModule, MatIconModule, MatToolbarModule],
  templateUrl: './login.html',
})
export class LoginComponent {
  // private readonly router = inject(Router);

  // onLoginSuccess(): void {
  //   this.router.navigate(['/home']);
  // }
}
