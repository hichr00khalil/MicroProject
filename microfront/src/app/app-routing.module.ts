import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListReclamationComponent } from './reclamation/list-reclamation/list-reclamation.component';
import { AddReclamationComponent } from './reclamation/add-reclamation/add-reclamation.component';



const routes: Routes = [
  { path: 'add-reclamation', component: AddReclamationComponent }, 
  { path: 'list', component: ListReclamationComponent }, 
  // Route pour la liste des réclamations
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],  // Ajoute le routage
  exports: [RouterModule]  // Exporte le module de routage pour l'utiliser dans l'app
})
export class AppRoutingModule { }