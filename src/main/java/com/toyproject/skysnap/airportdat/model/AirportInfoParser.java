package com.toyproject.skysnap.airportdat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

// airports.dat 각 행을 표현, dat 파일을 읽어서 메모리에 올리는 용도
@Getter
@AllArgsConstructor
public class AirportInfoParser {
    private String name;
    private String city;
    private String country;
    private String iata;
    private String icao;
}
