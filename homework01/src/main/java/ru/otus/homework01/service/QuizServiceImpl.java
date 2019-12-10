package ru.otus.homework01.service;

import ru.otus.homework01.dao.QuizDao;
import ru.otus.homework01.exception.QuizDataFormatException;
import ru.otus.homework01.model.Question;
import ru.otus.homework01.model.Student;
import ru.otus.homework01.model.TestingResult;

import java.util.List;

import static ru.otus.homework01.util.Util.checkAnswer;
import static ru.otus.homework01.util.Util.question2String;

class QuizServiceImpl implements QuizService {

    private final UIService uiService;
    private final QuizDao quizDao;

    QuizServiceImpl(UIService uiService, QuizDao quizDao) {
        this.quizDao = quizDao;
        this.uiService = uiService;
    }

    @Override
    public void startQuiz() throws QuizDataFormatException {
        Student student = uiService.readStudentInfo();
        List<Question> questions = quizDao.readQuizzes();
        int correctAnswers = 0;
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            uiService.printQuestion(i + 1, question2String(question));
            int userAnswer = uiService.getUserAnswer(question.getAnswerList().size());
            if (userAnswer <= 0) {
                continue;
            }
            if (checkAnswer(question, userAnswer)) {
                correctAnswers++;
            }
        }
        uiService.printResult(new TestingResult(student, correctAnswers, questions.size()));
    }

}
