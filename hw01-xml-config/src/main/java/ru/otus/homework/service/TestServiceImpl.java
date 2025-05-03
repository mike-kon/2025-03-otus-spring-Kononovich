package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import ru.otus.homework.dao.QuestionDao;
import ru.otus.homework.domain.Question;

import java.util.List;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public void executeTest() {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        List<Question> questions = questionDao.findAll();
        questions.forEach(this::printQuest);
    }

    private void printQuest(Question question) {
        ioService.printFormattedLine("Question: [%s]. Possible answers:", question.text());
        question.answers().forEach(x -> ioService.printFormattedLine("  %s.%s",
            x.text(),
            x.isCorrect() ? "[√]" : ""));
    }
}
