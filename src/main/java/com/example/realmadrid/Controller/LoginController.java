package com.example.realmadrid.Controller;

import com.example.realmadrid.Model.Model;
import com.example.realmadrid.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ChoiceBox<AccountType> account_selector;
    public Label player_lbl;
    public TextField input_field;
    public Button login_btn;
    public PasswordField password_field;
    public Label error_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        account_selector.setItems(FXCollections.observableArrayList(AccountType.ADMIN,AccountType.COUCH));
        account_selector.setValue(Model.getInstance().getViewFactory().getAccountType());
        account_selector.valueProperty().addListener((observable, oldValue, newValue) -> setAcc_selector());
        login_btn.setOnAction(event -> onLogin());
    }

    private void onLogin(){
        Stage stage = (Stage) error_lbl.getScene().getWindow();




        if(Model.getInstance().getViewFactory().getAccountType() == AccountType.COUCH){

           Model.getInstance().evaluateClientCred(input_field.getText(), password_field.getText());

            if(Model.getInstance().getClientLoginSuccessFlag()) {

                Model.getInstance().getViewFactory().showCouchWindow();

                Model.getInstance().getViewFactory().closeStage(stage);
            }
            else{

                    player_lbl.setText("");
                    password_field.setText("");
                    error_lbl.setText("No such Login Credentials");
             }


        }
        else  {
            // Evaluate adin Login Credentials
             Model.getInstance().evaluateAdminCred(input_field.getText(), password_field.getText());
            if(Model.getInstance().getAdminLoginSuccessFlag()){

            Model.getInstance().getViewFactory().showAdminWindow();

            // close the login stage
            Model.getInstance().getViewFactory().closeStage(stage);

             }
        else {

            player_lbl.setText("");
            password_field.setText("");
            error_lbl.setText("No such Login Credentials");
        }

        }


        }






    private void setAcc_selector(){
        Model.getInstance().getViewFactory().setdAccountType(account_selector.getValue());

        // change payee adresss lebel ;
        if( account_selector.getValue() == AccountType.ADMIN){
            player_lbl.setText("Admin");
        }
        else{
            player_lbl.setText("Username");
        }
    }
}

