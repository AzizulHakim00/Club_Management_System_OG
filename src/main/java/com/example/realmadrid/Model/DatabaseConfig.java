package com.example.realmadrid.Model;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Embedded database configuration. The original controllers keep using the
 * same JDBC API, but the application no longer requires MySQL or XAMPP.
 */
public final class DatabaseConfig {
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static volatile String initializedUrl;

    private DatabaseConfig() {
    }

    public static String url() {
        String url = databaseUrl();
        ensureInitialized(url);
        return url;
    }

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url(), USER, PASSWORD);
    }

    public static String username() {
        return USER;
    }

    public static String password() {
        return PASSWORD;
    }

    public static Path dataDirectory() {
        String override = System.getProperty("realmadrid.data.dir");
        Path path = override == null || override.isBlank()
                ? Path.of(System.getProperty("user.home"), ".real-madrid-club-manager")
                : Path.of(override);
        try {
            Files.createDirectories(path);
        } catch (Exception exception) {
            throw new IllegalStateException("Could not create application data directory: " + path, exception);
        }
        return path;
    }

    private static String databaseUrl() {
        String file = dataDirectory().resolve("realmadrid").toAbsolutePath().toString().replace('\\', '/');
        return "jdbc:h2:file:" + file
                + ";MODE=MySQL;DATABASE_TO_LOWER=TRUE;DEFAULT_NULL_ORDERING=HIGH;AUTO_SERVER=TRUE";
    }

    private static void ensureInitialized(String url) {
        if (url.equals(initializedUrl)) {
            return;
        }
        synchronized (DatabaseConfig.class) {
            if (url.equals(initializedUrl)) {
                return;
            }
            try {
                Class.forName("org.h2.Driver");
                try (Connection connection = DriverManager.getConnection(url, USER, PASSWORD)) {
                    if (!hasCoreSchema(connection)) {
                        try (Statement statement = connection.createStatement()) {
                            statement.execute("RUNSCRIPT FROM 'classpath:database/real_h2.sql' CHARSET 'UTF-8'");
                        }
                    }
                }
                initializedUrl = url;
            } catch (Exception exception) {
                throw new IllegalStateException("Could not initialize the embedded Real Madrid database.", exception);
            }
        }
    }

    private static boolean hasCoreSchema(Connection connection) {
        try (ResultSet tables = connection.getMetaData()
                .getTables(null, null, "allplayers", new String[]{"TABLE"})) {
            return tables.next();
        } catch (SQLException exception) {
            return false;
        }
    }
}
