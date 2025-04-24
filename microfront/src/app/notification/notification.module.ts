import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

import { NotificationListComponent } from './notification-list/notification-list.component';
import { NotificationCreateComponent } from './notification-create/notification-create.component';
import { NotificationDetailComponent } from './notification-detail/notification-detail.component';

const routes: Routes = [
  { path: 'notifications', component: NotificationListComponent },
  { path: 'notifications/create', component: NotificationCreateComponent },
  { path: 'notifications/:id', component: NotificationDetailComponent }
];

@NgModule({
  declarations: [
    NotificationListComponent,
    NotificationCreateComponent,
    NotificationDetailComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(routes)
  ],
  exports: [
    NotificationListComponent
  ]
})
export class NotificationModule { }
