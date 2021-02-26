package io.github.rahulrajsonu.springsecurityjwt.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ValidatorConfig {

    @Bean("validation-regex")
    @ConfigurationProperties(prefix = "validator.field.regex")
    public Map<String, String> getFieldValidationRegex() {
        return new HashMap();
    }

    @Bean("validation-error")
    @ConfigurationProperties(prefix = "validator.field.error")
    public Map<String, String> getFieldValidationErrorMessage() {
        return new HashMap();
    }

}
