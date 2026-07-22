package com.example.realmadrid.Model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DatabaseConfigTest {
    @TempDir
    Path tempDir;

    @Test
    void createsAndSeedsEmbeddedDatabase() throws Exception {
        System.setProperty("realmadrid.data.dir", tempDir.toString());
        try (Connection connection = DatabaseConfig.connect();
             Statement statement = connection.createStatement()) {
            try (ResultSet result = statement.executeQuery("SELECT COUNT(*) FROM allplayers")) {
                assertTrue(result.next());
                assertTrue(result.getInt(1) >= 12);
            }
            try (ResultSet result = statement.executeQuery(
                    "SELECT imagePath FROM allplayers WHERE name='Vinícius Júnior'")) {
                assertTrue(result.next());
                assertEquals("images/vinicius.png", result.getString(1));
            }
            try (ResultSet result = statement.executeQuery("SELECT COUNT(*) FROM match_statistics")) {
                assertTrue(result.next());
                assertTrue(result.getInt(1) > 0);
            }
        }
    }
}
