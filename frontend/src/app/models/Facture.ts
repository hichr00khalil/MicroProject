import { StatutPay } from "./StatutPay";
import { Payment } from "./Payment";

export interface Facture {
  id?: number;
  numero: string;
  montantTotale: number;
  statutPaiement: StatutPay;
  payment?: Payment;
}
