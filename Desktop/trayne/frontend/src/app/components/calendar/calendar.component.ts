import { Component } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FullCalendarModule } from '@fullcalendar/angular';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import { CommonModule } from '@angular/common';
import { CalendarOptions } from '@fullcalendar/core';


@Component({
  selector: 'app-calendar',
  standalone: true,
  imports: [FullCalendarModule, HttpClientModule, CommonModule],
  templateUrl: './calendar.component.html'
})
export class CalendarComponent {
  calendarPlugins = [dayGridPlugin];
  events: any[] = [];
  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
    plugins: [dayGridPlugin]
  };

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get<any[]>('http://localhost:8080/event/all').subscribe(data => {
      this.events = data.map(event => ({
        title: event.name,
        date: event.eventDate.split('T')[0]
      }));
    });
  }
}

