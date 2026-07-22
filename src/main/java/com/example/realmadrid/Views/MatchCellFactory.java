package com.example.realmadrid.Views;

import com.example.realmadrid.Controller.Match.MatchCellController;
import com.example.realmadrid.Model.Match;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class MatchCellFactory extends ListCell<Match> {
    protected void updateItem(Match match, boolean empty) {
        super.updateItem(match, empty);
        if (empty || match == null) {
            setText(null);
            setGraphic(null);
        }
        else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/realmadrid/Admin/MatchCell.fxml"));

            MatchCellController controller = new MatchCellController(match);
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
