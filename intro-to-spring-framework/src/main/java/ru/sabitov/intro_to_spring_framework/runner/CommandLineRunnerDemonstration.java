package ru.sabitov.intro_to_spring_framework.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.sabitov.intro_to_spring_framework.service.MessageService;

@Slf4j
@Component
public class CommandLineRunnerDemonstration implements CommandLineRunner {
    private final MessageService messageService;

    public CommandLineRunnerDemonstration(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void run(String... args) {
        log.info("Выводим сообщение после запуска приложения: {}", messageService.getMessageFromRepository());
    }
}
