package com.mind.vault.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author caixr
 * @date 2025/10/28 19:45
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "api")
public class ApiConfig {

    private String url;
}
