package com.example.realmadrid.Controller;

import com.example.realmadrid.Controller.Couch.TransferAllPopUp.PlayerPerformancePopUpController;
import com.example.realmadrid.Controller.Couch.TransferAllPopUp.TrainingPopupController;
import com.example.realmadrid.Controller.Couch.TransferAllPopUp.TransferEditorDialog;
import com.example.realmadrid.Model.FormationDataManager;
import com.example.realmadrid.Model.Model;
import com.example.realmadrid.Model.Player.*;
import com.example.realmadrid.Model.PlayerPerformanceDAO;
import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class MainController {

    @FXML private ImageView playerImageView;
    @FXML private ComboBox<String> formationComboBox;
    @FXML private AnchorPane formationPane;
    @FXML private ListView<Player> playerListView;
    @FXML private VBox playerDetailsPane;
    @FXML private Label nameLabel;
    @FXML private Label positionLabel;

    @FXML
    private Button performance_btn;


    private final Map<String, List<Position>> formations = new HashMap<>();
    private final ObservableList<Player> players = FXCollections.observableArrayList();
    private final Map<Player, Node> playerNodeMap = new HashMap<>();
    private final Gson gson = new Gson();

    private boolean isOpponent = false;



    public void setOpponentMode(boolean opponentMode) {
        this.isOpponent = opponentMode;
    }

    public void init() {
        formationComboBox.setOnAction(event -> {
            String selectedFormation = formationComboBox.getValue();
            handleFormationSelection(selectedFormation);
        });

        playerDetailsPane.setVisible(false);
        setupFormations();

        if (isOpponent) {
            setupPlayersForOpponent();
        } else {
            setupPlayersMyteam();
        }

        formationComboBox.setItems(FXCollections.observableArrayList(formations.keySet()));
        formationComboBox.getSelectionModel().selectFirst();

        playerListView.setItems(players);
        playerListView.setCellFactory(param -> new PlayerListCell());

        playerListView.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            highlightPlayerCard(newV);
            showPlayerDetails(newV);
        });

        Platform.runLater(() -> setInitialFormation(formationComboBox.getValue()));

        performance_btn.setOnAction(event -> {onPerformance();});
    }

    public void setInitialFormation(String formationName) {
        if (formationName != null && formationComboBox.getItems().contains(formationName)) {
            formationComboBox.setValue(formationName);
        } else if (formationComboBox.getItems().contains("4-3-3")) {
            formationComboBox.setValue("4-3-3");
        } else if (!formationComboBox.getItems().isEmpty()) {
            formationComboBox.getSelectionModel().selectFirst();
        }

        Platform.runLater(this::updateFormation); // ensure correct pane size
    }

    public String getCurrentFormation() {
        return formationComboBox.getValue();
    }

    public void handleFormationSelection(String formation) {
        if (formation != null && formations.containsKey(formation)) {
            formationComboBox.setValue(formation);
            Platform.runLater(this::updateFormation); // delayed update
        }
    }

    private void setupFormations() {
        formations.put("4-3-3", Arrays.asList(
                new Position(0.5, 0.9), new Position(0.15, 0.7), new Position(0.35, 0.7),
                new Position(0.65, 0.7), new Position(0.85, 0.7), new Position(0.3, 0.5),
                new Position(0.5, 0.5), new Position(0.7, 0.5), new Position(0.25, 0.2),
                new Position(0.5, 0.15), new Position(0.75, 0.2)
        ));

        formations.put("4-4-2", Arrays.asList(
                new Position(0.5, 0.9), new Position(0.15, 0.7), new Position(0.35, 0.7),
                new Position(0.65, 0.7), new Position(0.85, 0.7), new Position(0.2, 0.5),
                new Position(0.4, 0.5), new Position(0.6, 0.5), new Position(0.8, 0.5),
                new Position(0.35, 0.2), new Position(0.65, 0.2)
        ));
    }

    private void setupPlayersMyteam() {
        players.clear();
        players.addAll(
                new Player("Thibaut Courtois", "Goalkeeper", "images/courtois.png"),
                new Player("Ferland Mendy", "Left Back", "images/mendy.png"),
                new Player("Eder Militao", "Left Center Back", "images/militao.png"),
                new Player("Antonio Rudiger", "Right Center Back", "images/rudiger.png"),
                new Player("Dani Carvajal", "Right Back", "images/carvajal.png"),
                new Player("Luka Modric", "Left Midfield", "images/modric.png"),
                new Player("Toni Kroos", "Center Midfield", "images/kroos.png"),
                new Player("Casemiro", "Right Midfield", "images/casemiro.png"),
                new Player("Vinicius Jr", "Left Wing", "images/vinicius.png"),
                new Player("Karim Benzema", "Striker", "images/benzema.png"),
                new Player("Rodrygo", "Right Wing", "images/rodrygo.png")
        );
    }
    private void setupPlayersForOpponent() {
        players.clear();
        players.addAll(
                new Player("Marc-Andre ter Stegen", "Goalkeeper", "images/default.png"),
                new Player("João Cancelo", "Right Back", "images/default.png"),
                new Player("Ronald Araújo", "Center Back", "images/default.png"),
                new Player("Jules Koundé", "Center Back", "images/default.png"),
                new Player("Alejandro Balde", "Left Back", "images/default.png"),
                new Player("Frenkie de Jong", "Defensive Midfield", "images/default.png"),
                new Player("İlkay Gündoğan", "Central Midfield", "images/default.png"),
                new Player("Pedri", "Attacking Midfield", "images/default.png"),
                new Player("Raphinha", "Right Wing", "images/default.png"),
                new Player("Robert Lewandowski", "Striker", "images/default.png"),
                new Player("João Félix", "Left Wing", "images/default.png")
        );
    }



    private void updateFormation() {
        String formation = formationComboBox.getValue();
        if (formation == null) return;

        List<Position> positions = formations.get(formation);
        if (positions == null) return;

        formationPane.getChildren().clear();
        playerNodeMap.clear();

        int count = Math.min(players.size(), positions.size());

        for (int i = 0; i < count; i++) {
            Player player = players.get(i);
            Position pos = positions.get(i);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/realmadrid/PlayerCard.fxml"));
                Node playerCard = loader.load();

                PlayerCardController controller = loader.getController();
                controller.setData(player);

                formationPane.getChildren().add(playerCard);
                playerNodeMap.put(player, playerCard);

                // Positioning must also be delayed
                Platform.runLater(() -> {
                    positionPlayerCard(playerCard, pos);
                    enableDrag(playerCard, pos);
                });

                playerCard.setOnMouseClicked(e -> playerListView.getSelectionModel().select(player));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void positionPlayerCard(Node node, Position pos) {
        double paneWidth = formationPane.getWidth();
        double paneHeight = formationPane.getHeight();
        double nodeWidth = node.prefWidth(-1);
        double nodeHeight = node.prefHeight(-1);
        node.relocate(pos.getX() * paneWidth - nodeWidth / 2, pos.getY() * paneHeight - nodeHeight / 2);
    }

    private void enableDrag(Node node, Position pos) {
        final Delta dragDelta = new Delta();

        node.setOnMousePressed(event -> {
            dragDelta.x = node.getLayoutX() - event.getSceneX();
            dragDelta.y = node.getLayoutY() - event.getSceneY();
        });

        node.setOnMouseDragged(event -> {
            double newX = event.getSceneX() + dragDelta.x;
            double newY = event.getSceneY() + dragDelta.y;

            if (newX >= 0 && newX + node.getBoundsInParent().getWidth() <= formationPane.getWidth())
                node.setLayoutX(newX);

            if (newY >= 0 && newY + node.getBoundsInParent().getHeight() <= formationPane.getHeight())
                node.setLayoutY(newY);
        });

        node.setOnMouseReleased(event -> {
            double normalizedX = (node.getLayoutX() + node.getBoundsInParent().getWidth() / 2) / formationPane.getWidth();
            double normalizedY = (node.getLayoutY() + node.getBoundsInParent().getHeight() / 2) / formationPane.getHeight();
            pos.setX(normalizedX);
            pos.setY(normalizedY);
        });
    }

    private void highlightPlayerCard(Player player) {
        playerNodeMap.values().forEach(node -> node.getStyleClass().remove("player-selected"));
        if (player != null && playerNodeMap.containsKey(player)) {
            playerNodeMap.get(player).getStyleClass().add("player-selected");
        }
    }

    private void showPlayerDetails(Player player) {
        if (player == null) {
            playerDetailsPane.setVisible(false);
            return;
        }
        playerDetailsPane.setVisible(true);
        nameLabel.setText(player.getName());
        positionLabel.setText(player.getPosition());

        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/" + player.getImagePath())));
        playerImageView.setImage(image);
    }

    public void refreshUI() {
        playerListView.refresh();
        Player selectedPlayer = playerListView.getSelectionModel().getSelectedItem();
        if (selectedPlayer != null) {
            showPlayerDetails(selectedPlayer);
        }
        updateFormation();
    }

    @FXML
    private void onEditPlayer(ActionEvent event) {
        Player selected = playerListView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        PlayerEditDialog dialog = new PlayerEditDialog(selected);
        dialog.showAndWait().ifPresent(updatedPlayer -> {
            int idx = players.indexOf(selected);
            players.set(idx, updatedPlayer);
            refreshUI();
            playerListView.getSelectionModel().select(updatedPlayer);
        });
    }

    @FXML
    private void onSaveFormation(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Formation");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        File file = fileChooser.showSaveDialog(formationPane.getScene().getWindow());
        if (file == null) return;

        try {
            FormationDataManager.saveFormation(file, formationComboBox.getValue(), players, formations.get(formationComboBox.getValue()));
            new Alert(Alert.AlertType.INFORMATION, "Formation saved successfully!", ButtonType.OK).show();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save formation: " + e.getMessage(), ButtonType.OK).show();
        }
    }

    @FXML
    private void onLoadFormation(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Formation");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        File file = fileChooser.showOpenDialog(formationPane.getScene().getWindow());
        if (file == null) return;

        try {
            FormationDataManager.FormationData data = FormationDataManager.loadFormation(file);
            if (!formations.containsKey(data.getFormationName())) {
                new Alert(Alert.AlertType.ERROR, "Unknown formation in file.", ButtonType.OK).show();
                return;
            }
            formationComboBox.setValue(data.getFormationName());
            players.setAll(data.getPlayers());
            formations.put(data.getFormationName(), data.getPositions());
            Platform.runLater(this::updateFormation);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load formation: " + e.getMessage(), ButtonType.OK).show();
        }
    }

    @FXML
    private void toggleDetailsPane(ActionEvent event) {
        playerDetailsPane.setVisible(!playerDetailsPane.isVisible());
    }

    private static class Delta { double x, y; }
    @FXML
    public void onPerformance() {
        Player player = playerListView.getSelectionModel().getSelectedItem();
        if (player == null) {
            // Optional: Show alert if no player selected
            System.out.println("Please select a player first!");
            return;
        }

        String playerName = player.getName();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/realmadrid/Couch/transferallpopup/playerPerformancePopup.fxml"));
            Parent root = loader.load();

            // Get controller of the popup
            PlayerPerformancePopUpController controller = loader.getController();

            // Load PlayerPerformance from DAO by playerName
            PlayerPerformanceDAO dao = new PlayerPerformanceDAO();
            PlayerPerformance performance = dao.getByPlayerName(playerName);

            // Pass the data to popup controller
            controller.setData(performance);

            Stage stage = new Stage();
            stage.setTitle("Player Performance - " + playerName);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
