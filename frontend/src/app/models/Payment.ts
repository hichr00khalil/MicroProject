import { Facture } from "./Facture";

export interface Payment {
  id?: number;
  paymentDate: Date;
  paymentMethod: string;
  facture?: Facture;
}
