import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class QRCodeService {
  
  constructor() { }

  /**
   * Génère une URL pour un QR code en utilisant l'API QR Code Generator
   * @param data Les données à encoder dans le QR code
   * @param size La taille du QR code (par défaut 150x150)
   * @returns URL du QR code
   */
  generateQRCodeUrl(data: string, size: number = 150): string {
    // Encoder les données pour l'URL
    const encodedData = encodeURIComponent(data);
    
    // Utiliser l'API QR Code Generator
    return `https://api.qrserver.com/v1/create-qr-code/?size=${size}x${size}&data=${encodedData}`;
  }
}
