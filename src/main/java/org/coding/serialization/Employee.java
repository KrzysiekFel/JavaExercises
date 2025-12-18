package org.coding.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String name;
    private Position position;
    private final LocalDate startDate;
    private Employee reportsTo;
    private float salary;
    private final LocalDate birthDate;
    private Status status;


    public Employee(String name, Position position, LocalDate startDate, Employee reportsTo, float salary, LocalDate birthDate) {
        this.name = name;
        this.position = position;
        this.startDate = startDate;
        this.reportsTo = reportsTo;
        this.salary = salary;
        this.birthDate = birthDate;
        this.status = Status.NEW;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Employee getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(Employee reportsTo) {
        this.reportsTo = reportsTo;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
        LogUtils.info("deserialize Employee" + name, this.getClass().getName());
        in.defaultReadObject();
        if (!status.equals(Status.START)) {
            throw new InvalidEmployeeStatus("Employee status should me START.");
        }
        status = Status.FINISHED;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        LogUtils.info("serialize Employee" + name, this.getClass().getName());
        if (!status.equals(Status.NEW)) {
            throw new InvalidEmployeeStatus("Employee status should me NEW.");
        }
        status = Status.START;
        out.defaultWriteObject();
    }
}
