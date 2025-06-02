package com.toyproject.skysnap.filghtsearch.service;


import com.toyproject.skysnap.filghtsearch.dto.FlightSearchRequest;
import com.toyproject.skysnap.filghtsearch.dto.FlightSearchResponse;

// 항공편 검색을 수행하는 서비스 인터페이스
public interface FlightSearchService {
    FlightSearchResponse searchFlights(FlightSearchRequest request);
}
