package ru.otus.homework08.service;

import org.springframework.stereotype.Service;
import ru.otus.homework08.Exception.EmptyFieldException;

import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {
    private Scanner sc = new Scanner(System.in);

    @Override
    public String read() throws EmptyFieldException {
        String text = sc.nextLine();
        if (text.isEmpty()) {
            throw new EmptyFieldException("Error! The entered value must not be empty!");
        }
        return text;
    }

    @Override
    public void write(long count) {
        System.out.println(count);
    }

    @Override
    public void write(Object obj) {
        System.out.println(obj);
    }

}
