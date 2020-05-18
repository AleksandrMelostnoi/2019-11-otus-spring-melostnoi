package ru.otus.homework07.service;

import org.springframework.stereotype.Service;
import ru.otus.homework07.Exception.EmptyFieldException;

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
    public Integer readInt() {
        return sc.nextInt();
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
