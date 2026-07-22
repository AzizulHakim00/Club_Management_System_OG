package com.example.realmadrid.Controller.Admin;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ViewPlayerStatusController {

    @FXML
    private TableView table_upper;

    @FXML
    private TableView table_lower;
    @FXML

    private TableColumn<?, ?> description_clm;

    @FXML
    private Button edit_btn;

    @FXML
    private ComboBox<?> filter_combo_box;

    @FXML
    private TableColumn<?, ?> joining_date_clm;

    @FXML
    private TableColumn<?, ?> leaving_date_clm;

    @FXML
    private TableColumn<?, ?> miss_cdr;

    @FXML
    private TableColumn<?, ?> miss_laliga;

    @FXML
    private TableColumn<?, ?> miss_ucl;

    @FXML
    private TableColumn<?, ?> miss_upcoming_match_clm;

    @FXML
    private TableColumn<?, ?> miss_usc;

    @FXML
    private TableColumn<?, ?> player_bonus_clm;

    @FXML
    private TableColumn<?, ?> player_medical_status_clm;

    @FXML
    private TableColumn<?, ?> player_saalary_clm;

    @FXML
    private TableColumn<?, ?> pler_club_clm;

    @FXML
    private TableColumn<?, ?> plyer_name_1_clm;

    @FXML
    private TableColumn<?, ?> red_cdr;

    @FXML
    private TableColumn<?, ?> red_laliga;

    @FXML
    private TableColumn<?, ?> red_ucl;

    @FXML
    private TableColumn<?, ?> red_usc;

    @FXML
    private TextField search_var;

    @FXML
    private ComboBox<?> sort_combo_box;

    @FXML
    private TableColumn<?, ?> yellow_cdr;

    @FXML
    private TableColumn<?, ?> yellow_laliga;

    @FXML
    private TableColumn<?, ?> yellow_ucl;

    @FXML
    private TableColumn<?, ?> yellow_usc;

}
