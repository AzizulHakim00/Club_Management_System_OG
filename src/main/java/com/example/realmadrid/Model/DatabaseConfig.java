package com.example.realmadrid.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Centralized database configuration with environment-variable overrides. */
public final class DatabaseConfig {
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/realmadrid";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASSWORD = "password";

    private DatabaseConfig() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                env("REAL_MADRID_DB_URL", DEFAULT_URL),
                env("REAL_MADRID_DB_USER", DEFAULT_USER),
                env("REAL_MADRID_DB_PASSWORD", DEFAULT_PASSWORD)
        );
    }

    private static String env(String name, String fallback) {
        String value = System.getenv(name);
        return value == null || value.isBlank() ? fallback : value;
    }
}
