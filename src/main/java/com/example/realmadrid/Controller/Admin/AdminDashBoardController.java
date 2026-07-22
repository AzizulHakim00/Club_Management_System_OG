package com.example.realmadrid.Controller.Admin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AdminDashBoardController {

    @FXML
    private TextField amount_flb;

    @FXML
    private ChoiceBox<?> by_tournament_recent;

    @FXML
    private Label club_budjet_lbl;

    @FXML
    private Label club_transfer_budjet_lbl;

    @FXML
    private Label draw_lbl;

    @FXML
    private Label injured_player_staus_lbl;

    @FXML
    private ListView<?> latest_match_list;

    @FXML
    private Label login_date;

    @FXML
    private Label loss_lbl;

    @FXML
    private TextArea massage_flb;

    @FXML
    private Button onAllMatch_btn;

    @FXML
    private Button onPlayer_status_btn;

    @FXML
    private Button onTransfer_window_btn;

    @FXML
    private Label on_loan_lbl;

    @FXML
    private Label onsell_lbl;

    @FXML
    private TextField payee_flb;

    @FXML
    private Button send_money_btn;

    @FXML
    private Text user_name;

    @FXML
    private Label win_lbl;

}
