package com.example.realmadrid.Controller.Couch.TransferAllPopUp;



import com.example.realmadrid.Model.TrainingSession;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TrainingPopupController {

    @FXML private TextField nameField, typeField, attendanceField, fitnessField;
    @FXML private DatePicker datePicker;
    @FXML private TextArea injuryField;
    @FXML private Button insert_btn, update_btn, deleteButton;

   private com.example.realmadrid.Model.TrainingDAO dao = new com.example.realmadrid.Model.TrainingDAO();

    private TrainingSession selectedSession = null;

    // Interface callback to refresh main view
    private Runnable refreshCallback;

    @FXML
    public void initialize() {
        insert_btn.setOnAction(e -> addSession());
        update_btn.setOnAction(e -> updateSession());
        deleteButton.setOnAction(e -> deleteSession());
    }

    public void setSession(TrainingSession session) {
        this.selectedSession = session;
        if (session != null) {
            nameField.setText(session.getPlayerName());
            datePicker.setValue(session.getDate());
            typeField.setText(session.getSessionType());
            attendanceField.setText(session.getAttendanceStatus());
            fitnessField.setText(session.getFitnessLevel());
            injuryField.setText(session.getInjuryNotes());
        }
    }

    public void setRefreshCallback(Runnable refreshCallback) {
        this.refreshCallback = refreshCallback;
    }

    private void addSession() {
        try {
            TrainingSession session = new TrainingSession(
                    0,
                    nameField.getText(),
                    datePicker.getValue(),
                    typeField.getText(),
                    attendanceField.getText(),
                    fitnessField.getText(),
                    injuryField.getText()
            );
            dao.insertSession(session);
            refreshAndClose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateSession() {
        if (selectedSession != null) {
            try {
                selectedSession.setPlayerName(nameField.getText());
                selectedSession.setDate(datePicker.getValue());
                selectedSession.setSessionType(typeField.getText());
                selectedSession.setAttendanceStatus(attendanceField.getText());
                selectedSession.setFitnessLevel(fitnessField.getText());
                selectedSession.setInjuryNotes(injuryField.getText());
                dao.updateSession(selectedSession);
                refreshAndClose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteSession() {
        if (selectedSession != null) {
            try {
              //  dao.deleteSession(selectedSession.getPlayerId());
                refreshAndClose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void refreshAndClose() {
        if (refreshCallback != null) {
            refreshCallback.run();
        }
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}
