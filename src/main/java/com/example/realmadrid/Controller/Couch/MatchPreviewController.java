package com.example.realmadrid.Controller.Couch;

import com.example.realmadrid.Model.MatchPreview;
import com.example.realmadrid.Model.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MatchPreviewController implements Initializable {

    @FXML
    private Label myTeamScore, myteamShot, myTeamShotOnTarget, myTeamfaul;
    @FXML
    private Label opponentTeamLabel;
    @FXML
    private Label myTeamYellowCard, myTeamRedCard, myTeamCorner, myTeamOffside;
    @FXML
    private Label myTeamPasses, myTeamPassAccuracy, myTeamPossesion;

    @FXML
    private Label opponentdTeamScore, opponentTeamShot, opponentTeamShotOnTarget, opponentTeamfaul;
    @FXML
    private Label oppomemtTeamYellowCard, opponentTeamRedCard, opponentTeamCorner, opponentTeamOffside;
    @FXML
    private Label opponentTeamPasses, opponentTeamPassAcccuracy, opponentTeamPossesion;

    private MatchPreview preview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("MatchPreviewController initialized.");
    }

    public void setPreview(LocalDate date) {
        Model.getInstance().setMatchPreview(date);
        MatchPreview newPreview = Model.getInstance().getMatchPreview();

        if (newPreview == null) {
            System.out.println("❌ Preview is still null!");
            clearLabels();
            return;
        }

        // Unbind old preview bindings if exists
        if (this.preview != null) {
            unbindLabels();
        }

        this.preview = newPreview;

        // Bind labels to new preview properties
        myTeamScore.textProperty().bind(preview.myScoreProperty().asString());
        myteamShot.textProperty().bind(preview.myTotalShotsProperty().asString());
        opponentTeamLabel.textProperty().bind(preview.getOpponentTeamName());
        myTeamShotOnTarget.textProperty().bind(preview.myShotsOnTargetProperty().asString());
        myTeamfaul.textProperty().bind(preview.myFoulsProperty().asString());
        myTeamYellowCard.textProperty().bind(preview.myYellowCardsProperty().asString());
        myTeamRedCard.textProperty().bind(preview.myRedCardsProperty().asString());
        myTeamCorner.textProperty().bind(preview.myCornersProperty().asString());
        myTeamOffside.textProperty().bind(preview.myOffsidesProperty().asString());
        myTeamPasses.textProperty().bind(preview.myPassesProperty().asString());
        myTeamPassAccuracy.textProperty().bind(preview.myPassAccuracyProperty().asString("%.1f%%"));
        myTeamPossesion.textProperty().bind(preview.myPossessionProperty().asString("%.1f%%"));

        opponentdTeamScore.textProperty().bind(preview.opponentScoreProperty().asString());
        opponentTeamShot.textProperty().bind(preview.opponentTotalShotsProperty().asString());
        opponentTeamShotOnTarget.textProperty().bind(preview.opponentShotsOnTargetProperty().asString());
        opponentTeamfaul.textProperty().bind(preview.opponentFoulsProperty().asString());
        oppomemtTeamYellowCard.textProperty().bind(preview.opponentYellowCardsProperty().asString());
        opponentTeamRedCard.textProperty().bind(preview.opponentRedCardsProperty().asString());
        opponentTeamCorner.textProperty().bind(preview.opponentCornersProperty().asString());
        opponentTeamOffside.textProperty().bind(preview.opponentOffsidesProperty().asString());
        opponentTeamPasses.textProperty().bind(preview.opponentPassesProperty().asString());
        opponentTeamPassAcccuracy.textProperty().bind(preview.opponentPassAccuracyProperty().asString("%.1f%%"));
        opponentTeamPossesion.textProperty().bind(preview.opponentPossessionProperty().asString("%.1f%%"));

        System.out.println("✅ MatchPreview data bound and ready.");
    }

    private void unbindLabels() {
        myTeamScore.textProperty().unbind();
        myteamShot.textProperty().unbind();
        opponentTeamLabel.textProperty().unbind();
        myTeamShotOnTarget.textProperty().unbind();
        myTeamfaul.textProperty().unbind();
        myTeamYellowCard.textProperty().unbind();
        myTeamRedCard.textProperty().unbind();
        myTeamCorner.textProperty().unbind();
        myTeamOffside.textProperty().unbind();
        myTeamPasses.textProperty().unbind();
        myTeamPassAccuracy.textProperty().unbind();
        myTeamPossesion.textProperty().unbind();

        opponentdTeamScore.textProperty().unbind();
        opponentTeamShot.textProperty().unbind();
        opponentTeamShotOnTarget.textProperty().unbind();
        opponentTeamfaul.textProperty().unbind();
        oppomemtTeamYellowCard.textProperty().unbind();
        opponentTeamRedCard.textProperty().unbind();
        opponentTeamCorner.textProperty().unbind();
        opponentTeamOffside.textProperty().unbind();
        opponentTeamPasses.textProperty().unbind();
        opponentTeamPassAcccuracy.textProperty().unbind();
        opponentTeamPossesion.textProperty().unbind();
    }

    private void clearLabels() {
        myTeamScore.setText("-");
        myteamShot.setText("-");
        opponentTeamLabel.setText("-");
        myTeamShotOnTarget.setText("-");
        myTeamfaul.setText("-");
        myTeamYellowCard.setText("-");
        myTeamRedCard.setText("-");
        myTeamCorner.setText("-");
        myTeamOffside.setText("-");
        myTeamPasses.setText("-");
        myTeamPassAccuracy.setText("-");
        myTeamPossesion.setText("-");

        opponentdTeamScore.setText("-");
        opponentTeamShot.setText("-");
        opponentTeamShotOnTarget.setText("-");
        opponentTeamfaul.setText("-");
        oppomemtTeamYellowCard.setText("-");
        opponentTeamRedCard.setText("-");
        opponentTeamCorner.setText("-");
        opponentTeamOffside.setText("-");
        opponentTeamPasses.setText("-");
        opponentTeamPassAcccuracy.setText("-");
        opponentTeamPossesion.setText("-");
    }
}
