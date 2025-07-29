package org.coding.exercise1;

import java.util.List;

public class Student extends Person {
    private int studiesYear;

    public Student(String firstName, String secondName, String pesel, List<Subject> subjects, int studiesYear) {
        super(firstName, secondName, pesel, subjects);
        this.studiesYear = studiesYear;
    }

    public int getStudiesYear() {
        return studiesYear;
    }

    @Override
    public String greet() {
        return String.format("Hello, I'm %dth year student here and my name is: %s %s and I'm %d years old. \n" +
                        "Currently I'm attending to subjects: %s",
                this.getStudiesYear(), this.getFirstName(), this.getSecondName(), this.getAge(), this.getSubjects().toString());
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + this.getFirstName() + '\'' +
                ", secondName='" + this.getSecondName() + "}";
    }
}
