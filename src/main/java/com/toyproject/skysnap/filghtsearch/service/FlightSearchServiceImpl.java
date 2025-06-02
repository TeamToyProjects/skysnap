package com.toyproject.skysnap.filghtsearch.service;


import com.toyproject.skysnap.airportdat.client.AirportDbClient;
import com.toyproject.skysnap.airportdat.dto.AirportResponseDto;
import com.toyproject.skysnap.airportdat.model.AirportInfoParser;
import com.toyproject.skysnap.airportdat.service.AirportService;
import com.toyproject.skysnap.filghtsearch.dto.FlightSearchRequest;
import com.toyproject.skysnap.filghtsearch.dto.FlightSearchResponse;
import com.toyproject.skysnap.filghtsearch.model.FlightOption;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// 공항,항공사 API 조합해 필터링, 정렬된 항공편 목록을 반환, 실제 구현체
@Service
public class FlightSearchServiceImpl implements FlightSearchService {
    private final AirportDbClient airportDbClient;
    private final AirportService airportService;
    private final MockAirlineService mockAirlineService;

    public FlightSearchServiceImpl(AirportDbClient airportDbClient,
                                   AirportService airportService,
                                   MockAirlineService mockAirlineService) {
        this.airportDbClient = airportDbClient;
        this.airportService = airportService;
        this.mockAirlineService = mockAirlineService;
    }

    @Override
    public FlightSearchResponse searchFlights(FlightSearchRequest request) {
        String depIcao = airportService.findAirportsByCity(request.getDepartureCity()).get(0).getIcao();
        String arrIcao = airportService.findAirportsByCity(request.getArriveCity()).get(0).getIcao();

        AirportResponseDto depAirport = airportDbClient.getAirportByIcao(depIcao);
        AirportResponseDto arrAirport = airportDbClient.getAirportByIcao(arrIcao);

        List<FlightOption> options = mockAirlineService.getAvailableFlights(
                depAirport, arrAirport, request.getDepartureDate());

        if(request.isBaggageRequired()){
            options = options.stream()
                    .filter(FlightOption::isIncludesBaggage)
                    .collect(Collectors.toList());
        }

        if (request.isMealRequired()){
            options = options.stream()
                    .filter(FlightOption::isIncludesMeal)
                    .collect(Collectors.toList());
        }

        return new FlightSearchResponse(options);

    }

}
