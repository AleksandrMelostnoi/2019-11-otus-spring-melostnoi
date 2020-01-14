package ru.otus.homework03.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@ConfigurationProperties("application")
@Data
public class LocaleConfig {

    private String csvPath;
    private String locale;

    public Locale getLocale() {
        return Locale.forLanguageTag(locale);
    }

}
