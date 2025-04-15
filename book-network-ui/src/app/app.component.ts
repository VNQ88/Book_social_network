import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ButtonModule } from 'primeng/button';
import { LoginComponent } from './pages/login/login.component';
@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ButtonModule, FontAwesomeModule, LoginComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  title = 'book-network-ui';
}
