package me.fperez.appsencilla.model.persistencia.mariaDB;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DatabaseResourcesTest {

    @Test
    void databaseScriptCreatesClienteWithAutoIncrementAndTenSampleRows() throws IOException {
        String script;
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db/cliente.sql")) {
            assertTrue(input != null, "db/cliente.sql debe estar incluido en los recursos");
            script = new String(input.readAllBytes(), StandardCharsets.UTF_8);
        }

        assertTrue(script.contains("CREATE DATABASE IF NOT EXISTS `java-db`"));
        assertTrue(script.contains(
                "CREATE USER IF NOT EXISTS 'java-db'@'localhost' IDENTIFIED BY 'java-db'"));
        assertTrue(script.contains(
                "GRANT ALL PRIVILEGES ON `java-db`.* TO 'java-db'@'localhost'"));
        assertTrue(script.contains("USE `java-db`"));
        assertTrue(script.contains("CREATE TABLE IF NOT EXISTS cliente"));
        assertTrue(script.contains("ClienteID INT NOT NULL AUTO_INCREMENT"));
        long sampleRows = script.lines()
                .filter(line -> line.startsWith("INSERT INTO cliente"))
                .count();
        assertTrue(sampleRows >= 10, "El script debe incluir al menos 10 filas sintéticas");
    }
}
