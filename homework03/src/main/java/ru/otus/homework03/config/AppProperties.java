package ru.otus.homework03.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@ConfigurationProperties("application")
@Data
public class AppProperties {

    private String csvPath;
    private String language;
    private String country;

    @Bean
    public Locale locale() {
        return Locale.forLanguageTag(language + "-" + country);
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("bundle");
        source.setDefaultEncoding("UTF-8");
        return source;
    }

}
