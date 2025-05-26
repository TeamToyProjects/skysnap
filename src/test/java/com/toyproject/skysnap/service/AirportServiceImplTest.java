package com.toyproject.skysnap.service;

import com.toyproject.skysnap.airportdat.client.AirportDbClient;
import com.toyproject.skysnap.airportdat.dto.AirportResponseDto;
import com.toyproject.skysnap.airportdat.dto.CountryDto;
import com.toyproject.skysnap.airportdat.parser.AirportDataParser;
import com.toyproject.skysnap.airportdat.service.AirportServiceImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.Mockito.when;

public class AirportServiceImplTest {
    @Mock
    private AirportDataParser parser;

    @Mock
    private AirportDbClient client;

    @InjectMocks
    private AirportServiceImpl airportService;

    //
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("도시명으로 공항 검색")
    @Test
    public void findAirportsByCity() {
        // given: 도시명에 해당하는 ICAO 코드들과, 각 코드에 대한 공항 정보가 존재할 때
        String cityName = "Seoul";
        // 서울 -> 인천(RKSI), 김포(RKSS) 공항이 결과값
        List<String> mockIcaoCodes = Arrays.asList("RKSI", "RKSS");

        AirportResponseDto airport1 = new AirportResponseDto(
                "Incheon International Airport",
                "ICN",
                "RKSI",
                "Seoul",
                new CountryDto("KR", "South Korea"),
                37.5583,
                126.7906);
        AirportResponseDto airport2 = new AirportResponseDto(
                "Gimpo International Airport",
                "GMP",
                "RKSS",
                "Seoul",
                new CountryDto("KR", "South Korea"),
                37.5583,
                126.7906);
        //  Mockito에서 사용하는 Mock 객체의 행동을 지정하는 코드
        when(parser.findIcaoByCity(cityName)).thenReturn(mockIcaoCodes);
        when(client.getAirportByIcao("RKSI")).thenReturn(airport1);
        when(client.getAirportByIcao("RKSS")).thenReturn(airport2);


        // when
        List<AirportResponseDto> result = airportService.findAirportsByCity(cityName);


        // then
        assertEquals(2, result.size());
        assertEquals("RKSI", result.get(0).getIcao());
        assertEquals("RKSS", result.get(1).getIcao());


//        // then
//        List<AirportResponseDto> expected = Arrays.asList(airport1, airport2);
//        List<AirportResponseDto> actual = airportService.findAirportsByCity(cityName);
//
//        assertEquals(expected.size(), actual.size());
//        assertEquals(expected.get(0).getIcao(), actual.get(0).getIcao());
//        assertEquals(expected.get(1).getIcao(), actual.get(1).getIcao());

    }
}
