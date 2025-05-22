package com.toyproject.skysnap.client;

import com.toyproject.skysnap.airportdat.client.AirportDbClient;
import com.toyproject.skysnap.airportdat.dto.AirportResponseDto;
import com.toyproject.skysnap.airportdat.dto.CountryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AirportDbClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AirportDbClient client;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // 테스트용 토큰 강제로 설정
        client = new AirportDbClient(restTemplate);
        // 리플렉션으로 apiToken 필드 강제 설정
        try {
            java.lang.reflect.Field tokenField = AirportDbClient.class.getDeclaredField("apiToken");
            tokenField.setAccessible(true);
            tokenField.set(client, "dummy-token");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("정상적인 ICAO 코드 호출 시 응답 객체 반환")
    @Test
    public void getAirportByIcao_success() {
        //given
        String icao = "RKSI";
        AirportResponseDto mockResponse = new AirportResponseDto(
                "Incheon International Airport",
                "ICN",
                "RKSI",
                "Seoul",
                new CountryDto("KR", "South Korea"),
                37.5583,
                126.7906);
        ResponseEntity<AirportResponseDto> responseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                isNull(),
                eq(AirportResponseDto.class)
        )).thenReturn(responseEntity);

        // when
        AirportResponseDto result = client.getAirportByIcao(icao);

        // then
        assertNotNull(result);
        assertEquals("RKSI", result.getIcao());
        assertEquals("Seoul", result.getCity());
    }


    @DisplayName("API 호출 중 예외 발생 시 null 반환")
    @Test
    public void getAirportByIcao_exception() {
        // given
        String icao = "INVALID";

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                isNull(),
                eq(AirportResponseDto.class)
        )).thenThrow(new RuntimeException("API 실패"));


        // when
        AirportResponseDto result = client.getAirportByIcao(icao);

        // then
        assertNull(result);
    }

}
