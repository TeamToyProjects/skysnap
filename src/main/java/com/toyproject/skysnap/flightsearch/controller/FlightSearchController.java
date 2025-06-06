package com.toyproject.skysnap.flightsearch.controller;


import com.toyproject.skysnap.flightsearch.dto.FlightSearchRequest;
import com.toyproject.skysnap.flightsearch.dto.FlightSearchResponse;
import com.toyproject.skysnap.flightsearch.service.FlightSearchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 사용자 검색 요청을 받아 검색 결과를 응답
@RestController
@RequestMapping("/api/flights")
public class FlightSearchController {
    private final FlightSearchService flightSearchService;

    public FlightSearchController(FlightSearchService flightSearchService) {
        this.flightSearchService = flightSearchService;
    }

    @PostMapping("/search")
    public FlightSearchResponse searchFlights(@RequestBody FlightSearchRequest request){
        return flightSearchService.searchFlights(request);
    }


}
