package br.com.justdoit.apiconsumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class HttpClientConfig {

    @Bean
    RestClient dattebayoRestClient() {
        return RestClient.builder()
                .baseUrl("https://dattebayo-api.onrender.com")
                .build();
    }
}
