package com.toyproject.skysnap.airportapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// RestTemplate를 Bean으로 등록해 DI 통해 사용

// Bean을 자바 코드로 설정/등록하는 클래스임을 나타냄
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


}
