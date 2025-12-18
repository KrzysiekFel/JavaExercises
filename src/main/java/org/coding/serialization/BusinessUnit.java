package org.coding.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BusinessUnit implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private Employee headOfUnit;
    private List<Employee> employees;


    public BusinessUnit(String name, Employee headOfUnit, List<Employee> employees) {
        if (!Position.MANAGER.equals(headOfUnit.getPosition())) {
            throw new InvalidHeadOfUnitException("Head of business unit must have MANAGER position.");
        }
        this.name = name;
        this.headOfUnit = headOfUnit;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getHeadOfUnit() {
        return headOfUnit;
    }

    public void setHeadOfUnit(Employee headOfUnit) {
        if (!Position.MANAGER.equals(headOfUnit.getPosition())) {
            throw new InvalidHeadOfUnitException("Head of business unit must have MANAGER position.");
        }
        this.headOfUnit = headOfUnit;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void saveBusinessUnit(Path filePath) throws IOException {
        Path parentPath = filePath.getParent();
        if (Files.notExists(parentPath)) {
            Files.createDirectories(parentPath);
        }

        LogUtils.info("serialize BusinessUnit" + name, this.getClass().getName());
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath.toFile()));
        out.writeObject(this);
    }

    public BusinessUnit loadBusinessUnit(Path filePath) throws ClassNotFoundException, IOException {
        LogUtils.info("deserialize BusinessUnit" + name, this.getClass().getName());
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath.toFile()));
        return (BusinessUnit) in.readObject();
    }
}
