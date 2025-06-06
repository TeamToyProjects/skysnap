package com.toyproject.skysnap.flightsearch.dto;


import com.toyproject.skysnap.flightsearch.model.FlightOption;
import lombok.*;

import java.util.List;

// 검색 결과로 항공편 목록을 담는 응답 DTO
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightSearchResponse {
    List<FlightOption> flightOptions;
}
