package org.coding.exercise1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public abstract class Person {
    private final String firstName;
    private final String secondName;
    private final String pesel;
    private int age;
    private final List<Subject> subjects;

    protected Person(String firstName, String secondName, String pesel, List<Subject> subjects) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.pesel = pesel;
        this.age = this.getAgeFromPesel();
        this.subjects = subjects;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getAge() {
        return age;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public abstract String greet();

    private int getAgeFromPesel() {
        int year = Integer.parseInt(this.pesel.substring(0, 2));
        int month = Integer.parseInt(this.pesel.substring(2, 4));
        int day = Integer.parseInt(this.pesel.substring(4, 6));
        int century = month >= 1 && month <= 12 ? 1900 : 2000;
        year += century;

        LocalDate birthday = LocalDate.of(year, month, day);
        LocalDate today = LocalDate.now();

        return (int) ChronoUnit.YEARS.between(birthday, today);
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }

    public void removeSubject(Subject subject) {
        this.subjects.remove(subject);
    }

}
