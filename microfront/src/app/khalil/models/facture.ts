import { StatutPay } from "./StatutPay";
import { Payment } from "./payment";

export interface Facture {
  id?: number;
  numero: string;
  montantTotale: number;
  statutPaiement: StatutPay;
 }
