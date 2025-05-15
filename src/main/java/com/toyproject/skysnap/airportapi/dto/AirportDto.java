package com.toyproject.skysnap.airportapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// 사용자에게 전달할 DTO
@Getter
@Setter
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자 자동 생성
public class AirportDto {
    private String airportName;
    private String iataCode;
    private String icaoCode;
    private double latitude;
    private double longitude;
    private String countryName;
    private String cityIataCode; // 해당 공항이 속한 도시의 IATA 코드

}
