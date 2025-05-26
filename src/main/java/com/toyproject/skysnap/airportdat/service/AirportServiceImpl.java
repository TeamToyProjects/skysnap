package com.toyproject.skysnap.airportdat.service;

import com.toyproject.skysnap.airportdat.client.AirportDbClient;
import com.toyproject.skysnap.airportdat.dto.AirportResponseDto;
import com.toyproject.skysnap.airportdat.parser.AirportDataParser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


// 도시명으로 공항 정보를 조회 - 실직적 서비스 로직
@Service
public class AirportServiceImpl implements AirportService {

    private final AirportDataParser parser;
    private final AirportDbClient client;

    public AirportServiceImpl(AirportDataParser parser, AirportDbClient client) {
        this.parser = parser;
        this.client = client;
    }

    @Override
    public List<AirportResponseDto> findAirportsByCity(String cityName) {
        List<String> icaoCodes = parser.findIcaoByCity(cityName);

        return icaoCodes.stream()
                .map(client::getAirportByIcao)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
