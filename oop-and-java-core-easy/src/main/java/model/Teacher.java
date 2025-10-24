package model;

public class Teacher extends Person {

    public Teacher() {

    }

    public Teacher(String name, int age) {
        super(name, age);
    }

    @Override
    public String introduce() {
        return "Hello from Teacher";
    }
}
