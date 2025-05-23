package com.toyproject.skysnap.parser;

import com.toyproject.skysnap.airportdat.parser.AirportDataParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class AirportDataParserTest {

    @DisplayName("init() 실행 중 파일이 존재하지 않으면 IOException 발생")
    @Test
    public void InitThrowsIOExceptionTest() {
        // given
        AirportDataParser parser = new AirportDataParser() {
            @Override
            public void init() throws IOException {
                // 존재하지 않는 파일로 강제 설정
                Path path = Paths.get("src/test/resources/invalid_path/airports.dat");
                // 텍스트 파일을 읽기 위한 BufferedReader 객체를 생성
                try (var br = Files.newBufferedReader(path)){
                    br.readLine(); // 실제 파일 읽기 시도
                }
            }
        };

        // when & then
        // parser 객체의 init() 메서드를 호출했을 때,
        // 예외 IOException이 터져야 테스트가 통과
        Assertions.assertThrows(IOException.class, parser::init);
    }
}
