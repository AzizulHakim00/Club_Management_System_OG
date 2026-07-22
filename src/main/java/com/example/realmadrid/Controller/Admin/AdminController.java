package com.example.realmadrid.Controller.Admin;

import com.example.realmadrid.Model.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    public BorderPane admin_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getAdminSelectMenuItem().addListener((observableValue, oldValue, newValue) -> {
            switch (newValue) {
                case PLAYER_PERFORMANCE -> admin_parent.setCenter(Model.getInstance().getViewFactory().getPlayerPerforManceview());
                case FIFA_ALL_PLAYER-> admin_parent.setCenter(Model.getInstance().getViewFactory().getAllPlayerAndFifaView());
                case ALL_MATCH-> admin_parent.setCenter(Model.getInstance().getViewFactory().getAllMatchesView());
                case VIEW_PLAYER-> admin_parent.setCenter(Model.getInstance().getViewFactory().getViewPlayerStausView());

                case DASHBOARD -> admin_parent.setCenter(Model.getInstance().getViewFactory().getAdmindashboardView());
                default  -> admin_parent.setCenter(Model.getInstance().getViewFactory().getPreview());
            }

        });
    }
}
