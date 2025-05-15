package com.toyproject.skysnap.airportcsv.service;


import com.toyproject.skysnap.airportcsv.dto.AirportCsvDto;
import com.toyproject.skysnap.airportcsv.dto.AirportDto;
import com.toyproject.skysnap.airportcsv.loader.AirportDataLoader;
import com.toyproject.skysnap.airportcsv.mapper.AirportMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// Spring에 Service Bean으로 등록
@Service
public class AirportService {
    // 공항 데이터를 읽어온 AirportDataLoader을 DI
    private final AirportDataLoader loader;

    public AirportService(AirportDataLoader loader) {
        this.loader = loader;
    }

    // 도시명을 받아 해당 도시의 공항 리스트를 반환
    public List<AirportDto> getAirportByCity(String city) {
        // 도시명을 기준으로 공항 목록을 메모리 맵에서 조회
        // 소문자 변환은 loader에서 처리됨
        List<AirportCsvDto> raw = loader.findByCity(city);

        // 조회한 공항 리스트를 스트림으로 변환
        return raw.stream()
                // iata코드가 있는 공항만 필터링
                .filter(dto -> dto.getIataCode() != null && !dto.getIataCode().isEmpty())
                // AirportCsvDto -> AirportDto로 변환
                .map(AirportMapper::toDto)
                // 변환된 스트림을 리스트로 수집하여 반환
                .collect(Collectors.toList());
    }
}
