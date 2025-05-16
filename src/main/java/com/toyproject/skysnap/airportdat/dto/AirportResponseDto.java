package com.toyproject.skysnap.airportdat.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// API 응답에서 사용하는 공항 정보 데이터 전송 객체
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AirportResponseDto {
    private String name; // 공항 이름
    private String iata;
    private String icao;
    private String city;
    private String country;
    private Double latitude; // null 가능성 고려해 Double로
    private Double longitude;
}
