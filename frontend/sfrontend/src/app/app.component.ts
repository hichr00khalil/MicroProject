import { Component, OnInit } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';
import { NotificationBadgeComponent } from './components/notification-badge/notification-badge.component';
import { WebSocketService } from './services/websocket.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive, NotificationBadgeComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'CoffeeManager';
  userEmail = 'yosser.dridi@esprit.tn'; // This would typically come from authentication

  constructor(private webSocketService: WebSocketService) {}

  ngOnInit() {
    // Initialiser le service de notifications
    this.webSocketService.subscribeToUserNotifications(this.userEmail);
  }
}
