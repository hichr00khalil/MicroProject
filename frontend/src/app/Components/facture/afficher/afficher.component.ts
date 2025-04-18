import { Component, OnInit } from '@angular/core';
import { Facture } from 'src/app/models/Facture';
import { FactureService } from 'src/app/Services/facture/facture.service';

@Component({
  selector: 'app-afficher',
  templateUrl: './afficher.component.html',
  styleUrls: ['./afficher.component.css']
})
export class AfficherComponent implements OnInit {
  factures: Facture[] = [];

  constructor(private factureService: FactureService) {}

  ngOnInit(): void {
    this.getFactures();
  }

  getFactures(): void {
    this.factureService.getAll().subscribe({
      next: data => this.factures = data,
      error: err => console.error(err)
    });
    
  }

  deleteFacture(id: number): void {
    if (confirm('Are you sure you want to delete this facture?')) {
      this.factureService.delete(id).subscribe({
        next: () => {
          this.factures = this.factures.filter(f => f.id !== id);
        },
        error: err => console.error('Delete error:', err)
      });
    }
  }

  
  
}
