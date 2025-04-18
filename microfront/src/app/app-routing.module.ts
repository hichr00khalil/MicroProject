// src/app/app-routing.module.ts
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReservationComponent } from './reservation/reservation.component'; // Import your component
import { TableListComponent } from './components/table-list/table-list.component';
import { CommonModule } from '@angular/common';
const routes: Routes = [
  { path: 'reservation', component: ReservationComponent }, 
  { path: 'restauration', component: TableListComponent }, // Define the reservation route
  // Add other routes as needed
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes),
    CommonModule,
    RouterModule
  ]
})
export class AppRoutingModule { }
