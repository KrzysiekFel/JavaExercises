package org.coding.library;

import java.time.LocalDate;

public class User {
    private static long nextId = 1;
    private Long id;
    private String name;
    private String surname;
    private LocalDate birthday;

    public User(String name, String surname, LocalDate birthday) {
        this.id = nextId++;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return String.format(
                "User{id=%d, name='%s', surname='%s', birthday=%s}",
                id, name, surname, birthday
        );
    }
}
