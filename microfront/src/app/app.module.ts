import {APP_INITIALIZER, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ReservationComponent } from './reservation/reservation.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import { TableListComponent } from './components/table-list/table-list.component';
import { ListReclamationComponent } from './reclamation/list-reclamation/list-reclamation.component';
import { AddReclamationComponent } from './reclamation/add-reclamation/add-reclamation.component';
import { RouterModule } from '@angular/router';
import { NavbarComponent } from './navbar/navbar/navbar.component';
import {KeycloakService} from "./Keycloak/keycloak.service";
import {InterceptorInterceptor} from "./interceptor/interceptor.interceptor";
export function kcFactory(kcService: KeycloakService){
  return () => kcService.init();
}
import { NotificationModule } from './notification/notification.module';

@NgModule({
  declarations: [
    AppComponent,
    ReservationComponent,
    TableListComponent,
    ListReclamationComponent,
    AddReclamationComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    RouterModule,
    NotificationModule


  ],
  providers: [
    HttpClient,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptorInterceptor,
      multi: true
    },
    {
      provide: APP_INITIALIZER,
      deps: [KeycloakService],
      useFactory: kcFactory,
      multi: true
    }
   
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
