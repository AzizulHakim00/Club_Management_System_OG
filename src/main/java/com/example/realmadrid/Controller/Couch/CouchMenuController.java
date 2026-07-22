package com.example.realmadrid.Controller.Couch;

import com.example.realmadrid.Model.Model;
import com.example.realmadrid.Views.CouchMenuOptions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CouchMenuController implements Initializable {
    @FXML
    private Button Training_btn;

    @FXML
    private Button couch_profile_btn;

    @FXML
    private Button dassboard_btn;

    @FXML
    private Button formation_btn;

    @FXML
    private Button gamePlan_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private Button myContact_btn;

    @FXML
    private Button report_btn;

    @FXML
    private Button transfer_window_btn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        dassboard_btn.setOnAction(event -> onCouchDassboard());
//        matches_btn.setOnAction(event -> onMatchPreview());
        gamePlan_btn.setOnAction(event -> onGamePlan());
//        matches_btn11.setOnAction(event -> onFormation());
        transfer_window_btn.setOnAction(event -> onTransferMarket());
        Training_btn.setOnAction(event -> onTraining());
        logout_btn.setOnAction(event -> onLogout());
        formation_btn.setOnAction(event -> onFormation());

    }
    private void onCouchDassboard() {
        Model.getInstance().getViewFactory().getCouchMenuOptionsItem().set(CouchMenuOptions.CouchDashboard);
    }
//    private void onFormation(){
//        Model.getInstance().getViewFactory().getCouchMenuOptionsItem().set(CouchMenuOptions.Formation);
//    }

//    private void onMatchPreview() {
//        Model.getInstance().getViewFactory().getCouchMenuOptionsItem().set(CouchMenuOptions.MatchPreview);
//    }
    public void onGamePlan(){
        Model.getInstance().getViewFactory().getCouchMenuOptionsItem().set(CouchMenuOptions.GamePlane);
    }
    public void onTransferMarket(){
        Model.getInstance().getViewFactory().getCouchMenuOptionsItem().set(CouchMenuOptions.transferMarket);
    }
    public void onTraining(){
        Model.getInstance().getViewFactory().getCouchMenuOptionsItem().set(CouchMenuOptions.training);
    }
    public void onFormation(){
        Model.getInstance().getViewFactory().getCouchMenuOptionsItem().set(CouchMenuOptions.Formation);
    }

    private void onLogout(){
        // get Stage

        Stage stage = (Stage) dassboard_btn.getScene().getWindow();

        // close the admin window

        Model.getInstance().getViewFactory().closeStage(stage);

        // show login window

        Model.getInstance().getViewFactory().showLoginWindow();

        // set  admim success flag to false

        Model.getInstance().setAdminLoginSuccessFlag(false);
    }

}
