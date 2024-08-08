package com.example.demo.controller;

import com.example.demo.entity.Restaurant;
import com.example.demo.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping(value = "add-restaurant")
    public ResponseEntity<String> addRestaurant(@RequestBody Restaurant restaurant) {
       return this.restaurantService.addRestaurant(restaurant);
    }

    @GetMapping(value = "get-restaurant/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable("id") long id) {
        return this.restaurantService.getRestaurant(id);
    }

    @PutMapping(value = "update-restaurant/{id}")
    public ResponseEntity<String> updateRestaurant(@PathVariable("id") long id, @RequestBody Restaurant restaurant) {
        return this.restaurantService.updateRestaurant(id, restaurant);
    }

    @DeleteMapping(value = "delete-restaurant/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable("id") long id) {
        return this.restaurantService.deleteRestaurant(id);
    }

    @GetMapping(value = "restaurants/nearby")
    public ResponseEntity<List<Restaurant>> getNearbyRestaurants(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam double radius
    ) {
        return this.restaurantService.getNearbyRestaurants(latitude, longitude, radius);
//        List<Restaurant> nearbyRestaurants = restaurantService.getNearbyRestaurants(latitude, longitude, radius);
//        return ResponseEntity.ok(nearbyRestaurants);
    }

}
