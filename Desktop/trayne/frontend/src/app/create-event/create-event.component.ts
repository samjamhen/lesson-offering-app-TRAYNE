import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common'; // for *ngIf, *ngFor etc.
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-create-event',
  imports: [CommonModule, FormsModule, HttpClientModule],
  standalone:true,
  templateUrl: './create-event.component.html',
  styleUrl: './create-event.component.css'
})
export class CreateEventComponent {
  name = '';
  eventDate = '';
  location = '';
  equipmentProvided = false;
  description = '';
  price = 0;
  instructorId = localStorage.getItem('id'); // Assuming stored as string

  constructor(private http: HttpClient, private router: Router){}

  createEvent(){
    const eventData = {
      name: this.name,
      eventDate: this.eventDate,
      location: this.location,
      equipmentProvided: this.equipmentProvided,
      description: this.description,
      price: this.price,
      instructor: { id: Number(this.instructorId) }
    }
    this.http.post('http://localhost:8080/event/add', eventData).subscribe({
      next: () => {
        alert('Event created successfully!');
        this.router.navigate(['/home']);
      },
      error: err => {
        alert('Failed to create event.');
        console.error(err);
      }
  });
  }
}
