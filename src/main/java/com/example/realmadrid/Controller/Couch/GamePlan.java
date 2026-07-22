package com.example.realmadrid.Controller.Couch;

import com.example.realmadrid.Controller.TeamStats.ClubCharacteristic;
import com.example.realmadrid.Model.Model;
import com.example.realmadrid.Model.Player.FifaPlayer;
import com.example.realmadrid.Views.StrengtsCellFactory;
import com.example.realmadrid.Views.TournamentOption;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GamePlan implements Initializable {
    public ListView<String> club_player_list;
    public Label player_name_lbl;
    public ImageView fifa_image_view;
    public Label pac_lbl;
    public Label shot_lbl;
    public Label pass_lbl;
    public Label dri_lbl;
    public Label def_lbl;
    public Label phy_lbl;
    @FXML private ListView<ClubCharacteristic> streght_list;
    @FXML private ChoiceBox<String> choiceBox;
    @FXML private ListView<ClubCharacteristic> playStyle_list;

    private final ObservableList<ClubCharacteristic> playStyle = FXCollections.observableArrayList();
    private final ObservableList<ClubCharacteristic> strengthWeaknessList = FXCollections.observableArrayList();

    @FXML private Label club_level;
    @FXML private Label stadium_level;
    @FXML private Label venue_level;
    @FXML private ChoiceBox<String> tournament_choicebox;

    @FXML private Label againts_shot_direction_left;
    @FXML private Label againts_shot_direction_middle;
    @FXML private Label againts_shot_drection_right;
    @FXML private Label againts_shot_zone_outside;
    @FXML private Label againts_shot_zone_small;
    @FXML private Label againts_shot_zones_middle;
    @FXML private VBox againts_vBox;
    @FXML private Button agaits_btn;

    @FXML private Button for_btn;
    @FXML private Label for_shot_left;
    @FXML private Label for_shot_middle;
    @FXML private Label for_shot_right;
    @FXML private Label for_shot_zone_middle;
    @FXML private Label for_shot_zone_outside;
    @FXML private Label for_shot_zones_small;
    @FXML private VBox for_shots_vbox;
    @FXML private Button shot_btn;

    @FXML private Button touches_btn;
    @FXML private Label touches_left_side_lbl;
    @FXML private Label touches_middle_lbl;
    @FXML private Label touches_middle_thild_lbl;
    @FXML private Label touches_opponent_side_lbl;
    @FXML private Label touches_right_lbl;
    @FXML private VBox touches_vBox;
    @FXML private Label touchs_own_third_lbl;


    private final StringProperty clubLevel = new SimpleStringProperty();
    private final StringProperty stadiumLevel = new SimpleStringProperty();
    private final StringProperty venueLevel = new SimpleStringProperty();

    private final StringProperty touchesLeft = new SimpleStringProperty();
    private final StringProperty touchesMiddle = new SimpleStringProperty();
    private final StringProperty touchesRight = new SimpleStringProperty();
    private final StringProperty touchesOwnThird = new SimpleStringProperty();
    private final StringProperty touchesMiddleThird = new SimpleStringProperty();
    private final StringProperty touchesOpponentThird = new SimpleStringProperty();

    private final StringProperty shotLeft = new SimpleStringProperty();
    private final StringProperty shotMiddle = new SimpleStringProperty();
    private final StringProperty shotRight = new SimpleStringProperty();
    private final StringProperty shotZoneSmall = new SimpleStringProperty();
    private final StringProperty shotZoneMiddle = new SimpleStringProperty();
    private final StringProperty shotZoneOutside = new SimpleStringProperty();

    private final StringProperty againstLeft = new SimpleStringProperty();
    private final StringProperty againstMiddle = new SimpleStringProperty();
    private final StringProperty againstRight = new SimpleStringProperty();
    private final StringProperty againstSmall = new SimpleStringProperty();
    private final StringProperty againstMiddleZone = new SimpleStringProperty();
    private final StringProperty againstOutside = new SimpleStringProperty();

    @FXML void againts_btn_event(ActionEvent event) {

        touches_vBox.setVisible(false);
        againts_vBox.setVisible(true);
        for_shots_vbox.setVisible(false);
        shot_btn.setDefaultButton(true);
        touches_btn.setDefaultButton(false);
        for_btn.setDefaultButton(false);
    }

    @FXML void for_btn_event(ActionEvent event) {
        touches_vBox.setVisible(false);
        againts_vBox.setVisible(false);
        for_shots_vbox.setVisible(true);
        shot_btn.setDefaultButton(true);
        touches_btn.setDefaultButton(false);
    }

    @FXML void shot_btn_event(ActionEvent event) {
        touches_vBox.setVisible(false);
        againts_vBox.setVisible(false);
        for_shots_vbox.setVisible(true);
        for_btn.setDefaultButton(true);
        touches_btn.setDefaultButton(false);
    }

    @FXML void touches_btn_event(ActionEvent event) {
        touches_vBox.setVisible(true);
        againts_vBox.setVisible(false);
        for_shots_vbox.setVisible(false);
        shot_btn.setDefaultButton(false);
        for_btn.setDefaultButton(false);

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeDefaults();
        initializeChoiceBoxes();
        initializeListViews();
        bindLabels();
        loadInitialData();
        registerListeners();
        setupPlayerListSelection();


    }

    private void initializeDefaults() {
        touches_btn.setDefaultButton(true);
        for_shots_vbox.setVisible(false);
        againts_vBox.setVisible(false);
    }

    private void initializeChoiceBoxes() {
        ObservableList<String> tournament_options = FXCollections.observableArrayList("SSL", "UCL" , "USC");
        tournament_choicebox.setItems(tournament_options);
        tournament_choicebox.setValue(Model.getInstance().getTournamentOption().toString());

        ObservableList<String> selector = FXCollections.observableArrayList("Strength", "weakness");
        choiceBox.setItems(selector);
        choiceBox.setValue("Strength");
    }

    private void initializeListViews() {
        playStyle_list.setItems(playStyle);
        playStyle_list.setCellFactory(e -> new StrengtsCellFactory());

        streght_list.setItems(strengthWeaknessList);
        streght_list.setCellFactory(e -> new StrengtsCellFactory());
        String club_name = "";
        if(Model.getInstance().getUpcomingMatch().getOpponentTeam().equals("Fc Barcelona")){
           club_name = "Barcelona";

        }
        else{
            club_name = Model.getInstance().getUpcomingMatch().getOpponentTeam();
        }
        try {
            club_player_list.setItems(FXCollections.observableArrayList(
                    Model.getInstance().getClubPlayerList(
                           club_name
                    )
            ));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadInitialData() {
        try {
            setPlan();
            setStatistics();
            Model.getInstance().getPlayStyle(club, playStyle);
            onselctor();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void registerListeners() {
        choiceBox.valueProperty().addListener((obs, oldVal, newVal) -> onselctor());

        tournament_choicebox.valueProperty().addListener((obs, oldVal, newVal) -> {
            try {
                setPlan();
                setStatistics();
                playStyle.clear();
                Model.getInstance().getPlayStyle(club, playStyle);
                onselctor();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }





    public String club = "";
    public TournamentOption tournament = Model.getInstance().getTournamentOption();

    private String getChoiceBoxes() throws SQLException {
        String league = tournament_choicebox.getValue();

        if (league != null && league.equals("SSL")) {
            Model.getInstance().getOneMatch(TournamentOption.SSL);
        }
        else if(league != null && league.equals("UCL")) {
            Model.getInstance().getOneMatch(TournamentOption.UCL);
        } else if (league != null && league.equals("USC")) {
            Model.getInstance().getOneMatch(TournamentOption.USC);
        }
        return league;
    }


    private void setPlan() throws SQLException {

        String tour = getChoiceBoxes();
        if(tour == null) {
            Model.getInstance().getOneMatch(Model.getInstance().getTournamentOption());
        }

        clubLevel.set(Model.getInstance().getUpcomingMatch().getOpponentTeam());
        stadiumLevel.set(Model.getInstance().getUpcomingMatch().getStadium());
        venueLevel.set(Model.getInstance().getUpcomingMatch().getVenue());
        club = Model.getInstance().getUpcomingMatch().getOpponentTeam();
    }

    private void bindLabels() {

        club_level.textProperty().bind(clubLevel);
        stadium_level.textProperty().bind(stadiumLevel);
        venue_level.textProperty().bind(venueLevel);

        touches_left_side_lbl.textProperty().bind(touchesLeft);
        touches_middle_lbl.textProperty().bind(touchesMiddle);
        touches_right_lbl.textProperty().bind(touchesRight);

        touches_middle_thild_lbl.textProperty().bind(touchesMiddleThird);
        touches_opponent_side_lbl.textProperty().bind(touchesOpponentThird);
        touchs_own_third_lbl.textProperty().bind(touchesOwnThird);

        for_shot_left.textProperty().bind(shotLeft);
        for_shot_middle.textProperty().bind(shotMiddle);
        for_shot_right.textProperty().bind(shotRight);

        for_shot_zones_small.textProperty().bind(shotZoneSmall);
        for_shot_zone_middle.textProperty().bind(shotZoneMiddle);
        for_shot_zone_outside.textProperty().bind(shotZoneOutside);

        againts_shot_direction_left.textProperty().bind(againstLeft);
        againts_shot_direction_middle.textProperty().bind(againstMiddle);

        againts_shot_drection_right.textProperty().bind(againstRight);

        againts_shot_zone_small.textProperty().bind(againstSmall);
        againts_shot_zones_middle.textProperty().bind(againstMiddleZone);
        againts_shot_zone_outside.textProperty().bind(againstOutside);
    }

    private void onselctor() {
        strengthWeaknessList.clear();
        Model.getInstance().setCharacteristics();

        if ("weakness".equalsIgnoreCase(choiceBox.getValue())) {
            Model.getInstance().getWeaknessOnly(club);
        } else {
            Model.getInstance().getStrengthOnly(club);
        }

        strengthWeaknessList.addAll(Model.getInstance().getCharacteristic());
    }


    private void setStatistics() throws SQLException {
        ResultSet rs = Model.getInstance().getDataBaseDriver().getClubStatistics(club);
        if (rs.next()) {

            touchesLeft.set(rs.getString("touches_left_percent"));
            touchesMiddle.set(rs.getString("touches_middle_percent"));
            touchesRight.set(rs.getString("touches_right_percent"));

            touchesOwnThird.set(rs.getString("touches_own_third_percent"));
            touchesMiddleThird.set(rs.getString("touches_middle_third_percent"));
            touchesOpponentThird.set(rs.getString("touches_opponent_third_percent"));

            shotLeft.set(rs.getString("shots_left_percent"));
            shotMiddle.set(rs.getString("shots_middle_percent"));
            shotRight.set(rs.getString("shots_right_percent"));

            shotZoneSmall.set(rs.getString("shots_small_box_percent"));
            shotZoneMiddle.set(rs.getString("shots_penalty_box_percent"));
            shotZoneOutside.set(rs.getString("shots_outside_box_percent"));

            againstLeft.set(rs.getString("against_shots_left_percent"));
            againstMiddle.set(rs.getString("against_shots_middle_percent"));
            againstRight.set(rs.getString("against_shots_right_percent"));

            againstSmall.set(rs.getString("against_shots_small_box_percent"));
            againstMiddleZone.set(rs.getString("against_shots_penalty_box_percent"));
            againstOutside.set(rs.getString("against_shots_outside_box_percent"));
        }
    }
    public void clearFields() {
        // Clear choice boxes
        tournament_choicebox.setValue(null);
        choiceBox.setValue(null);

        // Clear list views
        playStyle.clear();
        strengthWeaknessList.clear();

        // Clear labels by unbinding and resetting (just to be safe)
        club_level.textProperty().unbind();
        stadium_level.textProperty().unbind();
        venue_level.textProperty().unbind();

        touches_left_side_lbl.textProperty().unbind();
        touches_middle_lbl.textProperty().unbind();
        touches_right_lbl.textProperty().unbind();
        touches_middle_thild_lbl.textProperty().unbind();
        touches_opponent_side_lbl.textProperty().unbind();
        touchs_own_third_lbl.textProperty().unbind();

        for_shot_left.textProperty().unbind();
        for_shot_middle.textProperty().unbind();
        for_shot_right.textProperty().unbind();
        for_shot_zone_middle.textProperty().unbind();
        for_shot_zone_outside.textProperty().unbind();
        for_shot_zones_small.textProperty().unbind();

        againts_shot_direction_left.textProperty().unbind();
        againts_shot_direction_middle.textProperty().unbind();
        againts_shot_drection_right.textProperty().unbind();
        againts_shot_zone_outside.textProperty().unbind();
        againts_shot_zone_small.textProperty().unbind();
        againts_shot_zones_middle.textProperty().unbind();

        // Reset property values
        clubLevel.set("");
        stadiumLevel.set("");
        venueLevel.set("");

        touchesLeft.set("");
        touchesMiddle.set("");
        touchesRight.set("");
        touchesOwnThird.set("");
        touchesMiddleThird.set("");
        touchesOpponentThird.set("");

        shotLeft.set("");
        shotMiddle.set("");
        shotRight.set("");
        shotZoneSmall.set("");
        shotZoneMiddle.set("");
        shotZoneOutside.set("");

        againstLeft.set("");
        againstMiddle.set("");
        againstRight.set("");
        againstSmall.set("");
        againstMiddleZone.set("");
        againstOutside.set("");

        // Hide all VBoxes
        touches_vBox.setVisible(true);
        againts_vBox.setVisible(false);
        for_shots_vbox.setVisible(false);

        // Optionally reset club and tournament values
        club = "";
        tournament = TournamentOption.SSL;

        // Rebind labels
        bindLabels();
    }

    public void refresh() {
        try {
            setPlan();
            setStatistics();
            onselctor();



            initializeDefaults();

            initializeChoiceBoxes();
            initializeListViews();
            bindLabels();
            loadInitialData();
            registerListeners();


            // update strength/weakness list
            // update other UI elements as needed
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private final FifaPlayer currentPlayer = new FifaPlayer();


    private void setupPlayerListSelection() {
        club_player_list.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                System.out.println("Selected player: " + newVal);
                try {
                    bindPlayerDetails(newVal);
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        });

    }

    private void bindPlayerDetails(String playerName) throws SQLException {
        ResultSet resultSet = Model.getInstance().getDataBaseDriver().getFifaStatsByname(playerName);

        if (resultSet.next()) {
            currentPlayer.setName(resultSet.getString("name"));
            currentPlayer.setPac(resultSet.getInt("pace"));
            currentPlayer.setShot(resultSet.getInt("shooting"));
            currentPlayer.setPass(resultSet.getInt("passing"));
            currentPlayer.setDribbling(resultSet.getInt("dribbling"));
            currentPlayer.setDefense(resultSet.getInt("defending"));
            currentPlayer.setPhysical(resultSet.getInt("physicality"));

            // Optional: set default image if needed
//            Image defaultImg = new Image("file:images/default_player.png");
//            currentPlayer.setFifaImage(defaultImg);
        }

        // Unbind previous (optional but clean)
        player_name_lbl.textProperty().unbind();

        pac_lbl.textProperty().unbind();
        shot_lbl.textProperty().unbind();
        pass_lbl.textProperty().unbind();
        dri_lbl.textProperty().unbind();
        def_lbl.textProperty().unbind();
        phy_lbl.textProperty().unbind();

        // Bind to currentPlayer (controller-level field)
        player_name_lbl.textProperty().bind(currentPlayer.nameProperty());

        pac_lbl.textProperty().bind(currentPlayer.pacProperty().asString());
        shot_lbl.textProperty().bind(currentPlayer.shotProperty().asString());
        pass_lbl.textProperty().bind(currentPlayer.passProperty().asString());
        dri_lbl.textProperty().bind(currentPlayer.dribblingProperty().asString());
        def_lbl.textProperty().bind(currentPlayer.defenseProperty().asString());
        phy_lbl.textProperty().bind(currentPlayer.physicalProperty().asString());

        System.out.println("Player details bound for: " + currentPlayer.getName());

    }

}
