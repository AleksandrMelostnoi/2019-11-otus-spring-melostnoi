package ru.otus.homework04.service;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homework04.config.LocaleConfig;

@Service
@AllArgsConstructor
public class MessageSourceWrapperImpl implements MessageSourceWrapper {

    private final MessageSource messageSource;
    private final LocaleConfig localeConfig;

    public String getMessage(String source) {
        return messageSource.getMessage(source, null, localeConfig.getLocale());
    }

}
