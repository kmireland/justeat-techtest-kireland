package com.justeat.techtest.kireland.test.mapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaDataDetails {
    public int ResultCount;
}
