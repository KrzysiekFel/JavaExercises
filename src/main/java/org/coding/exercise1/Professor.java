package org.coding.exercise1;

import java.util.List;

public class Professor extends Person {
    private int experience;

    public Professor(String firstName, String secondName, String pesel, List<Subject> subjects, int experience) {
        super(firstName, secondName, pesel, subjects);
        this.experience = experience;
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public String greet() {
        return String.format("Hello, I'm professor %s %s and I'm %d years old with %d years of experience. \n" +
                        "Currently I'm teaching subjects as: %s",
                this.getFirstName(), this.getSecondName(), this.getAge(), this.getExperience(),
                this.getSubjects().toString());
    }
}
