package ru.otus.homework03.service;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homework03.config.LocaleConfig;

@Service
@AllArgsConstructor
public class MessageWrapperImpl implements MessageWrapper {

    private final MessageSource messageSource;
    private final LocaleConfig localeConfig;

    public String getMessage(String source) {
        return messageSource.getMessage(source, null, localeConfig.getLocale());
    }

}
