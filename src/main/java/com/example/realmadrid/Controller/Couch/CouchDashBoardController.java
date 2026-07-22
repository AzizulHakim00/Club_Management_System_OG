package com.example.realmadrid.Controller.Couch;

import com.example.realmadrid.Model.DatabaseConfig;
import com.example.realmadrid.Model.Match;
import com.example.realmadrid.Model.Model;
import com.example.realmadrid.Model.PointsTable.LaLigaClub;
import com.example.realmadrid.Views.CouchMenuOptions;
import com.example.realmadrid.Views.MatchCellFactory;
import com.example.realmadrid.Views.TournamentOption;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;



public class CouchDashBoardController implements Initializable {
    public ListView<Match> latest_match_list;





    @FXML
    private Label login_date;

    @FXML
    private Label my_score_one;

    @FXML
    private Label oppoene_one_date;

    @FXML
    private Label oppoene_one_time;

    @FXML
    private Label opponent_one;

    @FXML
    private ImageView opponent_one_image;

    @FXML
    private Label opponent_one_touranment;

    @FXML
    private Label opponent_one_venue;

    @FXML
    private Label opponent_score_one;

    @FXML
    private Text user_name;


    public Button Laliga_button;



    @FXML
    private Button champions_league_btn;


    @FXML
    private Label my_score_two;


    @FXML
    private Label oppoene_two_date;

    @FXML
    private Label oppoene_two_time;

    @FXML
    private ImageView opponent_one_image1;

    @FXML
    private ImageView opponent_one_image11;


    @FXML
    private Label opponent_score_two;

    @FXML
    private Label opponent_two;

    @FXML
    private Label opponent_two_venue;

    @FXML
    private Label oppponent_two_tournament;


    @FXML
    private Label my_score_three;

    @FXML
    private Label oppoene_three_name;

    @FXML
    private Label opponen_threr_tournament;

    @FXML
    private Label opponent_data_three;

    @FXML
    private Label opponent_score_3;

    @FXML
    private Label opponent_time_three;

    @FXML
    private Label opponent_venue_three;

    @FXML
    private Button uefa_btn;
    @FXML
   private Button scdr_btn;

    public TableView<LaLigaClub> club_table;
    public TableColumn<LaLigaClub, Integer> club_id;
    public TableColumn<LaLigaClub, String> club_name;
    public TableColumn<LaLigaClub, Integer> club_win;
    public TableColumn<LaLigaClub, Integer> club_loss;
    public TableColumn<LaLigaClub, Integer> club_draw;
    public TableColumn<LaLigaClub, Integer> club_goaldice;
    public TableColumn<LaLigaClub, Integer> club_points;
    @FXML
    private ChoiceBox<String> club_choice_box;


    @FXML
    private Label opponent_name_4;

    @FXML
    private Label opponent_4_date;

    @FXML
    private Label opponent_4_time;

    @FXML
    private Label opponent_4_venue;

    @FXML
    private Label opponent_4_score;
    @FXML
    private Label tournament_4;


     @FXML
     private Label mey_four_score;



    private List<LaLigaClub> loadData(String tableName) {
        List<LaLigaClub> list = new ArrayList<>();
        String url = DatabaseConfig.url();
        String user = DatabaseConfig.username();
        String password = DatabaseConfig.password();

        String sql = "SELECT * FROM " + tableName + " ORDER BY total_points DESC";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new LaLigaClub(
                        rs.getString("club_name"),
                        rs.getInt("win"),
                        rs.getInt("loss"),
                        rs.getInt("draw"),
                        rs.getInt("goal_distributed"),
                        rs.getInt("total_points")
                ));
            }
            System.out.println("Data loaded from " + tableName);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public void setDataClub(List<LaLigaClub> dataList) {
        // Sort data by total points descending
        dataList.sort(Comparator.comparingInt(LaLigaClub::getTotalPoints).reversed());

        ObservableList<LaLigaClub> data = FXCollections.observableArrayList(dataList);
        club_table.setItems(data);

        // Set cell value factories only once (safe to call repeatedly)
        club_id.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(club_table.getItems().indexOf(cellData.getValue()) + 1));

        club_name.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        club_win.setCellValueFactory(new PropertyValueFactory<>("win"));
        club_loss.setCellValueFactory(new PropertyValueFactory<>("loss"));
        club_draw.setCellValueFactory(new PropertyValueFactory<>("draw"));
        club_goaldice.setCellValueFactory(new PropertyValueFactory<>("goalDistributed"));
        club_points.setCellValueFactory(new PropertyValueFactory<>("totalPoints"));
    }

    private void loadDataForCompetition(String competition) {
        String tableName;
        if ("La Liga".equals(competition)) {
            tableName = "laliga_points_table";
        } else if ("UCL".equals(competition)) {
            tableName = "champions_league_points_table";
        } else {
            return; // unknown competition
        }

        List<LaLigaClub> dataList = loadData(tableName);
        setDataClub(dataList);  // reuse setDataClub to load and set data
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDate localDate = LocalDate.now();
       login_date.setText(localDate.toString());
        initiLatestMatchList();

        // Setup ChoiceBox items and default value
        club_choice_box.setItems(FXCollections.observableArrayList("La Liga", "UCL"));
        club_choice_box.setValue("La Liga");

        // Load initial data for default competition
        loadDataForCompetition("La Liga");

        // Listener to change table data when selection changes
        club_choice_box.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            loadDataForCompetition(newVal);
        });;

        latest_match_list.setItems(Model.getInstance().getLatestMatchList());
        latest_match_list.setCellFactory( c-> new MatchCellFactory());


        try {

            set_Match(TournamentOption.SSL);
            set_Match(TournamentOption.UCL);
            set_Match(TournamentOption.USC);
            set_Match(TournamentOption.SCDR);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Laliga_button.setOnAction( event -> onLaliga_button());
        champions_league_btn.setOnAction( event -> onChampionButton());
        uefa_btn.setOnAction( event -> onUefaButton());
        scdr_btn.setOnAction(event -> onScdrButton());



    }


      private void  initiLatestMatchList(){

            if(Model.getInstance().getLatestMatchList().isEmpty()){
                Model.getInstance().setLatestMatchList();
            }
      }


    public void set_Match( TournamentOption tournamentOption) throws SQLException {

        //  ---- checking tournament ype ---


        Model.getInstance().getOneMatch(tournamentOption);



        if(tournamentOption == TournamentOption.SSL){

            opponent_one.setText(Model.getInstance().getUpcomingMatch().getOpponentTeam());
            oppoene_one_date.setText(Model.getInstance().getUpcomingMatch().getDate().toString());
            oppoene_one_time.setText(Model.getInstance().getUpcomingMatch().getTime());

            opponent_one_venue.setText(Model.getInstance().getUpcomingMatch().getVenue());
            my_score_one.setText(String.valueOf(Model.getInstance().getUpcomingMatch().getMyScore()));
            opponent_score_one.setText(String.valueOf(Model.getInstance().getUpcomingMatch().getOpponentScore()));
            opponent_one_touranment.setText("La liga");
        }
        else if(tournamentOption == TournamentOption.UCL){

            opponent_two.setText(Model.getInstance().getUpcomingMatch().getOpponentTeam());
            oppoene_two_date.setText(Model.getInstance().getUpcomingMatch().getDate().toString());
            oppoene_two_time.setText(Model.getInstance().getUpcomingMatch().getTime());

            opponent_two_venue.setText(Model.getInstance().getUpcomingMatch().getVenue());
            my_score_two.setText(String.valueOf(Model.getInstance().getUpcomingMatch().getMyScore()));
            opponent_score_two.setText(String.valueOf(Model.getInstance().getUpcomingMatch().getOpponentScore()));

            oppponent_two_tournament.setText("Champions League");

        } else if (tournamentOption == TournamentOption.USC) {


             my_score_three.setText(String.valueOf(Model.getInstance().getUpcomingMatch().getMyScore()));

             oppoene_three_name.setText(Model.getInstance().getUpcomingMatch().getOpponentTeam());

             opponent_data_three.setText(Model.getInstance().getUpcomingMatch().getDate().toString());

             opponent_score_3.setText(String.valueOf(Model.getInstance().getUpcomingMatch().getOpponentScore()));

             opponent_time_three.setText(Model.getInstance().getUpcomingMatch().getTime());
             opponent_venue_three.setText(Model.getInstance().getUpcomingMatch().getVenue());

             opponen_threr_tournament.setText("UEFA Super Cup");

        }
        else if(tournamentOption == TournamentOption.SCDR){
         opponent_name_4.setText(Model.getInstance().getUpcomingMatch().getOpponentTeam());

             opponent_4_date.setText(Model.getInstance().getUpcomingMatch().getDate().toString());

            opponent_4_time.setText(Model.getInstance().getUpcomingMatch().getTime());
            opponent_4_venue.setText(Model.getInstance().getUpcomingMatch().getVenue());

             opponent_4_score.setText(String.valueOf(Model.getInstance().getUpcomingMatch().getOpponentScore()));
             tournament_4.setText("Copa Del Rey");


           mey_four_score.setText(String.valueOf(Model.getInstance().getUpcomingMatch().getMyScore()));
        }

        //  ---- past or future match ------

        if(Model.getInstance().getNextOrLastMatch()) {


            if (Model.getInstance().getTournamentOption() == TournamentOption.SSL) {

                Laliga_button.setDisable(true);
                Model.getInstance().setNextOrLastMatch(false);

            } else if (Model.getInstance().getTournamentOption() == TournamentOption.UCL) {

                champions_league_btn.setDisable(true);
                Model.getInstance().setNextOrLastMatch(false);

            } else if(Model.getInstance().getTournamentOption() == TournamentOption.USC) {

                uefa_btn.setDisable(true);
                Model.getInstance().setNextOrLastMatch(false);
            } else if (Model.getInstance().getTournamentOption() == TournamentOption.SCDR) {
                scdr_btn.setDisable(true);
                Model.getInstance().setNextOrLastMatch(false);
            }

        }

    }



  private void onLaliga_button(){

      Model.getInstance().setTournamentOption(TournamentOption.SSL);
      Model.getInstance().setClubName(opponent_one.getText());
      Model.getInstance().getViewFactory().getCouchMenuOptionsItem().set(CouchMenuOptions.GamePlane);

  }

  private void onChampionButton(){

        Model.getInstance().setTournamentOption(TournamentOption.UCL);
        Model.getInstance().setClubName(opponent_two.getText());
        Model.getInstance().getViewFactory().getCouchMenuOptionsItem().set(CouchMenuOptions.GamePlane);

  }
  private void onUefaButton(){
        Model.getInstance().setTournamentOption(TournamentOption.USC);
        Model.getInstance().setClubName(opponent_two.getText());
        Model.getInstance().getViewFactory().getCouchMenuOptionsItem().set(CouchMenuOptions.GamePlane);
  }

  private void onScdrButton(){
      Model.getInstance().setTournamentOption(TournamentOption.SCDR);
      Model.getInstance().setClubName(opponent_two.getText());
      Model.getInstance().getViewFactory().getCouchMenuOptionsItem().set(CouchMenuOptions.GamePlane);
  }


}
