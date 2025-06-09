package com.toyproject.skysnap.airline.service;

import com.toyproject.skysnap.airline.dto.AirlineDto;
import com.toyproject.skysnap.airline.entitiy.Airline;
import com.toyproject.skysnap.airline.mapper.AirlineMapper;
import com.toyproject.skysnap.airline.repository.AirlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirlineService {


    

    private final AirlineRepository airlineRepository;
    private final AirlineMapper airlineMapper;

    public List<AirlineDto> getAllAirlines() {
        return airlineRepository.findAll().stream()
                .map(airlineMapper::airlineToAirlineDto)
                .collect(Collectors.toList());
    }

    public AirlineDto getAirlineByIataCode(String iataCode) {
        Airline airline = airlineRepository.findByIataCode(iataCode)
                .orElseThrow(() -> new RuntimeException("해당 항공사가 없습니다."));
        return airlineMapper.airlineToAirlineDto(airline);
    }

}
