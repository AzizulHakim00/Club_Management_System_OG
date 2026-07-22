package com.example.realmadrid.Controller.TeamStats;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class StrengtsCellController implements Initializable {
    public Label nameLabel;


    public Label elseLabel;

    private final ClubCharacteristic clubCharacteristic;

    public StrengtsCellController(ClubCharacteristic clubCharacteristic){
        this.clubCharacteristic = clubCharacteristic;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        setData();
    }
    public void setData() {

        nameLabel.setText(clubCharacteristic.levelTextProperty().get());
        elseLabel.setText(clubCharacteristic.elseTextProperty().get());

        String level = clubCharacteristic.elseTextProperty().get().toLowerCase();
        if (level.contains("very strong")) {
            elseLabel.setStyle("-fx-background-color: #00cc66; -fx-text-fill: white;");
        } else if (level.contains("strong")) {
            elseLabel.setStyle("-fx-background-color: #99ffcc; -fx-text-fill: black;");
        } else if (level.contains("very weak")) {
            elseLabel.setStyle("-fx-background-color: #cc0000; -fx-text-fill: white;");
        } else if (level.contains("weak")) {
            elseLabel.setStyle("-fx-background-color: #ff9999; -fx-text-fill: black;");
        }
        if(level.equals("")){
            elseLabel.setVisible(false);
        }
    }



}
