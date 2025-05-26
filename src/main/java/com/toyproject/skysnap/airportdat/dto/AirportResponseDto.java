package com.toyproject.skysnap.airportdat.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("iata_code")
    private String iata;

    @JsonProperty("icao_code")
    private String icao;

    @JsonProperty("municipality")
    private String city;

    private CountryDto country;

    @JsonProperty("latitude_deg")
    private Double latitude;

    @JsonProperty("longitude_deg")
    private Double longitude;
}
