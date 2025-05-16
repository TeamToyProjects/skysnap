package com.toyproject.skysnap.airportdat.service;


import com.toyproject.skysnap.airportdat.dto.AirportResponseDto;
import java.util.List;

// 공항 검색 비지니스 로직 정의
public interface AirportService {
    List<AirportResponseDto> findAirportsByCity(String cityName);
}
