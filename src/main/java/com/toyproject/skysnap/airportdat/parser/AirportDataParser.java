package com.toyproject.skysnap.airportdat.parser;


import com.toyproject.skysnap.airportdat.model.AirportInfo;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// airports.dat 파일을 읽어 도시명-ICAO 코드 간 매핑 제공
@Component
public class AirportDataParser {
    // 공항 정보를 저장할 리스트를 생성. AirportInfo 객체들을 담는 용도
    private final List<AirportInfo> airportList = new ArrayList<>();

    // 스프링이 빈 생성 후 초기화 작업할 때 실행할 메서드 지정
    @PostConstruct
    // 초기화 메서드 정의 - dat파일 읽는 작업
    public void init() throws IOException {
        // 파일 경로 지정
        Path path = Paths.get("src/main/resources/airports.dat");
        // 파일 읽기 위해 BufferReader 생성
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line; // 한 줄씩 읽기 위해 선언
            // 파일 끝까지 한 줄씩 읽음
            while((line = br.readLine()) != null) {
                String[] tokens = line.split(",");

                // 최소한 필요한 공항 정보가 있는 지 확인 후, 따옴표 제거 후 저장
                if(tokens.length >= 7) {
                    String name = stripQuotes(tokens[1]);
                    String city = stripQuotes(tokens[2]);
                    String country = stripQuotes(tokens[3]);
                    String iata = stripQuotes(tokens[4]);
                    String icao = stripQuotes(tokens[5]);

                    // 공항 정보를 AirportInfo 객체로 만들고 리스트에 추가
                    airportList.add(new AirportInfo(name, city, country, iata, icao));
                }
            }
        }
    }

    // 따옴표 제거
    private String stripQuotes(String s) {
        return s.replaceAll("^\"|\"$", "");

    }

    // 주어진 도시명에 해당하는 ICAO 코드 리스트를 반환하는 메서드
    public List<String> findIcaoByCity(String cityName) {
        List<String> result = airportList.stream()
                .filter(a -> a.getCity().equalsIgnoreCase(cityName))
                .map(AirportInfo::getIcao)
                .filter(code -> code != null && !code.isEmpty())
                .distinct()
                .collect(Collectors.toList());

        System.out.println("도시명: " + cityName + " → ICAO 코드 리스트: " + result);
        return result;
    }

}
