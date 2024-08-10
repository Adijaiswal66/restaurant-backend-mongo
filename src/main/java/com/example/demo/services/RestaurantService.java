package com.example.demo.services;

import com.example.demo.dto.RestaurantResponse;
import com.example.demo.entity.Restaurant;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface RestaurantService {

    public String addRestaurant(@RequestBody Restaurant restaurant);

    public Restaurant getRestaurant(@PathVariable("id") String id);

    public String updateRestaurant(@PathVariable("id") String id, @RequestBody Restaurant restaurant);

    public String deleteRestaurant(@PathVariable("id") String id);

    public List<RestaurantResponse> getNearbyRestaurants(
            double latitude,
            double longitude,
            double radius
    );

    public List<RestaurantResponse> getRestaurantsWithinDistanceRange(
            double latitude,
            double longitude,
            double minDistance,
            double maxDistance
    );
}
