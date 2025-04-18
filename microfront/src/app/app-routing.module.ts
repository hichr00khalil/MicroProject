// src/app/app-routing.module.ts
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReservationComponent } from './reservation/reservation.component'; // Import your component
import { TableListComponent } from './components/table-list/table-list.component';
import { CommonModule } from '@angular/common';
import { AddReclamationComponent } from './reclamation/add-reclamation/add-reclamation.component';
import { ListReclamationComponent } from './reclamation/list-reclamation/list-reclamation.component';
const routes: Routes = [
  { path: '', redirectTo: '/reservation', pathMatch: 'full' }, // Default route
  { path: 'reservation', component: ReservationComponent }, 
  { path: 'restauration', component: TableListComponent },
  { path: 'add-reclamation', component: AddReclamationComponent }, 
  { path: 'list', component: ListReclamationComponent },  // Define the reservation route
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
