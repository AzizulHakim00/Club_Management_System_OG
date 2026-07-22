package com.example.realmadrid.Views;

import com.example.realmadrid.Controller.Admin.AdminController;
import com.example.realmadrid.Controller.Couch.CouchController;
import com.example.realmadrid.Controller.Couch.GamePlan;
import com.example.realmadrid.Controller.Couch.MatchPreviewController;
import com.example.realmadrid.Controller.MainController;
import com.example.realmadrid.Model.MatchPreview;
import com.example.realmadrid.Model.Model;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;



public class ViewFactory {

    // Manager Section

    private AnchorPane couchDashboardView ;
    private AnchorPane gamePlanView ;
    private AnchorPane MatchPreview ;
    //    private BorderPane MainFormationView;
    private BorderPane transferMarketView;


    private ScrollPane trainingView;


    private GamePlan gamePlanController;

    private MatchPreviewController matchPreviewController;



    private final ObjectProperty<CouchMenuOptions> couchMenuOptionsItem ;


    //  admin view

    private AccountType accountType;

    private final  ObjectProperty<AdminMenuOptions>  adminSelectMenuItem;

    private AnchorPane admindashboardView ;
    private AnchorPane allPlayerAndFifaView;
    private AnchorPane allMatchesView;
    private AnchorPane playerPerforManceview;
    private AnchorPane viewPlayerStausView;
    private AnchorPane preview;



    public ViewFactory(){

        this.accountType = AccountType.COUCH;
        this.couchMenuOptionsItem = new SimpleObjectProperty<>() ;
        this.adminSelectMenuItem = new SimpleObjectProperty<>() ;
    }

    public AccountType getAccountType() {
        return accountType;
    }
    public void setdAccountType(AccountType accountType) {
        this.accountType = accountType;
    }



    // all admin view


    public ObjectProperty<AdminMenuOptions> getAdminSelectMenuItem() {

        return adminSelectMenuItem;
    }

    public void showAdminWindow(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/realmadrid/Admin/Admin.fxml"));
        AdminController adminController = new AdminController();
        fxmlLoader.setController(adminController);
        createStage(fxmlLoader);
    }

    public AnchorPane getPreview() {
        if(preview == null){
            try{
                preview = new FXMLLoader(getClass().getResource("/com/example/realmadrid/Admin/Preview.fxml")).load();

            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        return preview;
    }



    public AnchorPane getAdmindashboardView() {
        if(admindashboardView == null){
            try{
                admindashboardView = new FXMLLoader(getClass().getResource("/com/example/realmadrid/Admin/AdminDashBoard.fxml")).load();

            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        return admindashboardView;
    }

    public AnchorPane getAllPlayerAndFifaView() {
        if(allPlayerAndFifaView == null){
            try {
                allPlayerAndFifaView = new FXMLLoader(getClass().getResource("/com/example/realmadrid/Admin/AllPlayerAndFifa.fxml")).load();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return allPlayerAndFifaView;
    }

    public AnchorPane getAllMatchesView() {
        if(allMatchesView == null){
            try {
                allMatchesView = new FXMLLoader(getClass().getResource("/com/example/realmadrid/Admin/AllMatches.fxml")).load();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return allMatchesView;
    }
    public AnchorPane getPlayerPerforManceview() {
        if(playerPerforManceview == null){
            try{
                playerPerforManceview = new FXMLLoader(getClass().getResource("/com/example/realmadrid/Admin/PlayerPerformance.fxml")).load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return playerPerforManceview;
    }

    public AnchorPane getViewPlayerStausView() {
        if(viewPlayerStausView == null){
            try {
                viewPlayerStausView = new FXMLLoader(getClass().getResource("/com/example/realmadrid/Admin/viewPlayerStatus.fxml")).load();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return viewPlayerStausView;
    }

    private static final String CONFIG_FILE_TEAM1 = "team1_formation.txt";
    private static final String CONFIG_FILE_TEAM2 = "team2_formation.txt";


    public void openDualFormationWindows() {
        // --- Your Team ---
        Stage teamStage = openFormationWindow(
                "Your Team Formation",
                CONFIG_FILE_TEAM1,
                false);  // Not opponent

        // --- Opponent Team ---
        Stage opponentStage = openFormationWindow(
                "Opponent Team Formation",
                CONFIG_FILE_TEAM2,
                true);  // Is opponent

        // Keep both windows linked
        teamStage.setOnHidden(e -> opponentStage.close());
        opponentStage.setOnHidden(e -> teamStage.close());
    }
    // opern formaton window
    private Stage openFormationWindow(String title, String configFile, boolean isOpponent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/realmadrid/MainView.fxml"));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(
                    getClass().getResource("/com/example/realmadrid/Style.css").toExternalForm());

            MainController controller = loader.getController();
            controller.setOpponentMode(isOpponent);
            controller.init();

            String last = loadLastUsedFormation(configFile);
            controller.setInitialFormation(last != null ? last : "4-3-3");

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.getIcons().add(new Image(
                    getClass().getResourceAsStream("/images/courtois.png")));
            stage.setMinWidth(900);
            stage.setMinHeight(700);

            stage.setOnCloseRequest(evt -> {
                try { saveLastUsedFormation(configFile, controller.getCurrentFormation()); }
                catch (IOException ex) { ex.printStackTrace(); }
            });

            stage.show();
            return stage;

        } catch (IOException ex) {
            ex.printStackTrace();
            showAlertInfo("Unable to open formation window:\n" + ex.getMessage());
            return new Stage(); // fallback
        }
    }

    // load formatio from file
    private String loadLastUsedFormation(String file) {
        File f = new File(file);
        if (!f.exists()) return null;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void saveLastUsedFormation(String file, String formation) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(formation);
        }
    }


    public ObjectProperty<CouchMenuOptions> getCouchMenuOptionsItem() {
        return couchMenuOptionsItem;
    }

    public void showCouchWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/realmadrid/Couch/Couch.fxml"));
        CouchController couchController = new CouchController();
        loader.setController(couchController);
        createStage(loader);

    }


    public ScrollPane getTrainingView(){
        try{
            if(trainingView == null){
                trainingView = new FXMLLoader(getClass().getResource("/com/example/realmadrid/Couch/Training.fxml")).load();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return trainingView;

    }


    public AnchorPane getCouchDashboardView(){
        if(couchDashboardView == null){
            try {
                couchDashboardView = new FXMLLoader(getClass().getResource("/com/example/realmadrid/Couch/CouchDashboard.fxml")).load();

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return couchDashboardView;
    }



    public AnchorPane getMatchPreview(){
        if(MatchPreview == null){
            try{
                MatchPreview = new FXMLLoader(getClass().getResource("/com/example/realmadrid/Couch/MatchPreview.fxml")).load();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return MatchPreview;


    }


    public GamePlan getGamePlanController() {
        return gamePlanController;
    }

    public AnchorPane getGapePlaneView() {
        if (gamePlanView == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/realmadrid/Couch/GamePlan.fxml"));
                gamePlanView = loader.load();
                gamePlanController = loader.getController();
                System.out.println("GamePlan FXML loaded & controller cached");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("GamePlan FXML reused");
        }
        return gamePlanView;
    }



    public BorderPane getTransferMarketView()  {
        if (transferMarketView == null) {
            try {
                transferMarketView = new FXMLLoader(getClass().getResource("/com/example/realmadrid/Couch/TransferMarket.fxml")).load();
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
        return transferMarketView;

    }




    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/realmadrid/Login.fxml"));
        createStage(loader);
    }


    // ---- show message window ---

    public void showOpinionWindow(String messageText){
        StackPane pane = new StackPane();
        HBox hbox = new HBox(5);
        hbox.setAlignment(Pos.CENTER);
        Label sender = new Label("Sender");
        Label message = new Label(messageText);
        hbox.getChildren().addAll(sender , message);
        pane.getChildren().add(hbox);
        Scene scene = new Scene(pane, 300, 100);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/com/example/realmadrid/Images/real-madrid-icon-1467x2048-p4mfm7ya.png"))));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Message");
        stage.setScene(scene);
        stage.show();

    }




    public void createStage(FXMLLoader loader , double... dimensions ){
        Scene scene = null ;
        try {
            scene = new Scene(loader.load());

        }
        catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/com/example/realmadrid/Images/real-madrid-icon-1467x2048-p4mfm7ya.png"))));
        stage.setTitle("Real Madrid");
        stage.show();
    }


    public void closeStage(Stage stage){
        stage.close();
    }


    // utility selction ---

    // --- pop -- up -- for transfer ---

    public void showAllPlayersPopUp() {
        try {
            // Load your FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/realmadrid/Couch/transferallpopup/saveToallPlayers.fxml"));

            Parent root = loader.load();
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Manage Player Data");
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showPlayerStatusPopup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/realmadrid/Couch/transferallpopup/PlayerStatusFull.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            // scene.getStylesheets().add(getClass().getResource("/com/example/realmadrid/Style/TransferpopUp/PlayerStatus.css").toExternalForm());

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Manage Player Status");
            popupStage.setScene(scene);
            popupStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    ///  matchPreview
    public void openMatchPreview(LocalDate matchDate) {
        try {
            System.out.println(matchDate);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/realmadrid/Couch/MatchPreview.fxml"));
            AnchorPane root = loader.load(); // Always load a new AnchorPane

            matchPreviewController = loader.getController();


//            MatchPreview preview = Model.getInstance().getDataBaseDriver().getMatchByDate(matchDate);
            if (Model.getInstance().getMatchPreview() != null) {
                System.out.println("No match preview found for date: " + matchDate);

                Model.getInstance().setMatchPreview(matchDate);
            }

            matchPreviewController.setPreview(matchDate);

            Stage stage = new Stage();
            stage.setTitle("Match Preview");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            System.out.println("FXML load error: " + e.getMessage());
            e.printStackTrace();
        }
    }




    // -- all alert types ---

    public void showAlertInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showAlertConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }

    ///  for trainnig


    public void showPopupScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Popup");
            popupStage.setScene(new Scene(root, 400, 500)); // default size
            popupStage.getIcons().add(new Image(String.valueOf(getClass().getResource("/com/example/realmadrid/Images/real-madrid-icon-1467x2048-p4mfm7ya.png"))));
            popupStage.setResizable(false);
            popupStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            showAlertInfo("Failed to load popup: " + e.getMessage());
        }
    }




}
