import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { TableListComponent } from './components/table-list/table-list.component';

const routes: Routes = [
  { path: '', redirectTo: '/restauration', pathMatch: 'full' }, // Default route
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
