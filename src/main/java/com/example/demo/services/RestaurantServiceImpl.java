package com.example.demo.services;

import com.example.demo.dao.RestaurantRepository;
import com.example.demo.dto.RestaurantResponse;
import com.example.demo.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private static final double EARTH_RADIUS_IN_METERS = 6378100.0;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String addRestaurant(
            Restaurant restaurant
    ) {
        restaurantRepository.save(restaurant);
        return "Restaurant added successfully with ID: " + restaurant.getId();
    }

    @Override
    public Restaurant getRestaurant(
            String id
    ) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return restaurant.orElse(null);
    }

    @Override
    public String updateRestaurant(
            String id,
            Restaurant restaurant
    ) {
        Optional<Restaurant> existingRestaurant = restaurantRepository.findById(id);
        if (existingRestaurant.isPresent()) {
            Restaurant updatedRestaurant = existingRestaurant.get();
            updatedRestaurant.setName(restaurant.getName());
            updatedRestaurant.setDescription(restaurant.getDescription());
            updatedRestaurant.setLocation(restaurant.getLocation());
            updatedRestaurant.setRatings(restaurant.getRatings());
            restaurantRepository.save(updatedRestaurant);
            return "Restaurant updated successfully with ID: " + updatedRestaurant.getId();
        } else {
            return "Restaurant not found with ID: " + id;
        }
    }

    @Override
    public String deleteRestaurant(
            String id
    ) {
        if (restaurantRepository.existsById(id)) {
            restaurantRepository.deleteById(id);
            return "Restaurant deleted successfully with ID: " + id;
        } else {
            return "Restaurant not found with ID: " + id;
        }
    }

    @Override
    public List<RestaurantResponse> getNearbyRestaurants(
            double latitude,
            double longitude,
            double radius
    ) {
        Point location = new Point(longitude, latitude);
        double radiusInRadians = radius / 6378100.0; // Earth's radius in meters
        Circle area = new Circle(location, radiusInRadians);
        Query query = new Query(Criteria.where("location").withinSphere(area));
        List<Restaurant> restaurants = mongoTemplate.find(query, Restaurant.class);

        return restaurants.stream().map(restaurant -> {
            double averageRating = restaurant.getRatings().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            int numberOfRatings = restaurant.getRatings().size();
            return new RestaurantResponse(
                    restaurant.getId(),
                    restaurant.getName(),
                    restaurant.getDescription(),
                    restaurant.getLocation(),
                    averageRating,
                    numberOfRatings
            );
        }).collect(Collectors.toList());
    }

    @Override
    public List<RestaurantResponse> getRestaurantsWithinDistanceRange(double latitude, double longitude, double minDistance, double maxDistance) {

        // Perform the query
        List<RestaurantResponse> restaurants = getNearbyRestaurants(latitude,longitude,maxDistance);

        // Filter results to ensure they are also within the minimum distance
        return restaurants.stream()
                .filter(restaurant -> {
                    double distance = calculateDistance(
                            latitude,
                            longitude,
                            restaurant.getLocation()[1],
                            restaurant.getLocation()[0]
                    );
                    return distance >= minDistance;
                })
                .toList();

    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the Earth in kilometers
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c * 1000; // Convert to meters
    }

}
