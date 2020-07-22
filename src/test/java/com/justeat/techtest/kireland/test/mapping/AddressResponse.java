package com.justeat.techtest.kireland.test.mapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressResponse {
    public MetaDataDetails MetaData;
    public RestaurantDetails[] Restaurants;
}
