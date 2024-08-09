package com.example.demo.services;

import com.example.demo.dao.RestaurantRepository;
import com.example.demo.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public ResponseEntity<String> addRestaurant(Restaurant restaurant) {
        Optional<Restaurant> existingRestaurant = this.restaurantRepository.getRestaurantByName(restaurant.getName());
        if (existingRestaurant.isEmpty()) {
            try {
                restaurant.setPassword(passwordEncoder.encode(restaurant.getPassword()));
                this.restaurantRepository.save(restaurant);
                return new ResponseEntity<>("Restaurant is added", HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else return new ResponseEntity<>("Restaurant already exists !! ", HttpStatus.NOT_FOUND);

    }

    @Override
    public ResponseEntity<Restaurant> getRestaurant(long id) {
        Optional<Restaurant> existingRestaurant = this.restaurantRepository.findById(id);
        if (existingRestaurant.isPresent()) {

            try {
                this.restaurantRepository.findById(id);
                return new ResponseEntity<>(existingRestaurant.get(), HttpStatus.OK);

            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> updateRestaurant(long id, Restaurant restaurant) {
        Optional<Restaurant> existingRestaurant = this.restaurantRepository.findById(id);
        if (existingRestaurant.isPresent()) {
            try {
                this.restaurantRepository.findById(id).map(
                        u -> {
                            u.setId(existingRestaurant.get().getId());
                            u.setName(restaurant.getName());
                            u.setPassword(existingRestaurant.get().getPassword());
                            u.setDescription(restaurant.getDescription());
                            u.setLongitude(restaurant.getLongitude());
                            u.setLatitude(restaurant.getLatitude());
                            u.setRatings(restaurant.getRatings());
                            return this.restaurantRepository.save(u);
                        });
                return new ResponseEntity<>("Restaurant with id " + id + " is updated successfully", HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else return new ResponseEntity<>("Could not found user with id: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> deleteRestaurant(long id) {
        Optional<Restaurant> existingRestaurant = this.restaurantRepository.findById(id);

        if (existingRestaurant.isPresent()) {
            try {
                this.restaurantRepository.deleteById(id);
                return new ResponseEntity<>("Restaurant with id " + id + " is deleted successfully", HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>("Something went wrong !!", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else return new ResponseEntity<>("Could not found retaurant with id " + id, HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<List<Restaurant>> getNearbyRestaurants(double latitude, double longitude, double radius) {
        List<Restaurant> nearbyRestaurants = this.restaurantRepository.findAllWithinRadius(latitude, longitude, radius);
        if (!nearbyRestaurants.isEmpty()) {
            try {
                restaurantRepository.findAllWithinRadius(latitude, longitude, radius);
                return new ResponseEntity<>(nearbyRestaurants, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
