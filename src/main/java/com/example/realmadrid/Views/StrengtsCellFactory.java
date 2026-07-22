package com.example.realmadrid.Views;

import com.example.realmadrid.Controller.Match.MatchCellController;
import com.example.realmadrid.Controller.TeamStats.ClubCharacteristic;
import com.example.realmadrid.Controller.TeamStats.StrengtsCellController;
import com.example.realmadrid.Model.Match;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class StrengtsCellFactory  extends ListCell<ClubCharacteristic> {
    protected void updateItem(ClubCharacteristic clubCharacteristic, boolean empty) {
        super.updateItem(clubCharacteristic, empty);
        if (empty || clubCharacteristic == null) {
            setText(null);
            setGraphic(null);
        }
        else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/realmadrid/Couch/Strengts_WeaknessCell.fxml"));
            StrengtsCellController controller = new StrengtsCellController(clubCharacteristic);
            fxmlLoader.setController(controller);
            setText(null);
            try {
                setGraphic(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

}
