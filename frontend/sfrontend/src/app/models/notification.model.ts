export interface Notification {
  id?: number;  // Optional for creation
  message: string;
  date: Date;
  email: string;
  read: boolean;
  createdAt?: Date;  // Optional for creation
  reservationId?: number;
}
