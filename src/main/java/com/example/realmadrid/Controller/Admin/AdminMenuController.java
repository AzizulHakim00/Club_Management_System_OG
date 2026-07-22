package com.example.realmadrid.Controller.Admin;

import com.example.realmadrid.Model.Model;
import com.example.realmadrid.Views.AdminMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    public Button dashboard_btn;
    public Button player_stats_fifa_btn;
    public Button player_performancce_btn;
    public Button player_status_btn;
    public Button tornament_btn;
    public Button all_matches_btn;
    public Button transfer_window_btn;
    public Button binance_btn;
    public Button logout_btn;
    public Button preview_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }
    private void addListeners(){
        dashboard_btn.setOnAction(event -> ondashBoard());
        all_matches_btn.setOnAction(event -> onAllMatches());
        player_performancce_btn.setOnAction(event -> onPlayerPerformance());
        player_status_btn.setOnAction(event -> onPlayerStatus());
        player_stats_fifa_btn.setOnAction(event -> onPlayerStatsFifa());
        logout_btn.setOnAction(event -> onLogout());
        preview_btn.setOnAction(event -> onPreview());
    }
    private void ondashBoard(){
        Model.getInstance().getViewFactory().getAdminSelectMenuItem().set(AdminMenuOptions.DASHBOARD);
    }
    private void onAllMatches(){
        Model.getInstance().getViewFactory().getAdminSelectMenuItem().set(AdminMenuOptions.ALL_MATCH);
    }
    private void onPlayerPerformance(){
        Model.getInstance().getViewFactory().getAdminSelectMenuItem().set(AdminMenuOptions.PLAYER_PERFORMANCE);

    }private void onPlayerStatus(){
        Model.getInstance().getViewFactory().getAdminSelectMenuItem().set(AdminMenuOptions.VIEW_PLAYER);
    }
    private void onPlayerStatsFifa(){
        Model.getInstance().getViewFactory().getAdminSelectMenuItem().set(AdminMenuOptions.FIFA_ALL_PLAYER);
    }
    private void onPreview(){
        Model.getInstance().getViewFactory().getAdminSelectMenuItem().set(AdminMenuOptions.PREVIEW);
    }

    private void onLogout(){
        // get Stage

        Stage stage = (Stage) dashboard_btn.getScene().getWindow();

        // close the admin window

        Model.getInstance().getViewFactory().closeStage(stage);

        // show login window

        Model.getInstance().getViewFactory().showLoginWindow();

        // set  admim success flag to false

        Model.getInstance().setAdminLoginSuccessFlag(false);
    }


}
