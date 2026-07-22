package com.example.realmadrid.Controller.Couch.TransferAllPopUp;

import com.example.realmadrid.Model.DatabaseConfig;
import com.example.realmadrid.Model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** CRUD controller for the existing FIFA-ratings popup layout. */
public class FifaRatingsPopupController {
    @FXML private TextField txtId;
    @FXML private TextField txtName;
    @FXML private TextField txtPosition;     // Existing layout label: Pacing
    @FXML private TextField txtNationality;  // Existing layout label: Shooting
    @FXML private TextField txtAge;          // Existing layout label: Passing
    @FXML private TextField txtSex;          // Existing layout label: Dribbling
    @FXML private TextField txtWeight;       // Existing layout label: Defence
    @FXML private TextField txtHeight;       // Existing layout label: Physics
    @FXML private TextField txtImagePath;
    @FXML private Label lblMessage;

    @FXML
    private void initialize() {
        String selectedName = Model.getInstance().getPlayerName();
        if (selectedName != null && !selectedName.isBlank()) {
            loadByName(selectedName);
        }
    }

    @FXML
    private void handleInsert() {
        if (!validateInput()) return;
        String sql = "INSERT INTO playerfifaratings "
                + "(name,nationality,club,position,overallRating,pace,shooting,passing,dribbling,defending,physicality,imagePath) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection connection = DatabaseConfig.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            setRatingValues(statement, false);
            statement.executeUpdate();
            lblMessage.setText("FIFA rating inserted successfully.");
        } catch (SQLException | NumberFormatException exception) {
            lblMessage.setText("Insert error: " + exception.getMessage());
        }
    }

    @FXML
    private void handleUpdate() {
        if (!validateInput()) return;
        boolean useId = !txtId.getText().isBlank();
        String sql = "UPDATE playerfifaratings SET name=?, nationality=?, club=?, position=?, overallRating=?, "
                + "pace=?, shooting=?, passing=?, dribbling=?, defending=?, physicality=?, imagePath=? WHERE "
                + (useId ? "id=?" : "name=?");
        try (Connection connection = DatabaseConfig.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            setRatingValues(statement, false);
            if (useId) statement.setInt(13, integer(txtId));
            else statement.setString(13, txtName.getText().trim());
            int updated = statement.executeUpdate();
            lblMessage.setText(updated > 0 ? "FIFA rating updated successfully." : "No matching player rating found.");
        } catch (SQLException | NumberFormatException exception) {
            lblMessage.setText("Update error: " + exception.getMessage());
        }
    }

    @FXML
    private void handleDelete() {
        boolean useId = !txtId.getText().isBlank();
        if (!useId && txtName.getText().isBlank()) {
            lblMessage.setText("Enter an ID or player name to delete.");
            return;
        }
        String sql = "DELETE FROM playerfifaratings WHERE " + (useId ? "id=?" : "name=?");
        try (Connection connection = DatabaseConfig.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            if (useId) statement.setInt(1, integer(txtId));
            else statement.setString(1, txtName.getText().trim());
            int deleted = statement.executeUpdate();
            lblMessage.setText(deleted > 0 ? "FIFA rating deleted successfully." : "No matching rating found.");
            if (deleted > 0) handleClear();
        } catch (SQLException | NumberFormatException exception) {
            lblMessage.setText("Delete error: " + exception.getMessage());
        }
    }

    @FXML
    private void handleClear() {
        txtId.clear();
        txtName.clear();
        txtPosition.clear();
        txtNationality.clear();
        txtAge.clear();
        txtSex.clear();
        txtWeight.clear();
        txtHeight.clear();
        txtImagePath.clear();
        lblMessage.setText("");
    }

    private void loadByName(String playerName) {
        String sql = "SELECT * FROM playerfifaratings WHERE name=?";
        try (Connection connection = DatabaseConfig.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, playerName);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    txtId.setText(Integer.toString(result.getInt("id")));
                    txtName.setText(result.getString("name"));
                    txtPosition.setText(Integer.toString(result.getInt("pace")));
                    txtNationality.setText(Integer.toString(result.getInt("shooting")));
                    txtAge.setText(Integer.toString(result.getInt("passing")));
                    txtSex.setText(Integer.toString(result.getInt("dribbling")));
                    txtWeight.setText(Integer.toString(result.getInt("defending")));
                    txtHeight.setText(Integer.toString(result.getInt("physicality")));
                    txtImagePath.setText(result.getString("imagePath"));
                }
            }
        } catch (SQLException exception) {
            lblMessage.setText("Load error: " + exception.getMessage());
        }
    }

    private boolean validateInput() {
        if (txtName.getText().isBlank()) {
            lblMessage.setText("Player name is required.");
            return false;
        }
        try {
            integer(txtPosition); integer(txtNationality); integer(txtAge);
            integer(txtSex); integer(txtWeight); integer(txtHeight);
            return true;
        } catch (NumberFormatException exception) {
            lblMessage.setText("All six FIFA attributes must be whole numbers.");
            return false;
        }
    }

    private void setRatingValues(PreparedStatement statement, boolean unused) throws SQLException {
        int pace = integer(txtPosition);
        int shooting = integer(txtNationality);
        int passing = integer(txtAge);
        int dribbling = integer(txtSex);
        int defending = integer(txtWeight);
        int physicality = integer(txtHeight);
        int overall = Math.round((pace + shooting + passing + dribbling + defending + physicality) / 6.0f);
        statement.setString(1, txtName.getText().trim());
        statement.setString(2, "");
        statement.setString(3, "Real Madrid");
        statement.setString(4, "");
        statement.setInt(5, overall);
        statement.setInt(6, pace);
        statement.setInt(7, shooting);
        statement.setInt(8, passing);
        statement.setInt(9, dribbling);
        statement.setInt(10, defending);
        statement.setInt(11, physicality);
        statement.setString(12, txtImagePath.getText().trim());
    }

    private static int integer(TextField field) {
        return Integer.parseInt(field.getText().trim());
    }
}
