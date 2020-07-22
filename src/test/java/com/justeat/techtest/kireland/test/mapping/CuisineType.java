package com.justeat.techtest.kireland.test.mapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CuisineType {
    public String Name;

}
