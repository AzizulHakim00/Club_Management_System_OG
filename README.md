# Real Madrid Management System

> **Frontend preservation rule:** The original FXML layouts, CSS, navigation, dashboard, formation planner and player-card design are preserved. This version fixes the application behind that frontend; it does not replace the design.

A Java 21 and JavaFX desktop application with coach and administrator dashboards, match history, tactical planning, training, transfer data, player status and performance tools.

## Runtime fixes

- Preserved the original frontend and original images
- Removed the mandatory MySQL/XAMPP setup
- Added an embedded H2 database that creates and seeds itself automatically
- Kept the existing controller and DAO interfaces compatible
- Corrected the Java 21 Maven build and application packaging
- Added database and resource validation tests
- Added a native Windows EXE installer workflow with bundled Java

## Demo login

| Role | Username | Password |
|---|---|---|
| Coach | `xabi` | `1234` |
| Administrator | `admin` | `1234` |

## Run in IntelliJ IDEA

1. Open the repository folder containing `pom.xml` as a Maven project.
2. Select **JDK 21**.
3. Reload Maven dependencies.
4. Run `com.example.realmadrid.Launcher`.

Or run from a terminal:

```bash
mvn clean javafx:run
```

No MySQL server, XAMPP, manual SQL import or separate JavaFX installation is required.

The application stores its embedded database in the user's profile:

```text
%USERPROFILE%\.real-madrid-club-manager\realmadrid.mv.db
```

## Build checks

```bash
mvn clean test package
```

The Windows installer includes its own Java runtime and creates desktop and Start Menu shortcuts.

> Fan-made academic software. It is not affiliated with or endorsed by Real Madrid C.F.
