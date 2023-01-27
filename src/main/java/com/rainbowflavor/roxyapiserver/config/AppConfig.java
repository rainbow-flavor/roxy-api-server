package com.rainbowflavor.roxyapiserver.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private Discord discord;

    @Data
    public static class Discord{
        private String url;
    }
}
