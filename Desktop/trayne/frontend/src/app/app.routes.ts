import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { LayoutComponent } from './layout/layout.component';
import { CreateEventComponent } from './create-event/create-event.component';
import { SignupComponent } from './signup/signup.component';
import { CalendarComponent } from './components/calendar/calendar.component';

export const routes: Routes = [
    {
      path: '',
      component: LayoutComponent,
      children: [
        { path: 'home', component: HomeComponent },
        { path: 'calendar', component: CalendarComponent},
        { path: 'create-event', component: CreateEventComponent},
        { path: '', redirectTo: 'home', pathMatch: 'full' }
        // more protected pages here
      ]
    },
    { path: 'sign-in', component: SignupComponent},
    { path: 'login', component: LoginComponent } // No header shown here
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
