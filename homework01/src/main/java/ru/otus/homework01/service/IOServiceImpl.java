package ru.otus.homework01.service;

import java.util.Scanner;

public class IOServiceImpl implements IOService {

    private Scanner scanner;

    public IOServiceImpl() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String inputFirstName() {
        return scanner.nextLine();
    }

    @Override
    public String inputLastName() {
        return scanner.nextLine();
    }

    @Override
    public void printEnterFirstName() {
        System.out.println("Please input first name");
    }

    @Override
    public void printEnterLastName() {
        System.out.println("Please input last name");
    }

    @Override
    public void printNumberInputWarningMessage(int number) {
        System.out.println("Only number less or equals " + number + "\n");
    }

    @Override
    public void printEnterAnswerNumberMessage() {
        System.out.println("Enter number of correct answer: ");
    }

    @Override
    public int inputAnswerNumber() {
        return scanner.nextInt();
    }

    @Override
    public void printQuestion(String questionText) {
        System.out.println(questionText);
    }

    @Override
    public void printResult(String resultText) {
        System.out.println(resultText);
    }

}
