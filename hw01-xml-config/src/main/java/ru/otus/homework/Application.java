package ru.otus.homework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework.service.TestRunnerService;

public class Application {

    private static final String SPRING_CONTEXT_FILE = "/spring-context.xml";

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext(SPRING_CONTEXT_FILE);
        var testRunnerService = context.getBean(TestRunnerService.class);
        testRunnerService.run();

    }
}