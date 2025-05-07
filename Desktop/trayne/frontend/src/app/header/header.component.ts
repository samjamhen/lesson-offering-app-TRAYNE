import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router'; // ✅ import this


@Component({
  selector: 'app-header',
  imports: [CommonModule, RouterModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  constructor(private router: Router) {
  }

  get isInstructor() {
    console.log('User id:', localStorage.getItem('id'))
    console.log('Logged in as:', localStorage.getItem('role'));  // "instructor" or "client"
    return localStorage.getItem('role') === 'instructor';
  }

  get isClient() {
    return localStorage.getItem('role') === 'client';
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/login']);
  }

}
