package ru.otus.homework03.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {

    private final Scanner scanner;

    public IOServiceImpl() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String inputText() {
        return scanner.nextLine();
    }

    @Override
    public int inputNumber() {
        return scanner.nextInt();
    }

    @Override
    public void outputText(String text) {
        System.out.println(text);
    }

}
