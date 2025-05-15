package com.toyproject.skysnap.airportcsv.controller;


import com.toyproject.skysnap.airportcsv.dto.AirportDto;
import com.toyproject.skysnap.airportcsv.service.AirportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


// 이 클래스가 REST API컨트롤러임을 선언
// 내부 메서드는 JSON으로 응답
@RestController
@RequestMapping("/airports") // 기본 URL 경로 설정
public class AirportController {
    // 비지니스 로직을 처리하기 위해 DI
    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    // Get 요청 경로 설정
    @GetMapping("/search")
    public ResponseEntity<List<AirportDto>> searchAirports(@RequestParam String city) {
        // 서비스에서 도시명으로 공항 리스트 조회
        List<AirportDto> airports = airportService.getAirportByCity(city);
        if(airports.isEmpty()) {
            // 디버깅용
            System.out.println("도시명에 해당하는 공항을 찾을 수 없습니다. " + city);
        }

        // 결과 리스트를 HTTP 200 OK 상태로 응답
        return ResponseEntity.ok(airports);

    }
}