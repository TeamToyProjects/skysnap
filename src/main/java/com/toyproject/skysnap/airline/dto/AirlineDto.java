package com.toyproject.skysnap.airline.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AirlineDto {

    private String airlineName;
    private String iataCode;
    private String icaoCode;
    private String country;
}
