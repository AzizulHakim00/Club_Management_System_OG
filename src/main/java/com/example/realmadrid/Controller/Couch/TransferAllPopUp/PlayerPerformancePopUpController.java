package com.example.realmadrid.Controller.Couch.TransferAllPopUp;

import com.example.realmadrid.Model.Player.PlayerPerformance;
import com.example.realmadrid.Model.PlayerPerformanceDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class PlayerPerformancePopUpController {

    @FXML private Button cancel_btn, change_btn, clear_btn, delete_btn, insert_btn, update_btn, upload_btn;
    @FXML private ImageView imageview_fld;
    @FXML private Label lblMessage;
    @FXML private TextField textAssistTotal, textAverageRatings, textEdicalIssues;
    @FXML private TextField textGoalToatal, textSeasonYear, txtApperance, txtClubName, txtId, txtImagePath;
    @FXML private TextField txtName, txtNationality, txtPosition;

    private PlayerPerformanceDAO dao = new PlayerPerformanceDAO();
    private String selectedImagePath = null;

    public void setData(PlayerPerformance p) {
        if (p == null) return;

        txtId.setText(String.valueOf(p.getId()));
        txtName.setText(p.getPlayerName());
        txtClubName.setText(p.getClubName());
        txtNationality.setText(p.getNationality());
        txtApperance.setText(String.valueOf(p.getAppearancesTotal()));
        textGoalToatal.setText(String.valueOf(p.getGoalsTotal()));
        textAssistTotal.setText(String.valueOf(p.getAssistsTotal()));
        textAverageRatings.setText(String.valueOf(p.getAverageRating()));
        textSeasonYear.setText(String.valueOf(p.getSeasonYear()));
     //   textEdicalIssues.setText(p.getMedicalIssues());
        txtImagePath.setText(p.getImagePath());


    }

    @FXML
    public void initialize() {
        // Initialize buttons with handlers
        insert_btn.setOnAction(this::handleInsert);
        update_btn.setOnAction(this::handleUpdate);
        clear_btn.setOnAction(this::handleClear);
        delete_btn.setOnAction(this::handleDelete);
        change_btn.setOnAction(this::onChange);  // Optional manual update button
        upload_btn.setOnAction(this::onUpload);
        cancel_btn.setOnAction(this::onCancel);

        lblMessage.setText(""); // clear message on start
    }

    @FXML
    void handleClear(ActionEvent event) {
        clearFields();
        lblMessage.setText("");
    }

    @FXML
    void handleDelete(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtId.getText());
            dao.delete(id);
            clearFields();
            lblMessage.setText("Deleted successfully.");
        } catch (NumberFormatException e) {
            lblMessage.setText("Please enter a valid ID to delete.");
        } catch (Exception e) {
            lblMessage.setText("Delete Failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void handleInsert(ActionEvent event) {
        try {
            PlayerPerformance p = new PlayerPerformance();
            p.setPlayerName(txtName.getText());
            p.setClubName(txtClubName.getText());
            p.setNationality(txtNationality.getText());
        //    p.setPosition(txtPosition.getText());
            p.setAppearancesTotal(Integer.parseInt(txtApperance.getText()));
            p.setGoalsTotal(Integer.parseInt(textGoalToatal.getText()));
            p.setAssistsTotal(Integer.parseInt(textAssistTotal.getText()));
            p.setAverageRating(Double.parseDouble(textAverageRatings.getText()));
            p.setSeasonYear(Integer.parseInt(textSeasonYear.getText()));
         //   p.setMedicalIssues(textEdicalIssues.getText());
            p.setImagePath(selectedImagePath);

            dao.insert(p);
            clearFields();
            lblMessage.setText("Insert successful.");
        } catch (Exception e) {
            lblMessage.setText("Insert Failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void handleUpdate(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtId.getText());
            PlayerPerformance p = new PlayerPerformance();
            p.setId(id);
            p.setPlayerName(txtName.getText());
            p.setClubName(txtClubName.getText());
            p.setNationality(txtNationality.getText());
          //  p.setPosition(txtPosition.getText());
            p.setAppearancesTotal(Integer.parseInt(txtApperance.getText()));
            p.setGoalsTotal(Integer.parseInt(textGoalToatal.getText()));
            p.setAssistsTotal(Integer.parseInt(textAssistTotal.getText()));
            p.setAverageRating(Double.parseDouble(textAverageRatings.getText()));
            p.setSeasonYear(Integer.parseInt(textSeasonYear.getText()));
         //   p.setMedicalIssues(textEdicalIssues.getText());
            p.setImagePath(selectedImagePath);

            dao.update(p);
            clearFields();
            lblMessage.setText("Update successful.");
        } catch (NumberFormatException e) {
            lblMessage.setText("Please enter a valid ID to update.");
        } catch (Exception e) {
            lblMessage.setText("Update Failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void onUpload(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Player Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            selectedImagePath = file.toURI().toString();
            txtImagePath.setText(selectedImagePath);
            imageview_fld.setImage(new Image(selectedImagePath));
        }
    }

    @FXML
    void onCancel(ActionEvent event) {
        ((Stage) cancel_btn.getScene().getWindow()).close();
    }

    @FXML
    void onChange(ActionEvent event) {
        // Since no TableView, just reload image from path or clear image
        String path = txtImagePath.getText();
        if (path != null && !path.isEmpty()) {
            try {
                imageview_fld.setImage(new Image(path));
                selectedImagePath = path;
            } catch (Exception e) {
                imageview_fld.setImage(null);
                lblMessage.setText("Invalid image path.");
            }
        } else {
            imageview_fld.setImage(null);
        }
    }

    private void clearFields() {
        txtId.clear();
        txtName.clear();
        txtClubName.clear();
        txtNationality.clear();
        txtPosition.clear();
        txtApperance.clear();
        textGoalToatal.clear();
        textAssistTotal.clear();
        textAverageRatings.clear();
        textSeasonYear.clear();
        textEdicalIssues.clear();
        txtImagePath.clear();
        imageview_fld.setImage(null);
        selectedImagePath = null;
    }
}
