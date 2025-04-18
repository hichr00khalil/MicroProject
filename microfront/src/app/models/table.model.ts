export interface Table {
    id?: number; // facultatif pour la création
    numTab: number;
    capacite: number;
    statut: string; // ou enum si tu préfères, mais string c’est plus simple ici
  }
  
  