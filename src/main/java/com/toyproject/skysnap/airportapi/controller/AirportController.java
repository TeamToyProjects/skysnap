package com.toyproject.skysnap.airportapi.controller;

import com.toyproject.skysnap.airportapi.dto.AirportDto;
import com.toyproject.skysnap.airportapi.service.AirportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


// 사용자가 입력한 도시명 받아 service 호출, 결과 -> 사용자에게 전달
@RestController
@RequestMapping("/api/airports")
public class AirportController {
    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public List<AirportDto> getAirports(@RequestParam String city) {
        return airportService.getAirportsByCity(city);
    }
}
