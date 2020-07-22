package com.justeat.techtest.kireland.test.mapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantDetails {
    public String Name;
    public int NumberOfRatings;
    public float RatingStars;
    public CuisineType[] CuisineTypes;
}
