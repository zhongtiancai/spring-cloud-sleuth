package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableAutoConfiguration
@RestController
public class Frontend {
  private static Logger logger = LoggerFactory.getLogger(Frontend.class);
  @Autowired
  RestTemplate restTemplate;

  String backendBaseUrl = System.getProperty("spring.example.backendBaseUrl", "http://localhost:9022");

  @RequestMapping("/")
  public String callBackend() {
    logger.info("callbackend");
    return restTemplate.getForObject(backendBaseUrl + "/api", String.class);
  }

  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

  public static void main(String[] args) {
    SpringApplication.run(Frontend.class,
        "--spring.application.name=frontendMQ",
        "--server.port=8082"
    );
  }
}