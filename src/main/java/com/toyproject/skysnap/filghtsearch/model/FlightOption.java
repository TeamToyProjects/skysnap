package com.toyproject.skysnap.filghtsearch.model;


import lombok.*;

import java.time.LocalDateTime;


// 항공편 하나의 정보를 표현(항공사,가격,직항,경유,수화물,기내식 여부등)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightOption {
    private String airline;  // 항공사명
    private String FlightNumber;  // 항공편 번호

    private LocalDateTime departureTime;  // 출발 시간
    private LocalDateTime arrivalTime;  // 도착 시간
    private int durationMinutes;  // 비행 시간

    private double price;  // 가격, 달러,유로로 표기 시 유용

    private boolean isDirect;  // 직항 여부
    private boolean includesBaggage;  // 수화물 포함 여부
    private boolean includesMeal;  // 기내식 포함 여부

    private String seatGrade;  // 좌석 등급
    private int availableSeats;  // 잔여 좌석 수

    private boolean childFareApplied; // 어린이 요금 적용 여부
}
