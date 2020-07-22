package com.justeat.techtest.kireland.test.steps;


import com.justeat.techtest.kireland.test.mapping.AddressResponse;
import com.justeat.techtest.kireland.test.mapping.CuisineType;
import com.justeat.techtest.kireland.test.mapping.RestaurantDetails;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCases {
    @Before
    public void setup() {
        baseURI = "https://uk.api.just-eat.io/restaurants/bypostcode/ar511aa";
    }

    @Test
    public void validateAddressApiReturns200() {
        get().then().statusCode(200);

    }

    @Test
    public void validateRatedRestaurantHasValidSchema() {
//        TODO: I couldn't get this to work but fairly sure it should be possible,
//              so I'm showing you my working!
//                get()
//                .then()
//                .body("Restaurants[1].Address",
//                        matchesJsonSchemaInClasspath("addressSchema.json"));
        // A bit cheeky... I'm using the object mapper to validate the schema against the class definition
        AddressResponse response = get().then()
                .extract().as(AddressResponse.class);
        assertThat(response, notNullValue()); //If we have got here the schema is good for all address objects
    }

    @Test
    public void validateRatedRestaurantHasValidRating() {
        AddressResponse response = get().then()
                .extract().as(AddressResponse.class);
        for (RestaurantDetails restaurantDetailItem : Arrays.stream(response.Restaurants).filter(x -> x.NumberOfRatings > 0).toArray(RestaurantDetails[]::new)) {
            assertThat(restaurantDetailItem.RatingStars, greaterThan(0F));
        }

    }

    @Test
    public void validateNotRatedRestaurantHasValidRating() {
        AddressResponse response = get().then()
                .extract().as(AddressResponse.class);
        for (RestaurantDetails restaurantDetailItem : Arrays.stream(response.Restaurants).filter(x -> x.NumberOfRatings == 0).toArray(RestaurantDetails[]::new)) {
            assertThat(restaurantDetailItem.RatingStars, equalTo(0F));
        }

    }

    @Test
    public void validateRestaurantHasAtLeastOneCuisine() {
        AddressResponse response = get().then()
                .extract().as(AddressResponse.class);
        for (RestaurantDetails restaurantDetailItem : response.Restaurants) {
            assertThat(restaurantDetailItem.CuisineTypes.length, greaterThan(0));
            for (CuisineType cuisineType : restaurantDetailItem.CuisineTypes) {
                assertThat(cuisineType.Name, not(isEmptyOrNullString()));
            }
        }
    }

    @Test
    public void validateRestaurantResultCount() {
        AddressResponse response = get().then()
                .extract().as(AddressResponse.class);
        assertThat(response.MetaData.ResultCount, is(equalTo(response.Restaurants.length)));
    }

    @Test
    public void validateRestaurantHasAValidName() {
        AddressResponse response = get().then()
                .extract().as(AddressResponse.class);

        for (RestaurantDetails restaurantDetailItem : response.Restaurants) {
            assertThat(restaurantDetailItem.Name, not(isEmptyOrNullString()));
        }

    }

}
