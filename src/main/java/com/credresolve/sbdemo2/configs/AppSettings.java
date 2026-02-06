package com.credresolve.sbdemo2.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
@Data // If using Lombok
public class AppSettings {
    private String message;
    private int timeout;
}
