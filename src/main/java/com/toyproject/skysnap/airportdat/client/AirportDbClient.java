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
    @Value("${airportdb.api.token}") // ${API_TOKEN}, ${airportdb.api.token} 둘 다 됨
    private String apiToken;

    private final RestTemplate restTemplate;

    public AirportDbClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AirportResponseDto getAirportByIcao(String icao) {
        // apiToken이 환경변수에 추가됐는지 체크
        if (apiToken == null || apiToken.isEmpty()) {
            throw new IllegalStateException("API 토큰이 없습니다. 환경 변수나 application.yml에 토큰을 설정하세요.");
        }
        // API URL 수정
        String url = String.format("https://airportdb.io/api/v1/airport/%s?apiToken=%s", icao, apiToken);

        try {
            // 더 이상 헤더 필요 없음
            ResponseEntity<AirportResponseDto> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null, // 헤더 필요 X
                    AirportResponseDto.class
            );
            // 빈 응답을 위해 로그 출력
            System.out.println("API 응답: " + response.getBody());

            return response.getBody();

        } catch (Exception e) {
            // 좀 더 자세한 로그 출력
            System.err.printf("ICAO 코드 %s 조회 실패: %s%n", icao, e.getMessage());
            e.printStackTrace();
            return null;
        }

    }
}
