package com.toyproject.skysnap.airportcsv.dto;

import lombok.Builder;
import lombok.Data;


// 사용자 응답용
@Data
@Builder
public class AirportDto {

    private String name;  // 공항 이름
    private String country;
    private String iata;
    private String icao;
    private double latitude;
    private double longitude;


}
