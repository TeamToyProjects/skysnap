package com.toyproject.skysnap.flightsearch.service;


import com.toyproject.skysnap.flightsearch.dto.FlightSearchRequest;
import com.toyproject.skysnap.flightsearch.dto.FlightSearchResponse;

// 항공편 검색을 수행하는 서비스 인터페이스
public interface FlightSearchService {
    FlightSearchResponse searchFlights(FlightSearchRequest request);
}
