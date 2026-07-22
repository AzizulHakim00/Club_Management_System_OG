package com.example.realmadrid.Controller;

import com.example.realmadrid.Model.Player.Player;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.InputStream;

public class PlayerCardController {

    @FXML private ImageView playerImage;
    @FXML private Text playerName;
    @FXML private Text playerPosition;

    private Image defaultImage;

    public void setData(Player player) {
        if (player == null) {
            playerName.setText("");
            playerPosition.setText("");
            playerImage.setImage(getDefaultImage());
            return;
        }

        playerName.setText(player.getName());
        playerPosition.setText(player.getPosition());

        Image image = loadImage(player.getImagePath());
        playerImage.setImage(image);
    }

    private Image loadImage(String imagePath) {
        if (imagePath == null || imagePath.isEmpty()) {
            return getDefaultImage();
        }

        InputStream is = getClass().getResourceAsStream("/" + imagePath);
        if (is == null) {
            System.err.println("Image not found: /images/" + imagePath + ", loading default image.");
            return getDefaultImage();
        }

        try {
            return new Image(is);
        } catch (Exception e) {
            System.err.println("Failed to load image: " + imagePath + " - " + e.getMessage());
            return getDefaultImage();
        }
    }

    private Image getDefaultImage() {
        if (defaultImage == null) {
            InputStream is = getClass().getResourceAsStream("/default.png");
            if (is != null) {
                defaultImage = new Image(is);
            } else {
                System.err.println("Default image not found!");
            }
        }
        return defaultImage;
    }
}
