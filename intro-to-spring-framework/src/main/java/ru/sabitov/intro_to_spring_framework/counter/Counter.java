package ru.sabitov.intro_to_spring_framework.counter;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Counter {
    private int count = 0;

    public int getCount() {
        return count;
    }

    public void increment() {
        count += 1;
    }
}
