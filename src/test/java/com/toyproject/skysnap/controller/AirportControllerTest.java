package com.toyproject.skysnap.controller;

import com.toyproject.skysnap.airportdat.controller.AirportController;
import com.toyproject.skysnap.airportdat.dto.AirportResponseDto;
import com.toyproject.skysnap.airportdat.dto.CountryDto;
import com.toyproject.skysnap.airportdat.service.AirportService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(AirportController.class)
public class AirportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirportService airportService;

    @DisplayName("도시명으로 공항 리스트 조회 API 테스트")
    @Test
    public void getAirportsTest() throws Exception{
        // given
        String city = "Seoul";
        CountryDto country = new CountryDto("KR", "South Korea");

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

        List<AirportResponseDto> mockResponse = Arrays.asList(airport1, airport2);

        when(airportService.findAirportsByCity(city)).thenReturn(mockResponse);


        // when & then
        mockMvc.perform(get("/api/airports")
                .param("city", city)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                // ResponseDto에서 JSON 필드명이 icao_code로 되어있음
                .andExpect(jsonPath("$[0].icao_code").value("RKSI"))
                .andExpect(jsonPath("$[1].icao_code").value("RKSS"));

    }
}
