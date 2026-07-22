package com.example.realmadrid.Controller.Couch.TransferAllPopUp;

import com.example.realmadrid.Model.DatabaseConfig;
import com.example.realmadrid.Model.Model;
import com.example.realmadrid.Model.Player.AllPlayer;
import com.example.realmadrid.Model.Player.TransferPlayer;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;

public class AllPlayersController {

    @FXML private TextField txtClubName;
    @FXML private TextField txtName;
    @FXML private TextField txtPosition;
    @FXML private TextField txtNationality;
    @FXML private TextField txtAge;
    @FXML private TextField txtSex;
    @FXML private TextField txtWeight;
    @FXML private TextField txtHeight;
    @FXML private TextField txtRating;
    @FXML private TextField txtMidIssues;
    @FXML private TextField txtImagePath;

    @FXML private Label lblMessage;

    private Connection conn;
    private AllPlayer player;

    private String playerName = "";
    private TransferPlayer transferPlayer;

    public AllPlayersController() {
    }

    public void initialize() throws SQLException {
        try {
            conn = DriverManager.getConnection(DatabaseConfig.url(), DatabaseConfig.username(), DatabaseConfig.password());
        } catch (SQLException e) {
            lblMessage.setText("DB Connection error: " + e.getMessage());
        }
        playerName = Model.getInstance().getPlayerName();
        System.out.println(playerName);

        player = Model.getInstance().getAllPlayerSingle(playerName);


        if (player == null) {
            player = new AllPlayer();
        }

        bindFieldsToModel();



    }

    private void setTranserPlayerDataInThis() throws SQLException {

        if(!Model.getInstance().getIsAllPlayerDataHave()){
            Model.getInstance().setTransferPlayer(Model.getInstance().getPlayerName());
            transferPlayer = Model.getInstance().getTransferPlayer();
            player.setClubName(transferPlayer.getLeftClub());
            player.setName(transferPlayer.getPlayerName());
            player.setPosition(transferPlayer.getPosition());
            player.setNationality(transferPlayer.getNationality());
            player.setRatingFifaOverall(transferPlayer.getRating());

            player.setSex('M');
            player.setAge(0);
            player.setImagePath("");
            player.setMidIssues("Fit");
            player.setWeightKg(0);
            player.setHeightCm(0);
        }
    }

    private void bindFieldsToModel() {
        txtClubName.textProperty().bindBidirectional(player.clubNameProperty());
        txtName.textProperty().bindBidirectional(player.nameProperty());
        txtPosition.textProperty().bindBidirectional(player.positionProperty());
        txtNationality.textProperty().bindBidirectional(player.nationalityProperty());
        txtSex.textProperty().bindBidirectional(player.sexProperty());
        txtMidIssues.textProperty().bindBidirectional(player.midIssuesProperty());
        txtImagePath.textProperty().bindBidirectional(player.imagePathProperty());

        Bindings.bindBidirectional(txtAge.textProperty(), player.ageProperty(), new javafx.util.converter.NumberStringConverter());
        Bindings.bindBidirectional(txtWeight.textProperty(), player.weightKgProperty(), new javafx.util.converter.NumberStringConverter());
        Bindings.bindBidirectional(txtHeight.textProperty(), player.heightCmProperty(), new javafx.util.converter.NumberStringConverter());
        Bindings.bindBidirectional(txtRating.textProperty(), player.ratingFifaOverallProperty(), new javafx.util.converter.NumberStringConverter());
    }

    @FXML
    private void handleInsert() {
        try {
            String sql = "INSERT INTO allplayers (clubName, name, position, nationality, age, sex, weight_kg, height_cm, ratingFifaOverall, midIssues, imagePath) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                fillPreparedStatement(ps);
                int rows = ps.executeUpdate();
                lblMessage.setText(rows > 0 ? "Insert successful" : "Insert failed");
            }
        } catch (SQLException e) {
            lblMessage.setText("Insert error: " + e.getMessage());
        } catch (NumberFormatException e) {
            lblMessage.setText("Please enter valid numeric values.");
        }
    }
    @FXML
    private void handleUpdate() {
        if (player.getName().isEmpty()) {
            lblMessage.setText("Please enter the player name to update.");
            return;
        }

        try {
            String sql = "UPDATE allplayers SET clubName=?, position=?, nationality=?, age=?, sex=?, weight_kg=?, height_cm=?, ratingFifaOverall=?, midIssues=?, imagePath=? WHERE name=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, player.getClubName());
                ps.setString(2, player.getPosition());
                ps.setString(3, player.getNationality());
                ps.setInt(4, player.getAge());
                ps.setString(5, String.valueOf(player.getSex()));
                ps.setInt(6, player.getWeightKg());
                ps.setInt(7, player.getHeightCm());
                ps.setInt(8, player.getRatingFifaOverall());
                ps.setString(9, player.getMidIssues());
                ps.setString(10, player.getImagePath());

                // Identify which row to update based on name
                ps.setString(11, player.getName());

                int rows = ps.executeUpdate();
                lblMessage.setText(rows > 0 ? "Update successful" : "No player found with this name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            lblMessage.setText("Update error: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete() {
        if (player.getName().isEmpty()) {
            lblMessage.setText("Please enter the player name to delete.");
            return;
        }

        try {
            String sql = "DELETE FROM allplayers WHERE name=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, player.getName());
                int rows = ps.executeUpdate();
                lblMessage.setText(rows > 0 ? "Delete successful" : "No player found with this name");
            }
        } catch (SQLException e) {
            lblMessage.setText("Delete error: " + e.getMessage());
        }
    }

    @FXML
    private void handleClear() {
        // Fix: ensure player is never null before setting properties
        if (player == null) {
            player = new AllPlayer();
            bindFieldsToModel(); // bind again for new player instance
        }

        player.setClubName("");
        player.setName("");
        player.setPosition("");
        player.setNationality("");
        player.setAge(0);
        player.setSex(' ');
        player.setWeightKg(0);
        player.setHeightCm(0);
        player.setRatingFifaOverall(0);
        player.setMidIssues("");
        player.setImagePath("");

        lblMessage.setText("");
    }

    private void fillPreparedStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, player.getClubName());
        ps.setString(2, player.getName());
        ps.setString(3, player.getPosition());
        ps.setString(4, player.getNationality());
        ps.setInt(5, player.getAge());
        ps.setString(6, String.valueOf(player.getSex()));
        ps.setInt(7, player.getWeightKg());
        ps.setInt(8, player.getHeightCm());
        ps.setInt(9, player.getRatingFifaOverall());
        ps.setString(10, player.getMidIssues());
        ps.setString(11, player.getImagePath());
    }

}
