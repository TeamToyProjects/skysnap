package com.toyproject.skysnap.airportcsv.mapper;


import com.toyproject.skysnap.airportcsv.dto.AirportCsvDto;
import com.toyproject.skysnap.airportcsv.dto.AirportDto;

//DTO 변환
public class AirportMapper {
    public static AirportDto toDto(AirportCsvDto csvDto) {
        return AirportDto.builder()
                .name(csvDto.getName())
                .country(csvDto.getIsoCountry())
                .iata(csvDto.getIataCode())
                .icao(csvDto.getIcaoCode())
                .latitude(csvDto.getLatitudeDeg())
                .longitude(csvDto.getLongitudeDeg())
                .build();
    }
}
