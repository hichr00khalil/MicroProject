import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FactureService } from 'src/app/Services/facture/facture.service';
import { Facture } from 'src/app/models/Facture';
import { StatutPay } from 'src/app/models/StatutPay';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent {
  factureForm: FormGroup;
  statutOptions = Object.values(StatutPay);
  successMessage = '';

  constructor(private fb: FormBuilder, private factureService: FactureService,     private router: Router
  ) {
    this.factureForm = this.fb.group({
      numero: ['', Validators.required],
      montantTotale: [null, Validators.required],
      statutPaiement: [null, Validators.required]
    });
  }

  onSubmit(): void {
    if (this.factureForm.valid) {
      const facture: Facture = this.factureForm.value;

      this.factureService.sendEmail(facture).subscribe({
        next: () => {
          this.factureService.create(facture).subscribe({
            next: () => {
              this.successMessage = '✅ Facture added successfully!';
              setTimeout(() => this.router.navigate(['all']), 2000); // Wait 2s then redirect
            },
            error: err => console.error('Create error:', err)
          });
        },
        error: err => console.error('Email error:', err)
      });
    }
  }
}