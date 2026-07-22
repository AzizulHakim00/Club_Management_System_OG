package com.example.realmadrid.Views;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.Optional;

public class AlertManager {


    public AlertManager() {

    }

    public static void showInfoAlert(  String headerr , String info) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(headerr);
        alert.setContentText(info);
        alert.showAndWait();
    }

    public static void showWarningAlert(String headerr , String info) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(headerr);
        alert.setContentText(info);
        alert.showAndWait();
    }

    public static void showErrorAlert( String info) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error Header");
        alert.setContentText(info);
        alert.showAndWait();
    }

    public static void showConfirmationAlert(String info) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Confirmation Header");
        alert.setContentText(info);
        Optional<ButtonType> result = alert.showAndWait();
        result.ifPresent(response -> {
            if (response == ButtonType.OK) {
                System.out.println("User chose OK");
            } else if (response == ButtonType.CANCEL) {
                System.out.println("User cancelled");
            }
        });
    }

    public static void showCustomAlert(Window owner) {
        ButtonType retry = new ButtonType("Retry", ButtonBar.ButtonData.YES);
        ButtonType ignore = new ButtonType("Ignore", ButtonBar.ButtonData.NO);
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        Alert alert = new Alert(Alert.AlertType.NONE, "An error occurred. What do you want to do?", retry, ignore, cancel);
        alert.setTitle("Custom Alert");
        alert.setHeaderText("Custom Button Handling");
        alert.initOwner(owner);
        alert.initModality(Modality.APPLICATION_MODAL);

        TextArea details = new TextArea("Stack trace or detailed log goes here...");
        details.setEditable(false);
        alert.getDialogPane().setExpandableContent(details);
        alert.getDialogPane().setExpanded(false);

        Optional<ButtonType> result = alert.showAndWait();
        result.ifPresent(response -> {
            if (response == retry) {
                System.out.println("Retry chosen");
            } else if (response == ignore) {
                System.out.println("Ignore chosen");
            } else {
                System.out.println("Cancelled");
            }
        });
    }
}
