package com.example.realmadrid.Controller.Match;

import com.example.realmadrid.Controller.Couch.MatchPreviewController;

import com.example.realmadrid.Model.Match;
import com.example.realmadrid.Model.MatchPreview;
import com.example.realmadrid.Model.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MatchCellController implements Initializable {

    @FXML public Button opinion_btn;
    @FXML public Button show_icon_btn;
    @FXML public Label matchDate_lbl;
    @FXML public Label team_A_lbl;
    @FXML public Label tema_B;
    @FXML public Label tournament_lbl;
    @FXML public Button preview_btn;
    @FXML public Label team_A_score;
    @FXML public Label team_b_score;
    @FXML public Button d_btn;
    @FXML public Button l_btn;
    @FXML public Button w_btn;

    private final Match match;

    public MatchCellController(Match match) {
        this.match = match;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
        opinion_btn.setOnAction(event -> Model.getInstance().getViewFactory().showOpinionWindow(match.getOpinionProperty().get()));
        preview_btn.setOnAction(event -> Model.getInstance().getViewFactory().openMatchPreview(match.getMatchDateProperty().get()));
        System.out.println("Match Created" + match.getMatchDateProperty().get());
    }

    void setData() {
        tema_B.textProperty().bind(match.getOpponentClubProperty());
        tournament_lbl.textProperty().bind(match.getTournamentProperty());
        matchDate_lbl.textProperty().bind(match.getMatchDateProperty().asString());
        team_A_score.textProperty().bind(match.getMyScoreProperty().asString());
        team_b_score.textProperty().bind(match.getOpponentScoreProperty().asString());

        int score1 = match.getMyScoreProperty().get();
        int score2 = match.getOpponentScoreProperty().get();

        if (score1 > score2) {
            w_btn.setVisible(true);
            l_btn.setVisible(false);
            d_btn.setVisible(false);
        } else if (score1 < score2) {
            w_btn.setVisible(false);
            l_btn.setVisible(true);
            d_btn.setVisible(false);
        } else {
            w_btn.setVisible(false);
            l_btn.setVisible(false);
            d_btn.setVisible(true);
        }
    }


}
