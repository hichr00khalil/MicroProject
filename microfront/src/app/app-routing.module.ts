// src/app/app-routing.module.ts
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReservationComponent } from './reservation/reservation.component'; // Import your component
import { TableListComponent } from './components/table-list/table-list.component';
import { CommonModule } from '@angular/common';
import { AddReclamationComponent } from './reclamation/add-reclamation/add-reclamation.component';
import { ListReclamationComponent } from './reclamation/list-reclamation/list-reclamation.component';
import { AddComponent } from './khalil/components/facture/add/add.component';
import { AfficherComponent } from './khalil/components/facture/afficher/afficher.component';
import { AddpaymentComponent } from './khalil/components/payment/addpayment/addpayment.component';
const routes: Routes = [
  { path: 'reservation', component: ReservationComponent }, 
  { path: 'restauration', component: TableListComponent },
  { path: 'add-reclamation', component: AddReclamationComponent }, 
  { path: 'list', component: ListReclamationComponent },  
  {path: 'addfacture', component: AddComponent},
  {path: 'facture', component: AfficherComponent},  
     {path: 'pay', component: AddpaymentComponent},  

];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes),
    CommonModule,
    RouterModule
  ]
})
export class AppRoutingModule { }
