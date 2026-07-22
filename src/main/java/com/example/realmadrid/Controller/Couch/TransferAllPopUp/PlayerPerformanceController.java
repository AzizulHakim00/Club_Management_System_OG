// CONTROLLER: PlayerPerformanceController.java
package com.example.realmadrid.Controller.Couch.TransferAllPopUp;

import com.example.realmadrid.Model.Player.PlayerPerformance;

import com.example.realmadrid.Model.PlayerPerformanceDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class PlayerPerformanceController {
    @FXML private TableView<PlayerPerformance> table;
    @FXML private TableColumn<PlayerPerformance, String> colName;
    @FXML private TableColumn<PlayerPerformance, String> colClub;
    @FXML private TextField tfName, tfClub, tfNationality, tfAppearances, tfGoals, tfAssists, tfRating, tfSeasonYear;
    @FXML private ImageView imageView;
    @FXML private Button btnInsert, btnUpdate, btnDelete, btnBrowse;

    private final PlayerPerformanceDAO dao = new PlayerPerformanceDAO();
    private final ObservableList<PlayerPerformance> data = FXCollections.observableArrayList();
    private String selectedImagePath = null;

    @FXML
    public void initialize() {
        colName.setCellValueFactory(cell -> cell.getValue().playerNameProperty());
        colClub.setCellValueFactory(cell -> cell.getValue().clubNameProperty());

        try {
            data.setAll(dao.getAll());
            table.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        table.setOnMouseClicked((MouseEvent event) -> {
            PlayerPerformance p = table.getSelectionModel().getSelectedItem();
            if (p != null) {
                tfName.setText(p.getPlayerName());
                tfClub.setText(p.getClubName());
                tfNationality.setText(p.getNationality());
                tfAppearances.setText(String.valueOf(p.getAppearancesTotal()));
                tfGoals.setText(String.valueOf(p.getGoalsTotal()));
                tfAssists.setText(String.valueOf(p.getAssistsTotal()));
                tfRating.setText(String.valueOf(p.getAverageRating()));
                tfSeasonYear.setText(String.valueOf(p.getSeasonYear()));
                if (p.getImagePath() != null) {
                    try {
                        imageView.setImage(new Image(new FileInputStream(p.getImagePath())));
                        selectedImagePath = p.getImagePath();
                    } catch (FileNotFoundException e) {
                        imageView.setImage(null);
                    }
                }
            }
        });
    }

    @FXML
    private void onBrowse(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Player Image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File file = fileChooser.showOpenDialog(imageView.getScene().getWindow());
        if (file != null) {
            selectedImagePath = file.getAbsolutePath();
            imageView.setImage(new Image(file.toURI().toString()));
        }
    }

    @FXML
    private void onInsert(ActionEvent event) {
        try {
            PlayerPerformance p = new PlayerPerformance();
            p.setPlayerName(tfName.getText());
            p.setClubName(tfClub.getText());
            p.setNationality(tfNationality.getText());
            p.setAppearancesTotal(Integer.parseInt(tfAppearances.getText()));
            p.setGoalsTotal(Integer.parseInt(tfGoals.getText()));
            p.setAssistsTotal(Integer.parseInt(tfAssists.getText()));
            p.setAverageRating(Double.parseDouble(tfRating.getText()));
            p.setSeasonYear(Integer.parseInt(tfSeasonYear.getText()));
            p.setImagePath(selectedImagePath);

            dao.insert(p);
            data.setAll(dao.getAll());
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onUpdate(ActionEvent event) {
        PlayerPerformance selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                selected.setPlayerName(tfName.getText());
                selected.setClubName(tfClub.getText());
                selected.setNationality(tfNationality.getText());
                selected.setAppearancesTotal(Integer.parseInt(tfAppearances.getText()));
                selected.setGoalsTotal(Integer.parseInt(tfGoals.getText()));
                selected.setAssistsTotal(Integer.parseInt(tfAssists.getText()));
                selected.setAverageRating(Double.parseDouble(tfRating.getText()));
                selected.setSeasonYear(Integer.parseInt(tfSeasonYear.getText()));
                selected.setImagePath(selectedImagePath);

                dao.update(selected);
                data.setAll(dao.getAll());
                clearFields();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onDelete(ActionEvent event) {
        PlayerPerformance selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                dao.delete(selected.getId());
                data.setAll(dao.getAll());
                clearFields();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void clearFields() {
        tfName.clear();
        tfClub.clear();
        tfNationality.clear();
        tfAppearances.clear();
        tfGoals.clear();
        tfAssists.clear();
        tfRating.clear();
        tfSeasonYear.clear();
        imageView.setImage(null);
        selectedImagePath = null;
    }
}
