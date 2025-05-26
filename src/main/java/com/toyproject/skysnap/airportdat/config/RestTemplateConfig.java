package com.toyproject.skysnap.airportdat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// RestTemplate을 Bean으로 등록해 API 통신에 사용할 수 있도록 설정
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
