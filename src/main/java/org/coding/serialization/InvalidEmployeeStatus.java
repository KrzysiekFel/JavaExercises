package org.coding.serialization;

public class InvalidEmployeeStatus extends RuntimeException {
    public InvalidEmployeeStatus(String message) {
            super(message);
    }
}
