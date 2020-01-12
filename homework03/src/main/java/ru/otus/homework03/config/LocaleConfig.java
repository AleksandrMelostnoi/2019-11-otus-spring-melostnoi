package ru.otus.homework03.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Component
@ConfigurationProperties("application")
@Data
public class LocaleConfig {

    private String csvPath;
    private String language;
    private String country;
    private Locale locale;

    @PostConstruct
    public void init() {
        setLocale(language + "-" + country);
    }

    public void setLocale(String localeId) {
        locale = Locale.forLanguageTag(localeId);
    }

    public Locale getLocale() {
        return locale;
    }

}
