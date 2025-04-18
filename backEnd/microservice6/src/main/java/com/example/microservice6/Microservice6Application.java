package com.example.microservice6;

import com.example.microservice6.entities.Notification;
import com.example.microservice6.repositories.NotificationRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class Microservice6Application {


	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Microservice6Application.class);

		// Activer le profil de développement par défaut
		app.setAdditionalProfiles("dev");

		app.run(args);
	}

	@Autowired
	NotificationRespository notificationRespository;

	@Bean
	public CommandLineRunner addNotification(NotificationRespository notificationRespository) {
		return args -> {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date date1 = sdf.parse("14-04-2025");
			Date date2 = sdf.parse("15-04-2025");
			Date date3 = sdf.parse("16-04-2025");

			// Unread notification
			notificationRespository.save(new Notification("New reservation confirmed", date1, "yosser.dridi@esprit.tn", false));

			// Read notification
			notificationRespository.save(new Notification("Your payment was processed", date2, "yosser.dridi@esprit.tn", true));

			// Another unread notification
			notificationRespository.save(new Notification("Reservation reminder for tomorrow", date3, "yosser.dridi@esprit.tn", false));

			// Notifications for another user
			notificationRespository.save(new Notification("Welcome to our restaurant", date1, "badia@gmail.com", false));
			notificationRespository.save(new Notification("Special discount available", date2, "badia@gmail.com", true));
		};
	}
}