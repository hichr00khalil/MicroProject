import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Reclamation } from 'src/model/reclamation';
import { ReclamationService } from 'src/service/reclamation.service';

@Component({
  selector: 'app-list-reclamation',
  templateUrl: './list-reclamation.component.html',
  styleUrls: ['./list-reclamation.component.css']
})
export class ListReclamationComponent implements OnInit {

 
  reclamations: Reclamation[] = [];

  constructor(private reclamationService: ReclamationService, private router: Router) { }

  ngOnInit(): void {
    this.loadReclamations();
  }

  loadReclamations(): void {
    this.reclamationService.getAllReclamations().subscribe(
      (data) => {
        this.reclamations = data;
      },
      (error) => {
        console.error('Error loading reclamations:', error);
        alert('Error loading reclamations. Please try again later.');
      }
    );
  }

  deleteReclamation(id: number): void {
    if (confirm('Are you sure you want to delete this reclamation?')) {
      this.reclamationService.deleteReclamation(id).subscribe(
        () => {
          this.loadReclamations();  // Reload the list after deletion
        },
        (error) => {
          console.error('Error deleting reclamation:', error);
          alert('Error deleting reclamation. Please try again later.');
        }
      );
    }
  }

  // Navigates to the 'Add Reclamation' page
  navigateToAddReclamation(): void {
    this.router.navigate(['/add-reclamation']);  // Adjust the route path based on your routing configuration
  }

}
