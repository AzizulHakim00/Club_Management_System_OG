package com.example.realmadrid;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ResourceIntegrityTest {
    @Test
    void originalFrontendResourcesRemainAvailable() {
        List<String> resources = List.of(
                "/com/example/realmadrid/Login.fxml",
                "/com/example/realmadrid/Couch/Couch.fxml",
                "/com/example/realmadrid/Couch/CouchDashboard.fxml",
                "/com/example/realmadrid/Couch/GamePlan.fxml",
                "/com/example/realmadrid/Couch/Training.fxml",
                "/com/example/realmadrid/Couch/TransferMarket.fxml",
                "/com/example/realmadrid/Admin/Admin.fxml",
                "/com/example/realmadrid/Admin/AdminDashBoard.fxml",
                "/com/example/realmadrid/Style.css",
                "/com/example/realmadrid/Style/DashBoard.css",
                "/com/example/realmadrid/Images/stadium.jpeg",
                "/images/vinicius.png",
                "/images/default.png",
                "/database/real_h2.sql"
        );
        for (String resource : resources) {
            assertNotNull(getClass().getResource(resource), "Missing original resource: " + resource);
        }
    }
}
