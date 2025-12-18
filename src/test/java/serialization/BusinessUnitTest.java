package serialization;

import java.io.IOException;

import org.coding.serialization.BusinessUnit;
import org.coding.serialization.Employee;
import org.coding.serialization.Position;
import org.coding.serialization.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BusinessUnitTest {
    private static BusinessUnit bu;
    private static Employee tom;
    private static Employee bob;
    private static Employee jon;

    @BeforeEach
    public void beforeEachTest() {
        tom = new Employee("Tom", Position.MANAGER, LocalDate.now(), null, 20_000,
                LocalDate.of(1990, 5, 15));
        bob = new Employee("Bob", Position.DEVELOPER, LocalDate.now(), tom, 15_000,
                LocalDate.of(1995, 1, 11));
        jon = new Employee("jon", Position.HR, LocalDate.now(), tom, 9_000,
                LocalDate.of(1980, 7, 30));

        List<Employee> productUnitEmployees = new ArrayList<>();
        productUnitEmployees.add(tom);
        productUnitEmployees.add(bob);
        productUnitEmployees.add(jon);

        bu = new BusinessUnit("Product", tom, productUnitEmployees);
    }

    static Stream<Employee> employeeProvider() {
        BusinessUnitTest testInstance = new BusinessUnitTest();
        testInstance.beforeEachTest();
        return Stream.of(testInstance.tom, testInstance.bob, testInstance.jon);
    }

    @ParameterizedTest
    @MethodSource("employeeProvider")
    void createEmployeeAndSetStatusToNew(Employee employee) {
        assertEquals(Status.NEW, employee.getStatus());
    }

    @Test
    void createEmployeeAndSetStatusToNew() {
        assertEquals(Status.NEW, tom.getStatus());
        assertEquals(Status.NEW, bob.getStatus());
        assertEquals(Status.NEW, jon.getStatus());
    }

    @Test
    void writeObjectChangesEmployeeStatusToStart() throws IOException {
        bu.saveBusinessUnit(Files.createTempFile("businessUnit", ".ser"));

        Status tomResult = tom.getStatus();
        Status bobResult = bob.getStatus();
        Status jonResult = jon.getStatus();

        assertEquals(Status.START, tomResult);
        assertEquals(Status.START, bobResult);
        assertEquals(Status.START, jonResult);
    }

    @Test
    void readObjectChangesEmployeeStatusToFinished() throws IOException, ClassNotFoundException {
        Path resourceFile = Path.of("src/test/resources/serializedProductUnit.ser");
        BusinessUnit deserialized = new BusinessUnit("dummy", tom, List.of())
                .loadBusinessUnit(resourceFile);
        List<Employee> deserializedEmployees = deserialized.getEmployees();

        Status statusResult0 = deserializedEmployees.get(0).getStatus();
        Status statusResult1 = deserializedEmployees.get(1).getStatus();
        Status statusResult2 = deserializedEmployees.get(2).getStatus();

        assertEquals(Status.FINISHED, statusResult0);
        assertEquals(Status.FINISHED, statusResult1);
        assertEquals(Status.FINISHED, statusResult2);
    }

    @Test
    void settingHeadOfUnitDifferentThanManagerNotAllowed() {
        Employee developer =
                new Employee("Kris", Position.DEVELOPER, LocalDate.now(), tom, 15_000,
                        LocalDate.of(1995, 1, 11));
        List<Employee> employees = new ArrayList<>();
        employees.add(developer);

        RuntimeException exception =
                assertThrows(RuntimeException.class,
                        () -> new BusinessUnit("Product", developer, employees));
        assertEquals("Head of business unit must have MANAGER position.", exception.getMessage());
    }

}
