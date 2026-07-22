package com.example.realmadrid.Controller;

import com.example.realmadrid.Model.Player.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PlayerEditDialogController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField positionField;

    @FXML
    private TextField imagePathField;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    private Player player;
    private Stage dialogStage;
    private boolean okClicked = false;

    /**
     * Called to give the controller access to the dialog stage.
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the player to be edited in the dialog.
     */
    public void setPlayer(Player player) {
        this.player = player;

        if (player != null) {
            nameField.setText(player.getName());
            positionField.setText(player.getPosition());
            imagePathField.setText(player.getImagePath());
        }
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks OK.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            player.setName(nameField.getText());
            player.setPosition(positionField.getText());
            player.setImagePath(imagePathField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks Cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates user input.
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().trim().isEmpty()) {
            errorMessage += "No valid name!\n";
        }
        if (positionField.getText() == null || positionField.getText().trim().isEmpty()) {
            errorMessage += "No valid position!\n";
        }
        // imagePath can be empty or invalid, so no validation needed

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            // Show error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();
            return false;
        }
    }
}
