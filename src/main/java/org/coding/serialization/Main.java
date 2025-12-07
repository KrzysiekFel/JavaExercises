package org.coding.serialization;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        LogUtils.info("testing1", "Main");
        LogUtils.debug("testing2", "Main");

        Employee tom = new Employee("Tom", Position.MANAGER, LocalDate.now(), null, 20_000,
                LocalDate.of(1990, 5, 15));
        Employee bob = new Employee("Bob", Position.DEVELOPER, LocalDate.now(), tom, 15_000,
                LocalDate.of(1995, 1, 11));
        Employee jon = new Employee("jon", Position.HR, LocalDate.now(), tom, 9_000,
                LocalDate.of(1980, 7, 30));

        List<Employee> productUnitEmployees = new ArrayList<>();
        productUnitEmployees.add(tom);
        productUnitEmployees.add(bob);
        productUnitEmployees.add(jon);

        BusinessUnit productUnit = new BusinessUnit("Product", tom, productUnitEmployees);
        Path filePath = Path.of("data/serializedFiles/serializedProductUnit2.ser");
        try {
            productUnit.saveBusinessUnit(
                    filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Employee e : productUnit.getEmployees()) {
            System.out.println(e.getName() + e.getStatus());
        }

        try {
            BusinessUnit businessUnit =
                    productUnit.loadBusinessUnit(
                            filePath);
            for (Employee e : businessUnit.getEmployees()) {
                System.out.println(e.getName() + e.getStatus());
            }
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

        try {
            BackupService.createBackupOfFiles(Path.of("data/serializedFiles"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
