/* package com.example.realmadrid.Controller.Login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class VideoLoginController implements Initializable {

    @FXML
    private MediaView mediaView;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signinButton;

    @FXML
    private Button forgotPasswordButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File mediaFile = new File("C:\\Users\\User\\Desktop\\Project real mardid\\vecteezy_real-madrid-football-club-flag-loop_51690585.mov");

        if (!mediaFile.exists()) {
            System.err.println("Video file not found: " + mediaFile.getAbsolutePath());
            return;
        }

        Media media = new Media(mediaFile.toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);

        player.setVolume(0);
        player.setAutoPlay(true);
    }
}
*/
