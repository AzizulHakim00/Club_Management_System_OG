package com.example.realmadrid.Controller.Couch;

import com.example.realmadrid.Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import org.apache.poi.ss.formula.functions.Mode;

import java.net.URL;
import java.util.ResourceBundle;

public class CouchController implements Initializable {

    public BorderPane couch_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Model.getInstance().getViewFactory().getCouchMenuOptionsItem().addListener((obs, oldVal, newVal) -> {
            System.out.println("Menu changed to: " + newVal);
            switch (newVal) {
//                case MatchPreview -> {
//                    System.out.println("Switching to MatchPreview view");
//                    couch_parent.setCenter(Model.getInstance().getViewFactory().getMatchPreview());
//                }
                case GamePlane -> {
                    System.out.println("Switching to GamePlan view, refreshing data");
                    couch_parent.setCenter(Model.getInstance().getViewFactory().getGapePlaneView());
                    Model.getInstance().getViewFactory().getGamePlanController().refresh();
                }
//                case Formation -> {
//                    couch_parent.setCenter(Model.getInstance().getViewFactory().getMainFormationView());
//                }
                case transferMarket -> {
                    couch_parent.setCenter(Model.getInstance().getViewFactory().getTransferMarketView());
                }
                case training -> {
                    couch_parent.setCenter(Model.getInstance().getViewFactory().getTrainingView());
                    System.out.println("Switching to Training view");
                }
                case Formation -> {
                   Model.getInstance().getViewFactory().openDualFormationWindows();
                }
                default -> {
                    System.out.println("Switching to Dashboard and clearing GamePlan fields");
                    Model.getInstance().getViewFactory().getGamePlanController().clearFields();
                    couch_parent.setCenter(Model.getInstance().getViewFactory().getCouchDashboardView());
                }
            }
        });

    }
}
