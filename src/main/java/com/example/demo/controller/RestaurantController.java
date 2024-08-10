package com.example.demo.controller;

import com.example.demo.dto.DistanceRangeRequest;
import com.example.demo.dto.NearbyRequest;
import com.example.demo.dto.RestaurantResponse;
import com.example.demo.entity.Restaurant;
import com.example.demo.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping(value = "add-restaurant")
    public ResponseEntity<String> addRestaurant(@RequestBody Restaurant restaurant) {
        String response = this.restaurantService.addRestaurant(restaurant);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping(value = "get-restaurant/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable("id") String id) {
        Restaurant restaurant = this.restaurantService.getRestaurant(id);
        return ResponseEntity.ok(restaurant);
    }

    @PutMapping(value = "update-restaurant/{id}")
    public ResponseEntity<String> updateRestaurant(@PathVariable("id") String id, @RequestBody Restaurant restaurant) {
        String response = this.restaurantService.updateRestaurant(id, restaurant);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "delete-restaurant/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable("id") String id) {
        String response = this.restaurantService.deleteRestaurant(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "nearby")
    public ResponseEntity<List<RestaurantResponse>> getNearbyRestaurants(
            @RequestBody NearbyRequest nearbyRequest
    ) {
        List<RestaurantResponse> restaurants = this.restaurantService.getNearbyRestaurants(
                nearbyRequest.getLatitude(),
                nearbyRequest.getLongitude(),
                nearbyRequest.getRadius()
        );

        return ResponseEntity.ok(restaurants);
    }


    @PostMapping(value = "distance-range")
    public ResponseEntity<List<RestaurantResponse>> getRestaurantsWithinDistanceRange(
            @RequestBody DistanceRangeRequest distanceRangeRequest
    ) {
        List<RestaurantResponse> restaurants = this.restaurantService.getRestaurantsWithinDistanceRange(
                distanceRangeRequest.getLatitude(),
                distanceRangeRequest.getLongitude(),
                distanceRangeRequest.getMinDistance(),
                distanceRangeRequest.getMaxDistance()
        );
        return ResponseEntity.ok(restaurants);
    }


}
