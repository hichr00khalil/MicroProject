package esprit.microservice1.services;

import esprit.microservice1.entities.Restaurant;

import java.util.List;

public interface IRestaurantService {
    Restaurant createRestaurant(Restaurant restaurant);
    List<Restaurant> getAllRestaurants();
    Restaurant getRestaurantById(Long id);
    void deleteRestaurant(Long id);
}
