package com.toyproject.skysnap.airportapi.service;

import com.toyproject.skysnap.airportapi.dto.AirportDto;
import com.toyproject.skysnap.airportapi.external.response.AirportApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


// RestTemplate을 사용하여 외부 API로부터 공항 정보를 받아오고,
// 그 정보를 Airport DTO로 변환해 반환
@Service
public class AirportService {
    private final RestTemplate restTemplate;

    public AirportService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Value("${API_KEY}") // yml과 동일한 이름
    private String apiKey;

    // 도시명을 받아 해당 도시에 있는 공항 목록을 반환 매서드
    public List<AirportDto> getAirportsByCity(String city) {
        // aviationstack API에 요청을 보낼 URL 생성
        // 액세스키가 있어서 API에 접근 가능
        // search 파라미터+도시명- 해당 도시 관련 공항 검색(이 사이트가 명시해놓음)
        String url = "https://api.aviationstack.com/v1/airports?access_key=" + apiKey + "&search=" + city;


        // 외부 API 호출 결과를 AirportApiResponse 객체로 받아옴
        AirportApiResponse response = restTemplate.getForObject(url, AirportApiResponse.class);

        // null일 경우 처리(방어 코드)
        if(response == null || response.getData() == null) {
            return Collections.emptyList();
        }

        // API 응답 객체에서 공항 데이터 리스트를 스트림으로 변환
        // 각 공항 정보를 AirportDto 객체로 변환
        return response.getData().stream()
                .map(e -> new AirportDto(
                        e.getAirportName(),
                        e.getIataCode(),
                        e.getIcaoCode(),
                        e.getLatitude(),
                        e.getLongitude(),
                        e.getCountryName(),
                        e.getCityIataCode()
                ))
                .collect(Collectors.toList());
    }
}
