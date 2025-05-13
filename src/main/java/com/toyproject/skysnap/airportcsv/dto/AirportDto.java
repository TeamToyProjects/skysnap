package com.toyproject.skysnap.airportcsv.dto;

// 사용자 응답용

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirportDto {

    private String name;
    private String country;
    private String iata;
    private String icao;
    private String latitude;
    private String longitude;


}
