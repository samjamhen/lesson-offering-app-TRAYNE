import { Component } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common'; // for *ngIf, *ngFor etc.
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [CommonModule, FormsModule, HttpClientModule], // ✅ put your modules here
  templateUrl: './login.component.html',
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  constructor(private http: HttpClient, private router: Router) {}

  login() {
    this.http.post<any>('http://localhost:8080/api/auth/login', {
      email: this.email,
      password: this.password
    }).subscribe({
      next: (res) => {
        // Store token and role in localStorage
        localStorage.setItem('token', res.token);
        localStorage.setItem('id', res.id);
        localStorage.setItem('email', res.email);
        localStorage.setItem('role', res.role); // client or instructor
  
        // Navigate to the home page
        this.router.navigate(['/home']);
      },
      error: (err) => {
        alert('Login failed');
        console.error(err);
      }
    });
  }
}
