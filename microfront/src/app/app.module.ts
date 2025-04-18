import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { ListReclamationComponent } from './reclamation/list-reclamation/list-reclamation.component';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { AddReclamationComponent } from './reclamation/add-reclamation/add-reclamation.component';


@NgModule({
  declarations: [
    AppComponent,
    ListReclamationComponent,
    AddReclamationComponent,
    
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule  
    
    
   
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
