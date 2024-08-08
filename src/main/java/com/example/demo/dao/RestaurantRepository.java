package com.example.demo.dao;

import com.example.demo.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("select r from Restaurant r where r.name = :name")
    public Optional<Restaurant> getRestaurantByName(@Param("name") String name);

    @Query("select r from Restaurant r where r.id = :id")
    public Optional<Restaurant> getRestaurantById(@Param("id") long id);

    @Query("SELECT r, (6371000 * acos(cos(radians(:latitude)) * cos(radians(r.latitude)) * cos(radians(r.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(r.latitude)))) AS distance " +
            "FROM Restaurant r " +
            "WHERE (6371000 * acos(cos(radians(:latitude)) * cos(radians(r.latitude)) * cos(radians(r.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(r.latitude)))) < :radius")
    List<Restaurant> findAllWithinRadius(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("radius") double radius
    );


}
