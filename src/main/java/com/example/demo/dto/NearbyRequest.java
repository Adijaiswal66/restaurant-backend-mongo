package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NearbyRequest {
    private double latitude;
    private double longitude;
    private double radius;
}
