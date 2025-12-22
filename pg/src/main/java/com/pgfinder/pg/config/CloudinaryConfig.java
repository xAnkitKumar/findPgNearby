package com.pgfinder.pg.config;


import com.cloudinary.Cloudinary;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

import lombok.extern.slf4j.Slf4j;
@Configuration
@Slf4j
public class CloudinaryConfig {

    @Value("${cloudinary.cloud-name:}")
    private String cloudName;

    @Value("${cloudinary.api-key:}")
    private String apiKey;

    @Value("${cloudinary.api-secret:}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        log.info("Configuring Cloudinary with: cloudName={}, apiKey={}", cloudName, apiKey);
        
        if (cloudName.isEmpty() || apiKey.isEmpty() || apiSecret.isEmpty()) {
            log.error("Cloudinary configuration is incomplete!");
            throw new IllegalStateException("Cloudinary configuration is missing");
        }
        
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        config.put("secure", "true");
        
        return new Cloudinary(config);
    }
}