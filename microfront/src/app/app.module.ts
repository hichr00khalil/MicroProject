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
@NgModule({
  declarations: [
    AppComponent,
    ReservationComponent,
    TableListComponent,
    ListReclamationComponent,
    AddReclamationComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule ,
    ReactiveFormsModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
