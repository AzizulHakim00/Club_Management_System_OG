package com.example.realmadrid.Controller.Couch.TransferAllPopUp;

import com.example.realmadrid.Model.DatabaseConfig;
import com.example.realmadrid.Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Controller for the existing full player-performance form. */
public class PlayerPerformanceFullController {
    @FXML private TextField playerNameField;
    @FXML private TextField playerClubField;
    @FXML private TextField nationalityField;
    @FXML private TextField appearancesTotalField;
    @FXML private TextField appearancesLaLigaField;
    @FXML private TextField goalsLaLigaField;
    @FXML private TextField assistsLaLigaField;
    @FXML private TextField appearancesChampionsField;
    @FXML private TextField goalsChampionsField;
    @FXML private TextField assistsChampionsField;
    @FXML private TextField appearancesUefaField;
    @FXML private TextField goalsUefaField;
    @FXML private TextField assistsUefaField;
    @FXML private TextField appearancesCopaField;
    @FXML private TextField goalsCopaField;
    @FXML private TextField assistsCopaField;
    @FXML private TextField goalsTotalField;
    @FXML private TextField assistsTotalField;
    @FXML private TextField manOfMatchField;
    @FXML private TextField averageRatingField;
    @FXML private TextField seasonYearField;
    @FXML private TextField imagePathField;
    @FXML private TextArea medicalDescriptionArea;
    @FXML private ImageView imageView;

    @FXML
    private void initialize() {
        String selectedName = Model.getInstance().getPlayerName();
        if (selectedName != null && !selectedName.isBlank()) {
            loadPlayer(selectedName);
        }
    }

    @FXML
    private void handleUploadImage(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Player Image");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File file = chooser.showOpenDialog(imageView.getScene().getWindow());
        if (file != null) {
            String uri = file.toURI().toString();
            imagePathField.setText(uri);
            imageView.setImage(new Image(uri, true));
        }
    }

    @FXML
    private void handleInsert() {
        if (!validateRequired()) return;
        String sql = "INSERT INTO playerperformance "
                + "(clubName,playerName,nationality,appearancesTotal,appearancesLaLiga,appearancesChampionsLeague,"
                + "appearancesCopaDelRey,appearancesUEFASuperCup,appearancesNationalTeam,goalsTotal,goalsLaLiga,"
                + "goalsChampionsLeague,goalsCopaDelRey,goalsUEFASuperCup,goalsNationalTeam,assistsTotal,assistsLaLiga,"
                + "assistsChampionsLeague,assistsCopaDelRey,assistsUEFASuperCup,manOfTheMatchCount,averageRating,seasonYear,imagePath) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection connection = DatabaseConfig.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            applyValues(statement);
            statement.executeUpdate();
            message("Performance inserted successfully.");
        } catch (SQLException | NumberFormatException exception) {
            message("Insert error: " + exception.getMessage());
        }
    }

    @FXML
    private void handleUpdate() {
        if (!validateRequired()) return;
        String sql = "UPDATE playerperformance SET clubName=?, nationality=?, appearancesTotal=?, appearancesLaLiga=?, "
                + "appearancesChampionsLeague=?, appearancesCopaDelRey=?, appearancesUEFASuperCup=?, appearancesNationalTeam=0, "
                + "goalsTotal=?, goalsLaLiga=?, goalsChampionsLeague=?, goalsCopaDelRey=?, goalsUEFASuperCup=?, goalsNationalTeam=0, "
                + "assistsTotal=?, assistsLaLiga=?, assistsChampionsLeague=?, assistsCopaDelRey=?, assistsUEFASuperCup=?, "
                + "manOfTheMatchCount=?, averageRating=?, seasonYear=?, imagePath=? WHERE playerName=?";
        try (Connection connection = DatabaseConfig.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, text(playerClubField));
            statement.setString(2, text(nationalityField));
            statement.setInt(3, number(appearancesTotalField));
            statement.setInt(4, number(appearancesLaLigaField));
            statement.setInt(5, number(appearancesChampionsField));
            statement.setInt(6, number(appearancesCopaField));
            statement.setInt(7, number(appearancesUefaField));
            statement.setInt(8, number(goalsTotalField));
            statement.setInt(9, number(goalsLaLigaField));
            statement.setInt(10, number(goalsChampionsField));
            statement.setInt(11, number(goalsCopaField));
            statement.setInt(12, number(goalsUefaField));
            statement.setInt(13, number(assistsTotalField));
            statement.setInt(14, number(assistsLaLigaField));
            statement.setInt(15, number(assistsChampionsField));
            statement.setInt(16, number(assistsCopaField));
            statement.setInt(17, number(assistsUefaField));
            statement.setInt(18, number(manOfMatchField));
            statement.setDouble(19, decimal(averageRatingField));
            statement.setInt(20, number(seasonYearField));
            statement.setString(21, text(imagePathField));
            statement.setString(22, text(playerNameField));
            int updated = statement.executeUpdate();
            message(updated > 0 ? "Performance updated successfully." : "No performance record found for that player.");
        } catch (SQLException | NumberFormatException exception) {
            message("Update error: " + exception.getMessage());
        }
    }

    @FXML
    private void handleDelete() {
        if (text(playerNameField).isBlank()) {
            message("Player name is required.");
            return;
        }
        try (Connection connection = DatabaseConfig.connect();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM playerperformance WHERE playerName=?")) {
            statement.setString(1, text(playerNameField));
            int deleted = statement.executeUpdate();
            message(deleted > 0 ? "Performance deleted successfully." : "No performance record found.");
            if (deleted > 0) clearFields();
        } catch (SQLException exception) {
            message("Delete error: " + exception.getMessage());
        }
    }

    @FXML
    private void handleClear() {
        clearFields();
        message("");
    }

    @FXML
    private void handleExit(ActionEvent event) {
        if (event.getSource() instanceof Button button && button.getScene() != null) {
            ((Stage) button.getScene().getWindow()).close();
        }
    }

    private void loadPlayer(String name) {
        try (Connection connection = DatabaseConfig.connect();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM playerperformance WHERE playerName=?")) {
            statement.setString(1, name);
            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) return;
                set(playerNameField, result.getString("playerName"));
                set(playerClubField, result.getString("clubName"));
                set(nationalityField, result.getString("nationality"));
                set(appearancesTotalField, result.getInt("appearancesTotal"));
                set(appearancesLaLigaField, result.getInt("appearancesLaLiga"));
                set(appearancesChampionsField, result.getInt("appearancesChampionsLeague"));
                set(appearancesCopaField, result.getInt("appearancesCopaDelRey"));
                set(appearancesUefaField, result.getInt("appearancesUEFASuperCup"));
                set(goalsTotalField, result.getInt("goalsTotal"));
                set(goalsLaLigaField, result.getInt("goalsLaLiga"));
                set(goalsChampionsField, result.getInt("goalsChampionsLeague"));
                set(goalsCopaField, result.getInt("goalsCopaDelRey"));
                set(goalsUefaField, result.getInt("goalsUEFASuperCup"));
                set(assistsTotalField, result.getInt("assistsTotal"));
                set(assistsLaLigaField, result.getInt("assistsLaLiga"));
                set(assistsChampionsField, result.getInt("assistsChampionsLeague"));
                set(assistsCopaField, result.getInt("assistsCopaDelRey"));
                set(assistsUefaField, result.getInt("assistsUEFASuperCup"));
                set(manOfMatchField, result.getInt("manOfTheMatchCount"));
                set(averageRatingField, result.getDouble("averageRating"));
                set(seasonYearField, result.getInt("seasonYear"));
                set(imagePathField, result.getString("imagePath"));
                showImage(text(imagePathField));
            }
        } catch (SQLException exception) {
            message("Load error: " + exception.getMessage());
        }
    }

    private void applyValues(PreparedStatement statement) throws SQLException {
        statement.setString(1, text(playerClubField));
        statement.setString(2, text(playerNameField));
        statement.setString(3, text(nationalityField));
        statement.setInt(4, number(appearancesTotalField));
        statement.setInt(5, number(appearancesLaLigaField));
        statement.setInt(6, number(appearancesChampionsField));
        statement.setInt(7, number(appearancesCopaField));
        statement.setInt(8, number(appearancesUefaField));
        statement.setInt(9, 0);
        statement.setInt(10, number(goalsTotalField));
        statement.setInt(11, number(goalsLaLigaField));
        statement.setInt(12, number(goalsChampionsField));
        statement.setInt(13, number(goalsCopaField));
        statement.setInt(14, number(goalsUefaField));
        statement.setInt(15, 0);
        statement.setInt(16, number(assistsTotalField));
        statement.setInt(17, number(assistsLaLigaField));
        statement.setInt(18, number(assistsChampionsField));
        statement.setInt(19, number(assistsCopaField));
        statement.setInt(20, number(assistsUefaField));
        statement.setInt(21, number(manOfMatchField));
        statement.setDouble(22, decimal(averageRatingField));
        statement.setInt(23, number(seasonYearField));
        statement.setString(24, text(imagePathField));
    }

    private boolean validateRequired() {
        if (text(playerNameField).isBlank()) {
            message("Player name is required.");
            return false;
        }
        try {
            number(appearancesTotalField); number(goalsTotalField); number(assistsTotalField);
            decimal(averageRatingField); number(seasonYearField);
            return true;
        } catch (NumberFormatException exception) {
            message("Statistics must contain valid numbers.");
            return false;
        }
    }

    private void clearFields() {
        for (TextField field : new TextField[]{playerNameField, playerClubField, nationalityField,
                appearancesTotalField, appearancesLaLigaField, goalsLaLigaField, assistsLaLigaField,
                appearancesChampionsField, goalsChampionsField, assistsChampionsField,
                appearancesUefaField, goalsUefaField, assistsUefaField,
                appearancesCopaField, goalsCopaField, assistsCopaField,
                goalsTotalField, assistsTotalField, manOfMatchField, averageRatingField,
                seasonYearField, imagePathField}) {
            field.clear();
        }
        medicalDescriptionArea.clear();
        imageView.setImage(null);
    }

    private void showImage(String path) {
        if (path == null || path.isBlank()) return;
        try {
            String location = path;
            if (!path.contains(":") && getClass().getClassLoader().getResource(path) != null) {
                location = getClass().getClassLoader().getResource(path).toExternalForm();
            }
            imageView.setImage(new Image(location, true));
        } catch (Exception ignored) {
            imageView.setImage(null);
        }
    }

    private void message(String value) {
        medicalDescriptionArea.setText(value == null ? "" : value);
    }

    private static String text(TextField field) {
        return field.getText() == null ? "" : field.getText().trim();
    }

    private static int number(TextField field) {
        String value = text(field);
        return value.isBlank() ? 0 : Integer.parseInt(value);
    }

    private static double decimal(TextField field) {
        String value = text(field);
        return value.isBlank() ? 0.0 : Double.parseDouble(value);
    }

    private static void set(TextField field, Object value) {
        field.setText(value == null ? "" : String.valueOf(value));
    }
}
