package ru.otus.homework04;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.homework04.model.Student;
import ru.otus.homework04.service.IOService;
import ru.otus.homework04.service.UIService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UIServiceTest {

    @MockBean
    IOService ioService;

    @Autowired
    UIService uiService;

    private String studentName = UUID.randomUUID().toString();

    @Test
    void getUserAnswerTest() {
        given(ioService.inputText()).willReturn(studentName);
        Student student = uiService.readStudentInfo();
        assertEquals(studentName, student.getFirstName());
        assertEquals(studentName, student.getLastName());
    }

}
