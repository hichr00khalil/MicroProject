package esprit.microservice1.services;

import esprit.microservice1.entities.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService implements IRestaurantService{
    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        return null;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return null;
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return null;
    }

    @Override
    public void deleteRestaurant(Long id) {

    }
}
