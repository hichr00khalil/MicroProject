import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ReservationComponent } from './reservation/reservation.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { TableListComponent } from './components/table-list/table-list.component';
import { ListReclamationComponent } from './reclamation/list-reclamation/list-reclamation.component';
import { AddReclamationComponent } from './reclamation/add-reclamation/add-reclamation.component';
import { RouterModule } from '@angular/router';
import { NavbarComponent } from './navbar/navbar/navbar.component';
 import { AddComponent } from './khalil/components/facture/add/add.component';
import { AfficherComponent } from './khalil/components/facture/afficher/afficher.component';
import { AddpaymentComponent } from './khalil/components/payment/addpayment/addpayment.component';
 @NgModule({
  declarations: [
    AppComponent,
    ReservationComponent,
    TableListComponent,
    ListReclamationComponent,
    AddReclamationComponent,
    NavbarComponent,
    AddComponent,
    AfficherComponent,
    AddpaymentComponent  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule ,
    ReactiveFormsModule,
    RouterModule   
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
