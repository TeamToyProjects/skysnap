package com.toyproject.skysnap.filghtsearch.dto;


import java.time.LocalDate;

// 사용자 검색 조건을 담는 요청 DTO
public class FlightSearchRequest {
    private String departureCity;  // 출발도시
    private String arriveCity;     // 도착도시

    private LocalDate departureDate;  // 출발일
    private LocalDate returnDate;    // 도착일, 선택 옵션, null 가능

    private int seatCount;  // 좌석수
    private boolean isChild;  // T면 어린이, F면 성인

//    private SeatType seatType; // 좌석등급
    private boolean baggageRequired;
    private boolean mealRequired;

    private Boolean direct;  // T면 직항, F면 경유, null이면 모두 포함




}
