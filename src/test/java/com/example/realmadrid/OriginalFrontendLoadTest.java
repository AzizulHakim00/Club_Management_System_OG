package com.example.realmadrid;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.io.TempDir;

import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertTrue;

@EnabledIfSystemProperty(named = "runFxSmokeTests", matches = "true")
class OriginalFrontendLoadTest {
    private static final List<String> SCREENS = List.of(
            "/com/example/realmadrid/Login.fxml",
            "/com/example/realmadrid/MainView.fxml",
            "/com/example/realmadrid/Couch/Couch.fxml",
            "/com/example/realmadrid/Couch/CouchDashboard.fxml",
            "/com/example/realmadrid/Couch/CouchMenu.fxml",
            "/com/example/realmadrid/Couch/GamePlan.fxml",
            "/com/example/realmadrid/Couch/MatchPreview.fxml",
            "/com/example/realmadrid/Couch/Training.fxml",
            "/com/example/realmadrid/Couch/TransferMarket.fxml",
            "/com/example/realmadrid/Couch/Strengts_WeaknessCell.fxml",
            "/com/example/realmadrid/Couch/transferallpopup/FifaRatingsPopUp.fxml",
            "/com/example/realmadrid/Couch/transferallpopup/PlayerPerformanceFull.fxml",
            "/com/example/realmadrid/Couch/transferallpopup/PlayerStatusFull.fxml",
            "/com/example/realmadrid/Couch/transferallpopup/playerPerformancePopup.fxml",
            "/com/example/realmadrid/Couch/transferallpopup/player_status_popUp.fxml",
            "/com/example/realmadrid/Couch/transferallpopup/saveToallPlayers.fxml",
            "/com/example/realmadrid/Couch/transferallpopup/traingpopup.fxml",
            "/com/example/realmadrid/Couch/transferallpopup/transferInsert.fxml",
            "/com/example/realmadrid/Admin/Admin.fxml",
            "/com/example/realmadrid/Admin/AdminDashBoard.fxml",
            "/com/example/realmadrid/Admin/AdminMenu.fxml",
            "/com/example/realmadrid/Admin/AllMatches.fxml",
            "/com/example/realmadrid/Admin/AllPlayerAndFifa.fxml",
            "/com/example/realmadrid/Admin/MatchCell.fxml",
            "/com/example/realmadrid/Admin/PlayerPerformance.fxml",
            "/com/example/realmadrid/Admin/Preview.fxml",
            "/com/example/realmadrid/Admin/viewPlayerStatus.fxml",
            "/com/example/realmadrid/Player/Fifa.fxml",
            "/com/example/realmadrid/PlayerCard.fxml",
            "/com/example/realmadrid/player-edit-dialog.fxml"
    );

    @TempDir
    Path dataDirectory;

    @BeforeAll
    static void startJavaFx() throws Exception {
        CountDownLatch started = new CountDownLatch(1);
        try {
            Platform.startup(started::countDown);
        } catch (IllegalStateException alreadyStarted) {
            started.countDown();
        }
        assertTrue(started.await(20, TimeUnit.SECONDS), "JavaFX toolkit did not start");
    }

    @Test
    void loadsEveryOriginalScreen() throws Exception {
        System.setProperty("realmadrid.data.dir", dataDirectory.toString());
        List<AssertionError> failures = new ArrayList<>();
        for (String screen : SCREENS) {
            URL resource = getClass().getResource(screen);
            if (resource == null) {
                failures.add(new AssertionError("Missing original FXML resource: " + screen));
                continue;
            }
            AtomicReference<Throwable> failure = new AtomicReference<>();
            CountDownLatch loaded = new CountDownLatch(1);
            Platform.runLater(() -> {
                try {
                    new FXMLLoader(resource).load();
                } catch (Throwable throwable) {
                    failure.set(throwable);
                } finally {
                    loaded.countDown();
                }
            });
            if (!loaded.await(30, TimeUnit.SECONDS)) {
                failures.add(new AssertionError("Timed out loading original screen: " + screen));
            } else if (failure.get() != null) {
                failures.add(new AssertionError("Failed to load original screen: " + screen, failure.get()));
            }
        }
        if (!failures.isEmpty()) {
            AssertionError combined = new AssertionError("Original frontend runtime failures: " + failures.size());
            failures.forEach(combined::addSuppressed);
            throw combined;
        }
    }
}
