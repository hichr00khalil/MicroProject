import { Routes } from '@angular/router';
import { NotificationListComponent } from './components/notification-list/notification-list.component';
import { NotificationCreateComponent } from './components/notification-create/notification-create.component';
import { NotificationQrPageComponent } from './pages/notification-qr-page/notification-qr-page.component';

export const routes: Routes = [
  { path: '', redirectTo: '/notifications', pathMatch: 'full' },
  { path: 'notifications', component: NotificationListComponent },
  { path: 'notifications/create', component: NotificationCreateComponent },
  { path: 'notifications/:id/qr', component: NotificationQrPageComponent }
];
