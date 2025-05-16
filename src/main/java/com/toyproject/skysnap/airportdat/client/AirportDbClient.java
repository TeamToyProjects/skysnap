package com.toyproject.skysnap.airportdat.client;


import com.toyproject.skysnap.airportdat.dto.AirportResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

// ICAO 코드 이용해 AirportDB api 호출
@Component
public class AirportDbClient {
    @Value("${airportdb.api.token}") // 원래는 "${API_TOKEN}" 테스트해보기
    private String apiToken;

    private final RestTemplate restTemplate;

    public AirportDbClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AirportResponseDto getAirportByIcao(String icao) {
        String url = String.format("https://airportdb.io/api/v1/airport/icao/%s", icao);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiToken);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<AirportResponseDto> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    AirportResponseDto.class
            );
            return response.getBody();

        } catch (Exception e) {
            // 디버깅용
            //System.err.println("공항 정보를 불러오지 못했습니다. (ICAO: " + icao + ")");
            //System.err.println("Reason: " + e.getMessage());
            return null;
        }

    }
}
