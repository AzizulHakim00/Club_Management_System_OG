package com.example.realmadrid.Controller.Couch.TransferAllPopUp;

import com.example.realmadrid.Model.Model;
import com.example.realmadrid.Model.Player.TransferPlayer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TransferEditorDialog implements Initializable {

    @FXML private TextField nameField, positionField, nationalityField,
            leftClubField, joiningClubField, marketValueField;
    @FXML private DatePicker leftDatePicker, joiningDatePicker;
    @FXML private Spinner<Integer> ratingSpinner;
    @FXML private ComboBox<String> tradeTypeCombo;
    @FXML private Button insertBtn, updateBtn, deleteBtn;

    private TransferPlayer editingPlayer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ratingSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99, 75));
        tradeTypeCombo.getItems().addAll("Permanent", "Loan", "Free");
    }

    public void initForInsert() {
        insertBtn.setVisible(true);
        updateBtn.setVisible(false);
        deleteBtn.setVisible(false);
    }

    public void initForEdit(TransferPlayer player) {
        editingPlayer = player;
        nameField.setText(player.getPlayerName());
        positionField.setText(player.getPosition());
        ratingSpinner.getValueFactory().setValue(player.getRating());
        nationalityField.setText(player.getNationality());
        leftClubField.setText(player.getLeftClub());
        leftDatePicker.setValue(player.getLeftDate());
        joiningClubField.setText(player.getJoinClub());
        joiningDatePicker.setValue(player.getJoinDate());
        marketValueField.setText(String.valueOf(player.getMarketValue()));
        tradeTypeCombo.setValue(player.getTradeoption());

        insertBtn.setVisible(false);
        updateBtn.setVisible(true);
        deleteBtn.setVisible(true);
    }

    @FXML
    void onInsert() {
        try {
            Model.getInstance().getDataBaseDriver().insertTransferWindowRecord(
                    nameField.getText(),
                    positionField.getText(),
                    ratingSpinner.getValue(),
                    nationalityField.getText(),
                    leftClubField.getText(),
                    leftDatePicker.getValue(),
                    joiningClubField.getText(),
                    joiningDatePicker.getValue(),
                    Double.parseDouble(marketValueField.getText()),
                    tradeTypeCombo.getValue(),
                    null
            );
            close();
        } catch (SQLException | NumberFormatException e) {
            showError(e);
        }
    }

    @FXML
    void onUpdate() {
        if (editingPlayer == null) return;
        try {
            Model.getInstance().getDataBaseDriver().updateTransferWindowRecordByPlayerName(
                    editingPlayer.getPlayerName(),
                    positionField.getText(),
                    ratingSpinner.getValue(),
                    nationalityField.getText(),
                    leftClubField.getText(),
                    leftDatePicker.getValue(),
                    joiningClubField.getText(),
                    joiningDatePicker.getValue(),
                    Double.parseDouble(marketValueField.getText()),
                    tradeTypeCombo.getValue(),
                    null
            );
            close();
        } catch (SQLException | NumberFormatException e) {
            showError(e);
        }
    }

    @FXML
    void onDelete() {
        if (editingPlayer == null) return;
        try {
            Model.getInstance().getDataBaseDriver()
                    .deleteTransferWindowRecordByPlayerName(editingPlayer.getPlayerName());
            close();
        } catch (SQLException e) {
            showError(e);
        }
    }

    @FXML
    void onCancel() {
        close();
    }

    private void close() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    private void showError(Exception e) {
        e.printStackTrace();
        new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).showAndWait();
    }
}
