package ru.otus.homework.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.homework.dao.QuestionDao;
import ru.otus.homework.domain.Answer;
import ru.otus.homework.domain.Question;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class TestServiceImplTest {

  @Test
  void executeTest() {

    QuestionDao questionDao = () -> List.of(
      new Question("text",
        List.of(
          new Answer("one", true),
          new Answer("two", false)
        )));

    var ioService = Mockito.mock(IOService.class);
    var testService = new TestServiceImpl(ioService, questionDao);
    testService.executeTest();
    verify(ioService, times(1)).printFormattedLine(any(), any());
  }
}