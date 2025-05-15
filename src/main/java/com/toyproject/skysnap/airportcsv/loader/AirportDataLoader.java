package com.toyproject.skysnap.airportcsv.loader;


import com.toyproject.skysnap.airportcsv.dto.AirportCsvDto;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;


import java.io.*;
import java.util.*;

import org.apache.commons.csv.*;

// CSV 파일 로딩 및 맵 저장
@Component // Bean으로 등록
public class AirportDataLoader {

    //도시명(소문자)-key, 해당 도시 공항 리스트-value 저장
    private final Map<String, List<AirportCsvDto>> cityAirportMap = new HashMap<>();

    // Spring이 해당 Bean 생성 후 자동으로 loadCsv() 호출
    @PostConstruct
    private void loadCsv() throws IOException {

        // CSV 파일을 InputStream을 읽어옴
        InputStream is = getClass().getResourceAsStream("/airports.csv");
        // 바이트(숫자)를 글자로 변환
        Reader in = new InputStreamReader(is);
        // CSV parser
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withFirstRecordAsHeader().parse(in);

        // CSV 파일의 값들에 대해 반복(한줄씩)
        for (CSVRecord record : records){
            try {
                // 공항명, 도시명 등 필요한값 가져오고, trim() 양쪽 공백 제거
                String name = record.get(3).trim();
                String municipality = record.get(10).trim();
                String isoCountry = record.get(8).trim();
                String iataCode = record.get(13).trim();
                String icaoCode = record.get(11).trim();
                double latitude = parseDouble(record.get(4));
                double longitude = parseDouble(record.get(5));

                // 도시 정보 없으면 건너뜀
                if (municipality.isEmpty()) continue;

                // DTO 객체 생성, 필드들 설정
                AirportCsvDto dto = new AirportCsvDto();
                // 해당 공항 정보를 DTO에 저장
                dto.setName(name);
                dto.setMunicipality(municipality);
                dto.setIsoCountry(isoCountry);
                dto.setIataCode(iataCode);
                dto.setIcaoCode(icaoCode);
                dto.setLatitudeDeg(latitude);
                dto.setLongitudeDeg(longitude);

                // airport가 이름에 포함된 경우만 추가
                if (dto.getName().toLowerCase().contains("airport")) {
                    // 도시명-key, 공항 리스트에 추가
                    String cityKey = municipality.toLowerCase();
                    // computeIfAbsent는 key가 없으면 새 리스트를 만듦
                    cityAirportMap.computeIfAbsent(cityKey, k -> new ArrayList<>()).add(dto);
                }
            } catch (Exception e){
                System.out.println("파싱 중 오류 발생: " + e.getMessage());
            }
        }
        System.out.println("CSV 공항 데이터 로딩 완료. 도시 수: " + cityAirportMap.size());

    }

    // 검색 메서드
    // 소문자 도시 이름으로 맵에서 공항 리스트를 조회
    // 없으면 빈 리스트 반환
    public List<AirportCsvDto> findByCity(String city) {
        return cityAirportMap.getOrDefault(city.toLowerCase(), Collections.emptyList());
    }

    // 문자열 -> 숫자로 변환하는 유틸리티
    private double parseDouble(String val) {
        try {
            // 문자열 양쪽 공백 제거 후,
            // String -> double 변환
            return Double.parseDouble(val.trim());
        } catch (Exception e){
            // 만약 위 파싱이 실패하면
            //(예: 빈문자열, N/A, 숫자아님) 예외를 캐치
            System.out.println("숫자 파싱 실패: " + val);
            // 기본값 0.0을 반환해 버그를 막음
            return 0.0;
        }

    }




}
