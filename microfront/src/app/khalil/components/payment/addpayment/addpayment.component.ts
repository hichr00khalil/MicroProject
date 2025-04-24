import { Component, OnInit } from '@angular/core'; 
import { ActivatedRoute, Router } from '@angular/router';
import { PaymentService } from 'src/app/khalil/services/payment/payment.service';
import { FactureService } from 'src/app/khalil/services/facture/facture.service';
import { Facture } from 'src/app/khalil/models/facture';
import { Payment } from 'src/app/khalil/models/payment';
import { PAYMETHOD } from 'src/app/khalil/models/PAYMETHOD';

@Component({
  selector: 'app-addpayment',
  templateUrl: './addpayment.component.html',
  styleUrls: ['./addpayment.component.css']
})
export class AddpaymentComponent implements OnInit {
  payment: Payment = {
    paymentDate: new Date(),
    paymentMethod: PAYMETHOD.CARTE,
    carte: '',
    titulairecarte: '',
    cvv: '',
    jusqua: '',
  };
  facture: Facture | null = null;
  numTab: number | null = null;

  // Success message to display
  successMessage: string = '';

  constructor(
    private route: ActivatedRoute,
    private paymentService: PaymentService,
    private factureService: FactureService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const factureId = this.route.snapshot.queryParamMap.get('factureId');
    
    if (factureId) {
      this.factureService.getById(+factureId).subscribe({
        next: (data) => {
          this.facture = data;
          if (this.facture) {
            this.numTab = Number(this.facture.numero);
          }
        },
        error: (err) => console.error('Error fetching facture:', err)
      });
    }
  }

  onSubmit(): void {
    if (this.payment.paymentMethod === 'CASH') {
      this.payment.carte = null;
      this.payment.titulairecarte = null;
      this.payment.cvv = null;
      this.payment.jusqua = null;
    }

    if (this.facture) {
      this.payment.facture = this.facture;
    } else {
      console.error('Facture is missing, cannot create payment');
      return;
    }

    console.log('Submitting payment with facture:', this.payment);

    this.paymentService.create(this.payment).subscribe(
      (response) => {
        console.log('Payment created successfully', response);

        // Set success message and redirect to facture page
        this.successMessage = 'Payment created successfully!';
        
        // Redirect to facture page (replace `factureId` if needed)
        this.router.navigate(['/facture'], { queryParams: { factureId: this.facture!.id } });
      },
      (error) => {
        console.error('Error creating payment', error);
        // Set error message
        this.successMessage = 'Failed to create payment.';
      }
    );
  }
}
