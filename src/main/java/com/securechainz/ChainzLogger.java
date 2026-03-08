package com.securechainz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ChainzLogger provides structured logging capabilities
 * for the secure-chainz application using Apache Log4j.
 */
public class ChainzLogger {

    private static final Logger appLogger = LogManager.getLogger(ChainzLogger.class);

    /**
     * Logs an informational message about an incoming request.
     *
     * @param endpoint the API endpoint that was requested
     * @param clientId an identifier for the requesting client
     */
    public static void logRequest(String endpoint, String clientId) {
        appLogger.info("Request received on endpoint={} from client={}", endpoint, clientId);
    }

    /**
     * Logs a warning when a request contains unexpected data.
     *
     * @param detail description of the unexpected condition
     */
    public static void logWarning(String detail) {
        appLogger.warn("Unexpected condition detected: {}", detail);
    }

    /**
     * Logs an error with an associated throwable.
     *
     * @param message human-readable error message
     * @param thrown  the exception that caused the error
     */
    public static void logError(String message, Throwable thrown) {
        appLogger.error("Error occurred: {} — cause: {}", message, thrown.getMessage(), thrown);
    }

    /**
     * Entry point that demonstrates logging at various levels.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        appLogger.info("ChainzLogger starting up");

        logRequest("/api/health", "demo-client-001");
        logWarning("Missing optional header X-Trace-Id");

        try {
            // Intentionally trigger an exception to demonstrate error logging
            int result = 42 / 0;
            appLogger.debug("Result: {}", result);
        } catch (ArithmeticException ex) {
            logError("Division by zero in demo routine", ex);
        }

        appLogger.info("ChainzLogger shutting down");
    }
}
