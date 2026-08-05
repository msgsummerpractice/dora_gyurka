import { Component, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterLink } from '@angular/router';
import {
  ReactiveFormsModule,
  NonNullableFormBuilder,
  FormControl,
  Validators,
} from '@angular/forms';
import { AuthService } from '../service/auth-service';

type UserForm = {
  username: FormControl<string>;
  password: FormControl<string>;
  otp: FormControl<string>;
};

@Component({
  selector: 'app-login-form',
  imports: [ReactiveFormsModule, MatButtonModule, MatIconModule, MatToolbarModule, RouterLink],
  templateUrl: './login-form.html',
  styleUrl: './login-form.css',
})
export class LoginForm {
  private readonly form = inject(NonNullableFormBuilder);
  private readonly authService = inject(AuthService);
  showOtp = false;

  protected readonly userFormGroup = this.form.group<UserForm>({
    username: this.form.control('', Validators.required),
    password: this.form.control('', Validators.required),
    otp: this.form.control(''),
  });

  login(username: string, password: string): void {
    this.authService.login(username, password).subscribe({
      next: (response) => {
        this.authService.setUsername(username);
      },
      error: (error) => {
        console.error('Login failed:', error);
      },
    });
  }

  verify(): void {
    const { username, otp } = this.userFormGroup.getRawValue();

    console.log('Verifying OTP for username:', username, 'with OTP:', otp);

    this.authService.verifyOtp(username, otp).subscribe({
      next: (response) => {
        this.authService.setToken(response.accessToken);
        console.log('OTP verification successful. Token:', response.accessToken);
      },
      error: (error) => {
        console.error('OTP verification failed:', error);
      },
    });
  }

  onSubmitForm(): void {
    if (this.userFormGroup.invalid) {
      return;
    }
    const { username, password } = this.userFormGroup.getRawValue();
    console.log('Form submitted with username:', username, 'and password:', password);
    this.authService.login(username, password).subscribe({
      next: (response) => {
        this.showOtp = true;
        console.log('Login successful. Proceed to OTP verification.');
        this.authService.setUsername(username);
        console.log('Login successful. Proceed to OTP verification.');
      },
      error: (error) => {
        console.error('Login failed:', error);
      },
    });
  }
}
