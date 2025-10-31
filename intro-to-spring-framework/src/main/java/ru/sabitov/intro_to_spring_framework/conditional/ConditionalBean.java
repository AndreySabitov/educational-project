package ru.sabitov.intro_to_spring_framework.conditional;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(name = "feature.toggle.enable", havingValue = "true")
public class ConditionalBean {

    @PostConstruct
    private void initialLog() {
        log.info("Бин был создан, так как установлена настройка feature.toggle.enable=true");
    }
}
