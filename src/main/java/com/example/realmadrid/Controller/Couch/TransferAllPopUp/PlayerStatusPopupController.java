package com.example.realmadrid.Controller.Couch.TransferAllPopUp;

import com.example.realmadrid.Model.Model;
import com.example.realmadrid.Model.Player.PlayerStatus;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/** Read-only controller for the existing compact player-status popup. */
public class PlayerStatusPopupController {
    @FXML private Label playerNameLabel;
    @FXML private Label salaryLabel;
    @FXML private Label joiningDateLabel;
    @FXML private Label leavingDateLabel;
    @FXML private Label bonusLabel;

    @FXML
    private void initialize() {
        PlayerStatus status = Model.getInstance().getPendingStatus();
        if (status == null) status = Model.getInstance().getPlayerStatus();
        playerNameLabel.setText(value(status.getPlayerName(), "Not selected"));
        salaryLabel.setText(String.format("€%,.2f", status.getSalary()));
        joiningDateLabel.setText(status.getJoiningDate() == null ? "Not set" : status.getJoiningDate().toString());
        leavingDateLabel.setText(status.getLeavingDate() == null ? "Not set" : status.getLeavingDate().toString());
        bonusLabel.setText(String.format("€%,.2f", status.getPlayerBonus()));
    }

    private static String value(String text, String fallback) {
        return text == null || text.isBlank() ? fallback : text;
    }
}
