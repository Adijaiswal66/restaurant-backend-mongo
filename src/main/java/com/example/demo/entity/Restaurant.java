package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "restaurants")
@Getter
@Setter
public class Restaurant {

    @Id
    private String id;
    private String name;
    private String description;

    @GeoSpatialIndexed
    private double[] location; // [longitude, latitude]

    private List<Double> ratings; // List of ratings

}
