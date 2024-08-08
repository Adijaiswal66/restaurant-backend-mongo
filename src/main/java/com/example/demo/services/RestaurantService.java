package com.example.demo.services;

import com.example.demo.entity.Restaurant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RestaurantService {

    public ResponseEntity<String> addRestaurant(@RequestBody Restaurant restaurant);

    public ResponseEntity<Restaurant> getRestaurant(@PathVariable("id") long id);

    public ResponseEntity<String> updateRestaurant(@PathVariable("id") long id, @RequestBody Restaurant restaurant);

    public ResponseEntity<String> deleteRestaurant(@PathVariable("id") long id);

    public ResponseEntity<List<Restaurant>> getNearbyRestaurants(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam double radius
    );
}
