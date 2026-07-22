package com.example.realmadrid.Model.Player;

import javafx.scene.control.ListCell;

public class PlayerListCell extends ListCell<Player> {
    @Override
    protected void updateItem(Player player, boolean empty) {
        super.updateItem(player, empty);
        if (empty || player == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(player.getName() + " - " + player.getPosition());
        }
    }
}
