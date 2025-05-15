package com.toyproject.skysnap.airportapi.external.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

// 외부 API로부터 받은 공항 데이터를 담는 단건 DTO
@Getter
@Setter
public class AirportExternalDto {
    // JSON 필드명(공항정보)과 자바 필드명을 연결
    @JsonProperty("airport_name")
    private String airportName;

    @JsonProperty("iata_code")
    private String iataCode;

    @JsonProperty("icao_code")
    private String icaoCode;

    private double latitude;
    private double longitude;

    @JsonProperty("country_name")
    private String countryName;

    @JsonProperty("city_iata_code")
    private String cityIataCode;



}
