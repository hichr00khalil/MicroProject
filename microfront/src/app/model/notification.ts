export interface Notification {
    id?: number;
    message: string;
    createdAt?: Date;
    isRead: boolean;
    reservationId?: number;
}
