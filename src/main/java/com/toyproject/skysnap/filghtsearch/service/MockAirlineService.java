package com.toyproject.skysnap.filghtsearch.service;

import com.toyproject.skysnap.airportdat.model.AirportInfoParser;
import com.toyproject.skysnap.filghtsearch.model.FlightOption;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Service
public class MockAirlineService {
    public List<FlightOption> getAvailableFlights(AirportInfoParser from,
                                                  AirportInfoParser to,
                                                  LocalDate date) {
        // 출발 시간, 도착 시간 예시 설정
        LocalDateTime depTime1 = LocalDateTime.of(date, LocalTime.of(8, 0));
        LocalDateTime arrTime1 = LocalDateTime.of(date, LocalTime.of(12, 0));

        LocalDateTime depTime2 = LocalDateTime.of(date, LocalTime.of(10, 0));
        LocalDateTime arrTime2 = LocalDateTime.of(date, LocalTime.of(16, 0));

        FlightOption flight1 = FlightOption.builder()
                .airline("MockAir")
                .FlightNumber("MA123")
                .departureTime(depTime1)
                .arrivalTime(arrTime1)
                .durationMinutes(240)
                .price(120.0)
                .isDirect(true)
                .includesBaggage(true)
                .includesMeal(false)
                .seatGrade("Economy")
                .availableSeats(5)
                .childFareApplied(false)
                .build();

        FlightOption flight2 = FlightOption.builder()
                .airline("TestJet")
                .FlightNumber("TJ456")
                .departureTime(depTime2)
                .arrivalTime(arrTime2)
                .durationMinutes(360)
                .price(100.0)
                .isDirect(false)
                .includesBaggage(false)
                .includesMeal(true)
                .seatGrade("Business")
                .availableSeats(3)
                .childFareApplied(true)
                .build();

        return Arrays.asList(flight1, flight2);
    }


}
