package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistanceRangeRequest {
    private double latitude;
    private double longitude;
    private double minDistance; // Minimum distance in meters
    private double maxDistance; // Maximum distance in meters
}
