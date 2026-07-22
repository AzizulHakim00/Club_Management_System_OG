package com.example.realmadrid.Model.Player;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;

public class PlayerEditDialog extends Dialog<Player> {

    private final TextField nameField;
    private final TextField positionField;
    private final TextField imagePathField;

    public PlayerEditDialog(Player player) {
        setTitle("Edit Player");
        setHeaderText("Edit player details:");

        ButtonType okButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        nameField = new TextField(player.getName());
        positionField = new TextField(player.getPosition());
        imagePathField = new TextField(player.getImagePath());

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);

        grid.add(new Label("Position:"), 0, 1);
        grid.add(positionField, 1, 1);

        grid.add(new Label("Image Path:"), 0, 2);
        grid.add(imagePathField, 1, 2);

        getDialogPane().setContent(grid);

        setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                return new Player(nameField.getText(), positionField.getText(), imagePathField.getText());
            }
            return null;
        });
    }
}
