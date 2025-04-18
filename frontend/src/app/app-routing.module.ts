import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddComponent } from './Components/facture/add/add.component';
import { AfficherComponent } from './Components/facture/afficher/afficher.component';
import { EditComponent } from './Components/facture/edit/edit.component';
 

const routes: Routes = [

   
  {path: 'add', component: AddComponent},
  {path: 'all', component: AfficherComponent},
  {path: 'edit/:id', component: EditComponent},



];

@NgModule({
  imports: [RouterModule.forRoot(routes, { enableTracing: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
