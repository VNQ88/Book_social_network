import { Component } from '@angular/core';
import { AuthenticationRequest } from '../../services/models';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CardModule } from 'primeng/card';
import { MessagesModule } from 'primeng/messages';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/services';

@Component({
  selector: 'app-login',
  imports: [
    CommonModule,
    FontAwesomeModule,
    CardModule,
    MessagesModule,
    InputTextModule,
    ButtonModule,
    FormsModule,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  constructor(
    private router: Router,
    private authService: AuthenticationService
  ) {}
  login() {
    this.errorMessages = [];
    this.authService
      .authenticate({
        body: this.authRequest,
      })
      .subscribe({
        next: (response) => {
          // save the token
          this.router.navigate(['books']);
        },
        error: (error) => {
          console.log(error);
          if (error.error.validationErrors) {
            this.errorMessages = error.error.validationErrors;
          } else {
            this.errorMessages.push(error.error.errorMessages);
          }
        },
      });
  }
  register() {
    this.router.navigate(['register']);
  }
  authRequest: AuthenticationRequest = { email: '', password: '' };
  errorMessages: Array<string> = [];
}
