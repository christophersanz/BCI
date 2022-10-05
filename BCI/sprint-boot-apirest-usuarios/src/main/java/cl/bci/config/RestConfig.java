package cl.bci.config;

import cl.bci.exception.ApiRestTemplateErrorHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestConfig {

  @Value("${api-duration}")
  private Integer duration;

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    RestTemplate restTemplate = builder
        .setConnectTimeout(Duration.ofMillis(duration))
        .setReadTimeout(Duration.ofMillis(duration))
        .errorHandler(new ApiRestTemplateErrorHandler())
        .build();
    restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    return restTemplate;
  }

}
