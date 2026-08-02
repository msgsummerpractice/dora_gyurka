import { Component, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterLink } from '@angular/router';
import {ReactiveFormsModule,NonNullableFormBuilder,FormControl,Validators} from '@angular/forms';

type UserForm = {
  email: FormControl<string>;
  password: FormControl<string>;
};

@Component({
  selector: 'app-login-form',
  imports: [ReactiveFormsModule,MatButtonModule,MatIconModule, MatToolbarModule,RouterLink],
  templateUrl: './login-form.html',
  styleUrl: './login-form.css',
})
export class LoginForm {
  private readonly form = inject(NonNullableFormBuilder);

  protected readonly userFormGroup = this.form.group<UserForm>({
    email: this.form.control('', Validators.email),
    password: this.form.control('', Validators.required),
  });

  onSubmitForm(): void {
    if (this.userFormGroup.valid) {
      console.log('getRawValue():', this.userFormGroup.getRawValue());
    }
  }
}
