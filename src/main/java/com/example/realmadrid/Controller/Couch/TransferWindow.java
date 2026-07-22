package com.example.realmadrid.Controller.Couch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;

import com.example.realmadrid.Controller.Couch.TransferAllPopUp.AllPlayersController;
import com.example.realmadrid.Controller.Couch.TransferAllPopUp.TransferEditorDialog;
import com.example.realmadrid.Model.Model;
import com.example.realmadrid.Model.Player.AllPlayer;
import com.example.realmadrid.Model.Player.Player;
import com.example.realmadrid.Model.Player.PlayerStatus;
import com.example.realmadrid.Model.Player.TransferPlayer;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TransferWindow implements Initializable {

    @FXML
    private Label budgetLabel;

    @FXML
    private Button buyButton;

    @FXML
    private ComboBox<String> clubFilterCombo;

    @FXML
    private Label detailAssists;

    @FXML
    private Label detailClub;

    @FXML
    private Label detailGoals;

    @FXML
    private ImageView detailImageView;

    @FXML
    private Label detailMatches;

    @FXML
    private Label detailName;

    @FXML
    private Label detailPosition;

    @FXML
    private Label detailPrice;

    @FXML
    private Label detailRating;

    @FXML
    private ComboBox<String> positionFilterCombo;

    @FXML
    private Button sellButton;

    @FXML
    private ComboBox<String> sortCombo;

    @FXML
    private TableColumn<TransferPlayer, String> transfer_join_club;

    @FXML
    private TableColumn<TransferPlayer, LocalDate> transfer_join_date;

    @FXML
    private TableColumn<TransferPlayer, String> transfer_left_club;

    @FXML
    private TableColumn<TransferPlayer, LocalDate> transfer_left_date;

    @FXML
    private TableColumn<TransferPlayer, Number> transfer_market_value;

    @FXML
    private TableColumn<TransferPlayer, String> transfer_name;

    @FXML
    private TableColumn<TransferPlayer, String> transfer_nation;

    @FXML
    private TableColumn<TransferPlayer, String> transfer_positon;

    @FXML
    private TableColumn<TransferPlayer, Number> transfer_rating;

    @FXML
    private TableView<TransferPlayer> transfer_table;

    @FXML
    private TableView<TransferPlayer> transfer_table1;

    @FXML
    private TableColumn<TransferPlayer, String> transfer_trade_type;

    @FXML
    private Button uploadImageButton;

    // Own players section columns
    @FXML
    private TableColumn<TransferPlayer, Number> market_value;

    @FXML
    private TableColumn<TransferPlayer, String> own_join_club;

    @FXML
    private TableColumn<TransferPlayer, LocalDate> own_join_date;

    @FXML
    private TableColumn<TransferPlayer, String> own_name;

    @FXML
    private TableColumn<TransferPlayer, String> own_nationlity;

    @FXML
    private TableColumn<TransferPlayer, String> own_position;

    @FXML
    private TableColumn<TransferPlayer, Number> own_ratings;

    @FXML
    private TableColumn<TransferPlayer, String> own_trade;

    @FXML
    private TableColumn<TransferPlayer, String> ownLeftClub;

    @FXML
    private TableColumn<TransferPlayer, LocalDate> own_left_date;


    private double budget = 100_000_000;

    @FXML
    private Button delete_btn;


    @FXML
    private Button insert_btn;

    @FXML
    private Button update_btn;

    @FXML
    private Button edit_btn;

    @FXML
    private Button add_btn;

    // ⇢ Add these imports at the top of TransferWindow.java if not present

    @FXML
    private void onAdd(ActionEvent event) {
        try {
            // Load the dialog
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/realmadrid/Couch/transferallpopup/transferInsert.fxml"));
            Parent root = loader.load();

            // Get the controller and init insert mode
            TransferEditorDialog controller = loader.getController();
            controller.initForInsert();  // Prepares blank form

            // Show dialog
            Stage stage = new Stage();
            stage.setTitle("Add New Player");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.showAndWait();

            // Refresh data after insertion
            refreshTables();
        } catch (IOException e) {
            e.printStackTrace();
            Model.getInstance().getViewFactory().showAlertInfo("Failed to open Add Player dialog.");
        }
    }


    /**
     * Opens the TransferEditorDialog in EDIT mode
     */
    @FXML
    private void onEdit(ActionEvent event) {
        // 1️⃣ Get selected player
        TransferPlayer selectedPlayer = transfer_table.getSelectionModel().getSelectedItem();
        if (selectedPlayer == null) {
            Model.getInstance().getViewFactory().showAlertInfo("Please select a player to edit.");
            return;
        }

        try {
            // 2️⃣ Load dialog FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/realmadrid/Couch/transferallpopup/transferInsert.fxml"));
            Parent root = loader.load();

            // 3️⃣ Pass player to dialog controller
            TransferEditorDialog dialogController = loader.getController();
            dialogController.initForEdit(selectedPlayer);

            // 4️⃣ Show as modal window
            Stage stage = new Stage();
            stage.setTitle("Edit Transfer Player");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            // optional: set owner so it stays above parent window
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.showAndWait();

            // 5️⃣ Refresh tables after dialog closes
            refreshTables();
        } catch (IOException ex) {
            ex.printStackTrace();
            Model.getInstance().getViewFactory().showAlertInfo("Unable to open edit dialog.");
        }
    }


    @FXML
    void onDelete(ActionEvent event) {

    }

    @FXML
    void onUpdate(ActionEvent event) throws SQLException {

        TransferPlayer selected_player = transfer_table.getSelectionModel().getSelectedItem();


        if (selected_player == null) {
            Model.getInstance().getViewFactory().showAlertInfo("No player selected to update.");
            return;
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to update this player data?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                Model.getInstance().setAllPlayerSingle(selected_player.getPlayerName());
                Model.getInstance().setPlayerName(selected_player.getPlayerName());
                Model.getInstance().getViewFactory().showAllPlayersPopUp();
            }
        }

    }


    private PlayerStatus status = new PlayerStatus();
    private ObservableList<PlayerStatus> allPlayerStatusList = FXCollections.observableArrayList();

    @FXML
    void onInsert(ActionEvent event) throws SQLException {

        Model.getInstance().setPlayerStatusList();

        allPlayerStatusList = Model.getInstance().getPlayerStatusList();

        TransferPlayer selected_player = transfer_table.getSelectionModel().getSelectedItem();

        if (selected_player != null) {
            String selectedName = selected_player.getPlayerName();

            boolean found = false;

            for (PlayerStatus p : allPlayerStatusList) {
                System.out.println("Searching values");
                System.out.println(p.getPlayerName());
                if (p.getPlayerName().equals(selectedName)) {
                    Model.getInstance().setPendingStatus(p);
                    System.out.println("got values");
                    found = true;
                    break;
                }
            }

            if (!found) {
                PlayerStatus newPlayerStatus = new PlayerStatus(
                        selected_player.getPlayerName(), // playerName
                        selected_player.getJoinClub(),   // playerClub
                        0.00,                            // salary
                        selected_player.getJoinDate(),   // joiningDate
                        null,                            // leavingDate
                        0.00,                            // playerBonus
                        "Fit",                           // medicalStatus
                        null, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0,
                        "", ""                           // position, nationality
                );
                Model.getInstance().setPendingStatus(newPlayerStatus);
            }


            Model.getInstance().getViewFactory().showPlayerStatusPopup();
        }
    }

    private final ObservableList<TransferPlayer> players = FXCollections.observableArrayList();
    private final ObservableList<TransferPlayer> ownedPlayers = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Model.getInstance().setPlayerStatusList();

        allPlayerStatusList = FXCollections.observableArrayList(Model.getInstance().getPlayerStatusList());

        tableViewInit();

        clubFilterCombo.setItems(Model.getInstance().getComboList("Club"));
        positionFilterCombo.setItems(Model.getInstance().getComboList("Position"));
        sortCombo.setItems(FXCollections.observableArrayList("Price", "Rating"));
        sortCombo.setValue("Price");

        updateBudgetLabel();

        clubFilterCombo.setOnAction(e -> filterAndSortPlayers());
        sortCombo.setOnAction(e -> filterAndSortPlayers());
        positionFilterCombo.setOnAction(e -> filterAndSortPlayers());

        buyButton.setOnAction(this::onBuyPlayer);
        sellButton.setOnAction(this::handleSellPlayer);

        System.out.println("All players: " + Model.getInstance().getAllTransferPlayer().size());


        transfer_table.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                showPlayerDetails(newSel);
            }
        });

        transfer_table1.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                showPlayerDetails(newSel);
            }
        });


    }

    private void showPlayerDetails(TransferPlayer player) {
        if (player == null) return;

        detailName.setText(player.getPlayerName());
        detailClub.setText("Club: " + player.getJoinClub());
        detailPosition.setText("Position: " + player.getPosition());
        detailRating.setText(String.valueOf(player.getRating()));
        detailPrice.setText(String.format("€%,.2f", player.getMarketValue()));

        // Placeholder values unless you have data
        detailGoals.setText("Goals: N/A");
        detailAssists.setText("Assists: N/A");
        detailMatches.setText("Matches: N/A");


    }


    private void tableViewInit() {


        transfer_name.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPlayerName()));
        transfer_positon.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPosition()));
        transfer_nation.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNationality()));
        transfer_rating.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getRating()));
        transfer_left_club.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getLeftClub()));
        transfer_join_club.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getJoinClub()));
        transfer_left_date.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getLeftDate()));
        transfer_join_date.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getJoinDate()));
        transfer_market_value.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getMarketValue()));
        transfer_trade_type.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTradeoption()));


        own_name.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPlayerName()));
        own_position.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPosition()));
        own_nationlity.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNationality()));
        own_ratings.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getRating()));
        own_join_club.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getJoinClub()));
        own_join_date.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getJoinDate()));
        market_value.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getMarketValue()));
        own_trade.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTradeoption()));
        own_left_date.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getLeftDate()));
        ownLeftClub.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getLeftClub()));

        refreshTables();
        transfer_table.setItems(players);
        transfer_table1.setItems(ownedPlayers);
    }

//    private void setOwnedPlayers() {
//        List<TransferPlayer> owned = Model.getInstance().getAllTransferPlayer().stream()
//                .filter(p -> "Real Madrid".equals(p.getJoinClub()))
//                .toList();
//        ownedPlayers.setAll(owned);
//    }


    private void filterAndSortPlayers() {
        ownedPlayers.setAll(
                Model.getInstance().getAllTransferPlayer().stream()
                        .filter(p -> "Real Madrid".equals(p.getJoinClub()))
                        .toList()
        );


        String selectedClub = clubFilterCombo.getSelectionModel().getSelectedItem();
        String selectedPosition = positionFilterCombo.getSelectionModel().getSelectedItem();
        String sortBy = sortCombo.getSelectionModel().getSelectedItem();

        List<String> ownedPlayerNames = ownedPlayers.stream()
                .map(TransferPlayer::getPlayerName) // or use getId() if you have a unique ID field
                .toList();

        List<TransferPlayer> filtered = Model.getInstance().getAllTransferPlayer().stream()
                .filter(player -> !ownedPlayerNames.contains(player.getPlayerName())) // safely exclude owned
                .filter(player -> selectedClub == null || selectedClub.isEmpty() || selectedClub.equals(player.getLeftClub()))
                .filter(player -> selectedPosition == null || selectedPosition.isEmpty() || selectedPosition.equals(player.getPosition()))
                .toList();


        if ("Price".equals(sortBy)) {
            filtered = filtered.stream()
                    .sorted((p1, p2) -> Double.compare(p2.getMarketValue(), p1.getMarketValue()))
                    .toList();
        } else if ("Rating".equals(sortBy)) {
            filtered = filtered.stream()
                    .sorted((p1, p2) -> Integer.compare(p2.getRating(), p1.getRating()))
                    .toList();
        }

        players.setAll(filtered);
    }

    private void updateBudgetLabel() {
        budgetLabel.setText(String.format("Budget: $%,.2f", budget));
    }

    @FXML
    private void onBuyPlayer(javafx.event.ActionEvent event) {

        TransferPlayer selectedPlayer = transfer_table.getSelectionModel().getSelectedItem();
        if (selectedPlayer == null) {
            Model.getInstance().getViewFactory().showAlertInfo("No player selected");
            return;
        }
        if (budget >= selectedPlayer.getMarketValue()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Purchase");
            alert.setHeaderText(null);
            alert.setContentText("Do you want to save the purchase?");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    budget -= selectedPlayer.getMarketValue();

                    try {
                        Model.getInstance().getDataBaseDriver().updateTransferWindowRecordByPlayerName(
                                selectedPlayer.getPlayerName(),
                                selectedPlayer.getPosition(),
                                selectedPlayer.getRating(),
                                selectedPlayer.getNationality(),
                                selectedPlayer.getLeftClub(),
                                selectedPlayer.getLeftDate(),
                                "Real Madrid",  // Set JoinClub to Real Madrid
                                selectedPlayer.getJoinDate(),
                                selectedPlayer.getMarketValue(),
                                selectedPlayer.getTradeoption(),
                                "" // Possibly new LeftClub or empty
                        );
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Model.getInstance().getAllTransferPlayer().clear();
                    refreshTables();  // Handles both tables safely
                    updateBudgetLabel();

                }
            });

        } else {
            Model.getInstance().getViewFactory().showAlertInfo("Not enough budget");
        }
    }
    @FXML
    private void handleSellPlayer(ActionEvent event) {
        TransferPlayer selected = transfer_table1.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Please select a player to sell.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Sell");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to sell " + selected.getPlayerName() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                Model.getInstance().getDataBaseDriver().sellPlayer(
                        selected.getPlayerName(),
                        "Real Madrid",
                        LocalDate.now()
                );

                // Refresh data
                Model.getInstance().setAllTransferPlayers();

                // Refresh tables and UI
                refreshTables();
                updateBudgetLabel();

                showAlert("Player sold successfully.");

            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Failed to sell player: " + e.getMessage());
            }
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void refreshTables() {
        // Reload all players from DB
        Model.getInstance().setAllTransferPlayers();

        List<TransferPlayer> allPlayers = Model.getInstance().getAllTransferPlayer();

        // 1️⃣ Recompute ownedPlayers list from scratch
        ownedPlayers.setAll(
                allPlayers.stream()
                        .filter(p -> "Real Madrid".equals(p.getJoinClub()))
                        .toList()
        );


        // 2️⃣ Now filter non-owned players properly using updated ownedPlayers
        List<String> ownedNames = ownedPlayers.stream()
                .map(TransferPlayer::getPlayerName)
                .toList();

        players.setAll(
                allPlayers.stream()
                        .filter(p -> !"Real Madrid".equals(p.getJoinClub()))
                        .filter(p -> !ownedNames.contains(p.getPlayerName()))
                        .toList()
        );

        // 3️⃣ Apply filters manually again
        filterAndSortPlayers(); // Now this will work with fresh `ownedPlayers`
    }

}