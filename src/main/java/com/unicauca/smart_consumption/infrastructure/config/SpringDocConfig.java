package com.unicauca.smart_consumption.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Open Doc Config.
 *
 * @author juliansmartinez@unicauca.edu.co
 * @version 1.0
 * @since 2024-09-23
 */
@Configuration
public class SpringDocConfig {

  /**
   * API documentation info bean.
   *
   * @author juliansmartinez@unicauca.edu.co
   * @since 2024-09-23
   */
  @Bean
  public OpenAPI apiInfo() {
    return new OpenAPI().info(new Info()
        .title("Backend Documentation - Smart Consumption")
        .version("1.0.0"));
  }

  /**
   * Grouped open api bean.
   *
   * @author juliansmartinez@unicauca.edu.co
   * @since 2024-09-23
   */
  @Bean
  public GroupedOpenApi httpApi() {
    return GroupedOpenApi.builder()
        .group("http")
        .pathsToMatch("/**")
        .build();
  }

}