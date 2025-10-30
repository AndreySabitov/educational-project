package ru.sabitov;

import com.google.common.base.Strings;

public class Main {
    public static void main(String[] args) {
        validateString(null);
    }

    public static void validateString(String string) {
        System.out.printf("Строка должна быть null или пуста - %s%n", Strings.isNullOrEmpty(string));
    }
}
