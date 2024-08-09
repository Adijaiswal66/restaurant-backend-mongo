package com.example.demo.services;

import com.example.demo.config.CustomUserDetails;
import com.example.demo.dao.RestaurantRepository;
import com.example.demo.entity.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Restaurant restaurant = this.restaurantRepository.getRestaurantByName1(name);
        System.out.println(restaurant);
        if (restaurant != null) {
            return new CustomUserDetails(restaurant);
        }
        throw new UsernameNotFoundException("Could not found user !!");

    }
}

