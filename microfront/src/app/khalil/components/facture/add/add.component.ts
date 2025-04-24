import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FactureService } from 'src/app/khalil/services/facture/facture.service';
import { Facture } from 'src/app/khalil/models/facture';
import { StatutPay } from 'src/app/khalil/models/StatutPay';  

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent {
  factureForm: FormGroup;
  statutOptions = Object.values(StatutPay);  // You already have this line, it's fine
  successMessage = '';

  constructor(
    private fb: FormBuilder, 
    private factureService: FactureService,     
    private router: Router
  ) {
    // Set default statutPaiement to NON_PAYE and prevent it from being edited
    this.factureForm = this.fb.group({
      numero: ['', Validators.required],
      montantTotale: [null, Validators.required],
      statutPaiement: [{ value: StatutPay.NON_PAYE, disabled: true }, Validators.required]
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
              setTimeout(() => this.router.navigate(['facture']), 500); // Wait 2s then redirect
            },
            error: err => console.error('Create error:', err)
          });
        },
        error: err => console.error('Email error:', err)
      });
    }
  }
}
