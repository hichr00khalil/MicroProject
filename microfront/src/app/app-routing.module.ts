// src/app/app-routing.module.ts
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReservationComponent } from './reservation/reservation.component'; // Import your component

const routes: Routes = [
  { path: '', redirectTo: '/reservation', pathMatch: 'full' }, // Default route
  { path: 'reservation', component: ReservationComponent }, // Define the reservation route
  // Add other routes as needed
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
