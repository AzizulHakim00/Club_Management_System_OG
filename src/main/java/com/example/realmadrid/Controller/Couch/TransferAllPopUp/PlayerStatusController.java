package com.example.realmadrid.Controller.Couch.TransferAllPopUp;

import com.example.realmadrid.Model.DatabaseConfig;
import com.example.realmadrid.Model.Model;
import com.example.realmadrid.Model.Player.PlayerStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;

public class PlayerStatusController {

    @FXML private TextField playerNameField;
    @FXML private TextField playerClubField;
    @FXML private TextField salaryField;
    @FXML private DatePicker joiningDatePicker;
    @FXML private DatePicker leavingDatePicker;
    @FXML private TextField playerBonusField;
    @FXML private TextField medicalStatusField;
    @FXML private DatePicker leaveInDatePicker;
    @FXML private TextField yellowCardsLALField;
    @FXML private TextField redCardsLALField;
    @FXML private TextField yellowCardsUCLField;
    @FXML private TextField redCardsUCLField;
    @FXML private TextField yellowCardsCDRField;
    @FXML private TextField redCardsCDRField;
    @FXML private TextField yellowCardsUSCField;
    @FXML private TextField redCardsUSCField;
    @FXML private TextField missUpcomingGamesField;
    @FXML private TextField missLALField;
    @FXML private TextField missUCLField;
    @FXML private TextField missCDRField;
    @FXML private TextField missUSCField;
    @FXML private TextArea medicalDescriptionArea;
    @FXML private TextField imagePathField;


    @FXML
    private ImageView imageView;


    @FXML
    private void handleUploadImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(imageView.getScene().getWindow());
        if (selectedFile != null) {
            try {
                Image image = new Image(selectedFile.toURI().toString());
                imageView.setImage(image);
                imagePathField.setText(selectedFile.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    private Connection conn;
    private PlayerStatus playerStatus = new PlayerStatus();

    public void initialize() {


        try {
            conn = DatabaseConfig.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "Could not connect to database.");
        }

        PlayerStatus status = Model.getInstance().getPendingStatus();
        if(status != null){
            this.playerStatus = status;
        }


        bindFieldsToModel();
    }



    private void bindFieldsToModel() {

        playerNameField.textProperty().bindBidirectional(playerStatus.playerNameProperty());
        playerClubField.textProperty().bindBidirectional(playerStatus.playerClubProperty());
        salaryField.textProperty().bindBidirectional(playerStatus.salaryProperty(), new NumberStringConverter());
        joiningDatePicker.valueProperty().bindBidirectional(playerStatus.joiningDateProperty());
        leavingDatePicker.valueProperty().bindBidirectional(playerStatus.leavingDateProperty());
        playerBonusField.textProperty().bindBidirectional(playerStatus.playerBonusProperty(), new NumberStringConverter());
        medicalStatusField.textProperty().bindBidirectional(playerStatus.medicalStatusProperty());
        leaveInDatePicker.valueProperty().bindBidirectional(playerStatus.leaveInDateProperty());
        yellowCardsLALField.textProperty().bindBidirectional(playerStatus.yellowCardsLALProperty(), new NumberStringConverter());
        redCardsLALField.textProperty().bindBidirectional(playerStatus.redCardsLALProperty(), new NumberStringConverter());
        yellowCardsUCLField.textProperty().bindBidirectional(playerStatus.yellowCardsUCLProperty(), new NumberStringConverter());
        redCardsUCLField.textProperty().bindBidirectional(playerStatus.redCardsUCLProperty(), new NumberStringConverter());
        yellowCardsCDRField.textProperty().bindBidirectional(playerStatus.yellowCardsCDRProperty(), new NumberStringConverter());
        redCardsCDRField.textProperty().bindBidirectional(playerStatus.redCardsCDRProperty(), new NumberStringConverter());
        yellowCardsUSCField.textProperty().bindBidirectional(playerStatus.yellowCardsUSCProperty(), new NumberStringConverter());
        redCardsUSCField.textProperty().bindBidirectional(playerStatus.redCardsUSCProperty(), new NumberStringConverter());
        missUpcomingGamesField.textProperty().bindBidirectional(playerStatus.missUpcomingGamesProperty(), new NumberStringConverter());
        missLALField.textProperty().bindBidirectional(playerStatus.missLALProperty(), new NumberStringConverter());
        missUCLField.textProperty().bindBidirectional(playerStatus.missUCLProperty(), new NumberStringConverter());
        missCDRField.textProperty().bindBidirectional(playerStatus.missCDRProperty(), new NumberStringConverter());
        missUSCField.textProperty().bindBidirectional(playerStatus.missUSCProperty(), new NumberStringConverter());
        medicalDescriptionArea.textProperty().bindBidirectional(playerStatus.medicalDescriptionProperty());
        imagePathField.textProperty().bindBidirectional(playerStatus.imagePathProperty());
    }


    @FXML
    private void insertRecord() {
        if (playerStatus.getPlayerName() == null || playerStatus.getPlayerName().trim().isEmpty()) {
            showAlert("Validation Error", "Player Name is required for insert.");
            return;
        }

        String sql = "INSERT INTO playerstatus (playerName, playerClub, salary, joiningDate, leavingDate, playerBonus, medicalStatus, leaveInDate, " +
                "yellowCardsLAL, redCardsLAL, yellowCardsUCL, redCardsUCL, yellowCardsCDR, redCardsCDR, yellowCardsUSC, redCardsUSC, " +
                "missUpcomingGames, missLAL, missUCL, missCDR, missUSC, medicalDescription, imagePath) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, playerStatus.getPlayerName());
            ps.setString(2, playerStatus.getPlayerClub());
            ps.setDouble(3, playerStatus.getSalary());
            ps.setDate(4, toSQLDate(playerStatus.getJoiningDate()));
            ps.setDate(5, toSQLDate(playerStatus.getLeavingDate()));
            ps.setDouble(6, playerStatus.getPlayerBonus());
            ps.setString(7, playerStatus.getMedicalStatus());
            ps.setDate(8, toSQLDate(playerStatus.getLeaveInDate()));
            ps.setInt(9, playerStatus.getYellowCardsLAL());
            ps.setInt(10, playerStatus.getRedCardsLAL());
            ps.setInt(11, playerStatus.getYellowCardsUCL());
            ps.setInt(12, playerStatus.getRedCardsUCL());
            ps.setInt(13, playerStatus.getYellowCardsCDR());
            ps.setInt(14, playerStatus.getRedCardsCDR());
            ps.setInt(15, playerStatus.getYellowCardsUSC());
            ps.setInt(16, playerStatus.getRedCardsUSC());
            ps.setInt(17, playerStatus.getMissUpcomingGames());
            ps.setInt(18, playerStatus.getMissLAL());
            ps.setInt(19, playerStatus.getMissUCL());
            ps.setInt(20, playerStatus.getMissCDR());
            ps.setInt(21, playerStatus.getMissUSC());
            ps.setString(22, playerStatus.getMedicalDescription());
            ps.setString(23, playerStatus.getImagePath());

            int result = ps.executeUpdate();
            if (result > 0) {
                showInfo("Insert Success", "Player inserted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Insert Error", e.getMessage());
        }
    }

    @FXML
    private void updateRecord() {
        if (playerStatus.getPlayerName() == null || playerStatus.getPlayerName().trim().isEmpty()) {
            showAlert("Validation Error", "Player Name is required for update.");
            return;
        }

        String sql = "UPDATE playerstatus SET playerClub=?, salary=?, joiningDate=?, leavingDate=?, playerBonus=?, medicalStatus=?, leaveInDate=?, " +
                "yellowCardsLAL=?, redCardsLAL=?, yellowCardsUCL=?, redCardsUCL=?, yellowCardsCDR=?, redCardsCDR=?, yellowCardsUSC=?, redCardsUSC=?, " +
                "missUpcomingGames=?, missLAL=?, missUCL=?, missCDR=?, missUSC=?, medicalDescription=?, imagePath=? WHERE playerName=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, playerStatus.getPlayerClub());
            ps.setDouble(2, playerStatus.getSalary());
            ps.setDate(3, toSQLDate(playerStatus.getJoiningDate()));
            ps.setDate(4, toSQLDate(playerStatus.getLeavingDate()));
            ps.setDouble(5, playerStatus.getPlayerBonus());
            ps.setString(6, playerStatus.getMedicalStatus());
            ps.setDate(7, toSQLDate(playerStatus.getLeaveInDate()));
            ps.setInt(8, playerStatus.getYellowCardsLAL());
            ps.setInt(9, playerStatus.getRedCardsLAL());
            ps.setInt(10, playerStatus.getYellowCardsUCL());
            ps.setInt(11, playerStatus.getRedCardsUCL());
            ps.setInt(12, playerStatus.getYellowCardsCDR());
            ps.setInt(13, playerStatus.getRedCardsCDR());
            ps.setInt(14, playerStatus.getYellowCardsUSC());
            ps.setInt(15, playerStatus.getRedCardsUSC());
            ps.setInt(16, playerStatus.getMissUpcomingGames());
            ps.setInt(17, playerStatus.getMissLAL());
            ps.setInt(18, playerStatus.getMissUCL());
            ps.setInt(19, playerStatus.getMissCDR());
            ps.setInt(20, playerStatus.getMissUSC());
            ps.setString(21, playerStatus.getMedicalDescription());
            ps.setString(22, playerStatus.getImagePath());
            ps.setString(23, playerStatus.getPlayerName());

            int result = ps.executeUpdate();
            if (result > 0) {
                showInfo("Update Success", "Player updated successfully.");
            } else {
                showAlert("Update Failed", "Player not found or no changes made.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Update Error", e.getMessage());
        }
    }

    @FXML
    private void deleteRecord() {
        if (playerStatus.getPlayerName() == null || playerStatus.getPlayerName().trim().isEmpty()) {
            showAlert("Validation Error", "Player Name is required for deletion.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Delete");
        confirm.setHeaderText("Are you sure you want to delete this player?");
        confirm.setContentText("Player: " + playerStatus.getPlayerName());

        if (confirm.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            String sql = "DELETE FROM playerstatus WHERE playerName = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, playerStatus.getPlayerName());
                int result = ps.executeUpdate();
                if (result > 0) {
                    showInfo("Delete Success", "Player deleted successfully.");
                    clearFields();
                } else {
                    showAlert("Delete Failed", "Player not found.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Delete Error", e.getMessage());
            }
        }
    }

    @FXML
    private void clearFields() {
        playerNameField.clear();
        playerClubField.clear();
        salaryField.clear();
        joiningDatePicker.setValue(null);
        leavingDatePicker.setValue(null);
        playerBonusField.clear();
        medicalStatusField.clear();
        leaveInDatePicker.setValue(null);
        yellowCardsLALField.clear();
        redCardsLALField.clear();
        yellowCardsUCLField.clear();
        redCardsUCLField.clear();
        yellowCardsCDRField.clear();
        redCardsCDRField.clear();
        yellowCardsUSCField.clear();
        redCardsUSCField.clear();
        missUpcomingGamesField.clear();
        missLALField.clear();
        missUCLField.clear();
        missCDRField.clear();
        missUSCField.clear();
        medicalDescriptionArea.clear();
        imagePathField.clear();
    }

    @FXML
    private void handleInsert(ActionEvent actionEvent) {
        insertRecord();
    }

    @FXML
    private void handleUpdate(ActionEvent actionEvent) {
        updateRecord();
    }

    @FXML
    private void handleDelete(ActionEvent actionEvent) {
        deleteRecord();
    }

    @FXML
    private void handleClear(ActionEvent actionEvent) {
        clearFields();
    }

    @FXML
    private void handleExit(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    private java.sql.Date toSQLDate(LocalDate date) {
        return (date == null) ? null : java.sql.Date.valueOf(date);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}



//FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/playerstatus.fxml"));
//Parent root = loader.load();
//
//PlayerStatusController controller = loader.getController();
//controller.setPlayerStatus(selectedPlayer); // load player data into popup
//
//Stage stage = new Stage();
//stage.setScene(new Scene(root));
//        stage.show();
