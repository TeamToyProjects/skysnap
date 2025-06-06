package com.toyproject.skysnap.flightsearch.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

// 사용자 검색 조건을 담는 요청 DTO
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightSearchRequest {
    @NotBlank  // 공백, 빈문자열, null 이면 안됨
    private String departureCity;  // 출발도시
    @NotBlank
    private String arriveCity;     // 도착도시

    @NotNull
    private LocalDate departureDate;  // 출발일
    private LocalDate returnDate;    // 도착일, 선택 옵션, null 가능

    @Min(1)
    private int seatCount;  // 좌석수
    private boolean isChild;  // T면 어린이, F면 성인

//    private SeatGrade seatGrade; // 좌석등급
    private boolean baggageRequired;
    private boolean mealRequired;
    private Boolean direct;  // T면 직항, F면 경유, null이면 모두 포함

}
