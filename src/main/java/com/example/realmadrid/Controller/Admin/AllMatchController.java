package com.example.realmadrid.Controller.Admin;

import com.example.realmadrid.Model.Match;
import com.example.realmadrid.Model.Model;
import com.example.realmadrid.Views.MatchCellFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AllMatchController implements Initializable {

    @FXML
    private Button add_btn;

    @FXML
    private DatePicker date_fld;

    @FXML
    private Button delete_btn;

    @FXML
    private ComboBox<String> filter_btn;

    @FXML
    private ListView<Match> list_view;

    @FXML
    private TextField myscore_fld;

    @FXML
    private TextField opponent_score_fld;

    @FXML
    private TextField opponent_team_fld;

    @FXML
    private TextField seatch_var_lbl;

    @FXML
    private ComboBox<?> sort_by_btn;

    @FXML
    private TextField tornament_fld;

    @FXML
    private Button update_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initiAllMatchList();

        list_view.setItems(Model.getInstance().getAllMatchList());
        list_view.setCellFactory( c-> new MatchCellFactory());


    }

    private void  initiAllMatchList(){

        if(Model.getInstance().getAllMatchList().isEmpty()){
            Model.getInstance().setAllMatchList();
        }
    }
}
