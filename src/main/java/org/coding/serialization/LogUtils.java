package org.coding.serialization;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LogUtils {

    private LogUtils() {
    }

    private static final String LOG_FILE = "logs.txt";

    private static void log(String message, LogLevel logLevel, String className) {
        String logLine = logLevel.name() + ": [" + LocalDateTime.now() + "] [" + className + "] :" +
                message + "\n";

        try (FileWriter fileWriter = new FileWriter(LOG_FILE, true)) {
            fileWriter.write(logLine);
        } catch (IOException e) {
            System.out.println("IOException caught");
        }
    }

    public static void info(String message, String className) {
        log(message, LogLevel.INFO, className);
    }

    public static void debug(String message, String className) {
        log(message, LogLevel.DEBUG, className);
    }

    public static void warn(String message, String className) {
        log(message, LogLevel.WARN, className);
    }
}
