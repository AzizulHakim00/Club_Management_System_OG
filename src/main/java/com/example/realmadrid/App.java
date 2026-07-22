package com.example.realmadrid;

import com.example.realmadrid.Controller.TeamStats.ClubCharacteristic;
import com.example.realmadrid.Model.Model;
import com.example.realmadrid.Model.Player.PlayerStatus;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class   App extends Application {
    @Override
    public void start(Stage stage) {
     Model.getInstance().getViewFactory().showLoginWindow();
        }


    public static void main(String[] args) {
        launch(args);
    }
}


