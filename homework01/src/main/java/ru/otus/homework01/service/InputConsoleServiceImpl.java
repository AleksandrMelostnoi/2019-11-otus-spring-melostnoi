package ru.otus.homework01.service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputConsoleServiceImpl implements InputConsoleService {

    private Scanner scanner;

    public InputConsoleServiceImpl() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String getName() {
        return scanner.nextLine();
    }

    @Override
    public Integer getUserAnswer(int maxAnswerNumber) {
        System.out.println("Enter number of correct answer: ");
        Integer answerNumber = null;
        try {
            answerNumber = scanner.nextInt();
            if (1 > answerNumber || answerNumber > maxAnswerNumber) {
                System.out.println("Only number between 1 and " + maxAnswerNumber + "\n");
                return 0;
            }
        } catch (InputMismatchException e) {
            System.out.println("Only number" + "\n");
            return 0;
        }
        return answerNumber;
    }

}
