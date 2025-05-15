package com.toyproject.skysnap.airportapi.external.response;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

// 외부 API 응답 전체 구조(리스트)
@Getter
@Setter
public class AirportApiResponse {
    private List<AirportExternalDto> data;

}
