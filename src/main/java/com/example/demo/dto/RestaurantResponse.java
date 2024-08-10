package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestaurantResponse {
    private String id;
    private String name;
    private String description;
    private double[] location;
    private double averageRating;
    private int numberOfRatings;
}
