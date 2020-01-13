package ru.otus.homework03;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework03.service.MessageSourceWrapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class LocalizationServiceTest {

    @Test
    void getMessageTest(@Mock MessageSourceWrapper messageSourceWrapper) {
        Mockito.when(messageSourceWrapper.getMessage("enter.first.name")).thenReturn("Please input first name");
        assertEquals("Please input first name", messageSourceWrapper.getMessage("enter.first.name"));
        Mockito.verify(messageSourceWrapper).getMessage("enter.first.name");
    }

}
