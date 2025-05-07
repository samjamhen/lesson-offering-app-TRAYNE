import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common'; // for *ngIf, *ngFor etc.
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-signup',
  imports: [CommonModule, FormsModule, HttpClientModule],
  standalone: true,
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {
  fullName = '';
  phoneNumber = '';
  email = '';
  password = '';
  userType: 'client' | 'instructor' = 'client'; // default

  constructor(private http: HttpClient, private router: Router) {}

  signup() {
    const userData = {
      fullName: this.fullName,
      phoneNumber: this.phoneNumber,
      email: this.email,
      password: this.password,
    };

    const endpoint =
      this.userType === 'client'
        ? 'http://localhost:8080/client/add'
        : 'http://localhost:8080/instructor/add';

    this.http.post(endpoint, userData).subscribe({
      next: () => {
        alert('Account created successfully!');
        this.router.navigate(['/login']);
      },
      error: err => {
        alert('Failed to create account.');
        console.error(err);
      }
    });
  }
}
