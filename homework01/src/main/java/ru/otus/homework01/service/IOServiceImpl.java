package ru.otus.homework01.service;

import java.util.Scanner;

public class IOServiceImpl implements IOService {

    private Scanner scanner;

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
