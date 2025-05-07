// event-card.component.ts
import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-event-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './event-card.component.html'
})
export class EventCardComponent {
  @Input() event: any;
  @Output() attend = new EventEmitter<number>();

  attendEvent() {
    this.attend.emit(this.event.id);
  }
}
