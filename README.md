# Real Madrid Club Management System

A JavaFX desktop application for club administration, coach workflows, player management, match previews, training records, transfer operations, performance reports, and database-backed dashboards.

## Technology

- Java 21
- JavaFX 21
- Maven
- MySQL 8
- FXML and CSS
- CalendarFX, Apache POI, iText and Gson

## Important

This is a **desktop application**, not a web application. Vercel, Netlify and Render static hosting cannot run it. GitHub Actions verifies that the source builds; run the application locally with Java and Maven.

## Requirements

1. JDK 21
2. Maven 3.9 or newer
3. MySQL 8
4. Internet access for Maven to download dependencies on the first build

## Database setup

Create the database:

```sql
CREATE DATABASE realmadrid CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
```

Then import `database/real.sql` into the `realmadrid` database. The application reads these optional environment variables:

```text
REAL_MADRID_DB_URL=jdbc:mysql://localhost:3306/realmadrid
REAL_MADRID_DB_USER=root
REAL_MADRID_DB_PASSWORD=password
```

When the variables are absent, the values above are used as local defaults.

## Run

From the repository root:

```bash
mvn clean javafx:run
```

On Windows, run the same command in PowerShell, Command Prompt, or the IntelliJ terminal.

## Build without launching the interface

```bash
mvn --batch-mode --no-transfer-progress -DskipTests clean package
```

## Demo login

- Coach: `xabi` / `1234`
- Admin: `admin` / `1234`

These are demonstration credentials defined in the current application code and should be replaced with database-backed authentication before production use.

## Automated verification

The workflow under `.github/workflows/` installs Java 21 and runs a Maven package build for pushes to the validation branch and pull requests targeting `main`.
