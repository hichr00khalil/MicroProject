import { Facture} from './facture';
import { PAYMETHOD } from './PAYMETHOD';

export interface Payment {
  id?: number;
  paymentDate: Date;
  paymentMethod: PAYMETHOD;
  
  carte: string | null;   
  titulairecarte: string | null;   
  cvv: string | null;      
  jusqua: string | null;   
 

  facture?: Facture;
}
