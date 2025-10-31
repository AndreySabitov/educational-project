package ru.sabitov.intro_to_spring_framework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sabitov.intro_to_spring_framework.counter.Counter;

@RestController
@Slf4j
public class CounterController {
    private final ApplicationContext context;

    public CounterController(ApplicationContext context) {
        this.context = context;
    }

    @GetMapping("/counter")
    public void testCount() {
        Counter counter1 = context.getBean(Counter.class);
        Counter counter2 = context.getBean(Counter.class);

        log.info("counter1 и counter2 - 2 разных объекта");
        log.info("Счётчик counter1 = {}", counter1.getCount());
        log.info("Счётчик counter2 = {}", counter2.getCount());
        counter1.increment();
        log.info("Увеличили счётчик для counter1. Теперь его count = {}", counter1.getCount());
        log.info("При этом счётчик для counter2 остался = {}", counter2.getCount());
    }
}
