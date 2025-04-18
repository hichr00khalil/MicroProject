import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AfficherComponent } from './Components/facture/afficher/afficher.component';
import { AddComponent } from './Components/facture/add/add.component';
import { EditComponent } from './Components/facture/edit/edit.component'; 
 @NgModule({
  declarations: [
    AppComponent,
    AfficherComponent,
    AddComponent,
    EditComponent
    
    
    

  ],
  imports: [
     BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule ,
    ReactiveFormsModule
  ],
  exports: [AppRoutingModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
