import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Reclamation } from 'src/model/reclamation';
import { ReclamationService } from 'src/service/reclamation.service';

@Component({
  selector: 'app-add-reclamation',
  templateUrl: './add-reclamation.component.html',
  styleUrls: ['./add-reclamation.component.css']
})
export class AddReclamationComponent {

  content: string = '';

  constructor(
    private router: Router,
    private reclamationService: ReclamationService // injecter le service
  ) {}


    onSubmit() {
      const newReclamation = {
        content: this.content,
        dateReclamation: new Date()
      } as Reclamation;  // <-- Cast propre
    
    this.reclamationService.createReclamation(newReclamation).subscribe({
      next: (response) => {
        console.log('Réclamation ajoutée !', response);
        this.router.navigate(['/list']); // Retour à la liste
      },
      error: (err) => {
        console.error('Erreur lors de l\'ajout de la réclamation', err);
      }
    });
  }

}
