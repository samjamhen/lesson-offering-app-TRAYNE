import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common'; // for *ngIf, *ngFor etc.
import { FormsModule } from '@angular/forms';
import { EventCardComponent } from '../components/event-card/event-card.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule, EventCardComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  events: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get<any[]>('http://localhost:8080/event/all').subscribe({
      next: (data) => (this.events = data),
      error: (err) => console.error('Failed to fetch events', err)
    });
  }

  attendEvent(eventId: number) {
    const userId = Number(localStorage.getItem('id'));
    if (!userId) {
      alert('You must be logged in as a client to attend an event.');
      return;
    }
  
    const url = `http://localhost:8080/client/attend/${userId}/${eventId}`;
    this.http.post(url, null).subscribe({
      next: () => {
        alert('Successfully registered for the event!');
      },
      error: (err) => {
        console.error('Failed to register for event', err);
        alert('Something went wrong while attending the event.');
      }
    });
  }
  
}
