package com.example.realmadrid.Model.Player;

import javafx.beans.property.*;
import java.time.LocalDate;

public class PlayerStatus {

    private final StringProperty playerName = new SimpleStringProperty();
    private final StringProperty playerClub = new SimpleStringProperty();
    private final DoubleProperty salary = new SimpleDoubleProperty();
    private final ObjectProperty<LocalDate> joiningDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> leavingDate = new SimpleObjectProperty<>();
    private final DoubleProperty playerBonus = new SimpleDoubleProperty();
    private final StringProperty medicalStatus = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> leaveInDate = new SimpleObjectProperty<>();
    private final IntegerProperty yellowCardsLAL = new SimpleIntegerProperty();
    private final IntegerProperty redCardsLAL = new SimpleIntegerProperty();
    private final IntegerProperty yellowCardsUCL = new SimpleIntegerProperty();
    private final IntegerProperty redCardsUCL = new SimpleIntegerProperty();
    private final IntegerProperty yellowCardsCDR = new SimpleIntegerProperty();
    private final IntegerProperty redCardsCDR = new SimpleIntegerProperty();
    private final IntegerProperty yellowCardsUSC = new SimpleIntegerProperty();
    private final IntegerProperty redCardsUSC = new SimpleIntegerProperty();
    private final IntegerProperty missUpcomingGames = new SimpleIntegerProperty();
    private final IntegerProperty missLAL = new SimpleIntegerProperty();
    private final IntegerProperty missUCL = new SimpleIntegerProperty();
    private final IntegerProperty missCDR = new SimpleIntegerProperty();
    private final IntegerProperty missUSC = new SimpleIntegerProperty();
    private final StringProperty medicalDescription = new SimpleStringProperty();
    private final StringProperty imagePath = new SimpleStringProperty();

    // Constructor excluding auto-incremented ID

    public PlayerStatus() {}

    public PlayerStatus(String playerName , String playerClub , double salary , LocalDate joiningDate , LocalDate leavingDate , double playerBonus , String medicalStatus) {
        this.playerName.set(playerName);
        this.playerClub.set(playerClub);
        this.salary.set(salary);
        this.joiningDate.set(joiningDate);
        this.leavingDate.set(leavingDate);
        this.playerBonus.set(playerBonus);
        this.medicalStatus.set(medicalStatus);

    }

    public PlayerStatus(String playerName, String playerClub, double salary, LocalDate joiningDate,
                        LocalDate leavingDate, double playerBonus, String medicalStatus,
                        LocalDate leaveInDate, int yellowCardsLAL, int redCardsLAL,
                        int yellowCardsUCL, int redCardsUCL, int yellowCardsCDR, int redCardsCDR,
                        int yellowCardsUSC, int redCardsUSC, int missUpcomingGames,
                        int missLAL, int missUCL, int missCDR, int missUSC,
                        String medicalDescription, String imagePath) {

        this.playerName.set(playerName);
        this.playerClub.set(playerClub);
        this.salary.set(salary);
        this.joiningDate.set(joiningDate);
        this.leavingDate.set(leavingDate);
        this.playerBonus.set(playerBonus);
        this.medicalStatus.set(medicalStatus);
        this.leaveInDate.set(leaveInDate);
        this.yellowCardsLAL.set(yellowCardsLAL);
        this.redCardsLAL.set(redCardsLAL);
        this.yellowCardsUCL.set(yellowCardsUCL);
        this.redCardsUCL.set(redCardsUCL);
        this.yellowCardsCDR.set(yellowCardsCDR);
        this.redCardsCDR.set(redCardsCDR);
        this.yellowCardsUSC.set(yellowCardsUSC);
        this.redCardsUSC.set(redCardsUSC);
        this.missUpcomingGames.set(missUpcomingGames);
        this.missLAL.set(missLAL);
        this.missUCL.set(missUCL);
        this.missCDR.set(missCDR);
        this.missUSC.set(missUSC);
        this.medicalDescription.set(medicalDescription);
        this.imagePath.set(imagePath);
    }

    // Getters and property accessors
    public String getPlayerName() { return playerName.get(); }
    public void setPlayerName(String value) { playerName.set(value); }
    public StringProperty playerNameProperty() { return playerName; }

    public String getPlayerClub() { return playerClub.get(); }
    public void setPlayerClub(String value) { playerClub.set(value); }
    public StringProperty playerClubProperty() { return playerClub; }

    public double getSalary() { return salary.get(); }
    public void setSalary(double value) { salary.set(value); }
    public DoubleProperty salaryProperty() { return salary; }

    public LocalDate getJoiningDate() { return joiningDate.get(); }
    public void setJoiningDate(LocalDate value) { joiningDate.set(value); }
    public ObjectProperty<LocalDate> joiningDateProperty() { return joiningDate; }

    public LocalDate getLeavingDate() { return leavingDate.get(); }
    public void setLeavingDate(LocalDate value) { leavingDate.set(value); }
    public ObjectProperty<LocalDate> leavingDateProperty() { return leavingDate; }

    public double getPlayerBonus() { return playerBonus.get(); }
    public void setPlayerBonus(double value) { playerBonus.set(value); }
    public DoubleProperty playerBonusProperty() { return playerBonus; }

    public String getMedicalStatus() { return medicalStatus.get(); }
    public void setMedicalStatus(String value) { medicalStatus.set(value); }
    public StringProperty medicalStatusProperty() { return medicalStatus; }

    public LocalDate getLeaveInDate() { return leaveInDate.get(); }
    public void setLeaveInDate(LocalDate value) { leaveInDate.set(value); }
    public ObjectProperty<LocalDate> leaveInDateProperty() { return leaveInDate; }

    public int getYellowCardsLAL() { return yellowCardsLAL.get(); }
    public void setYellowCardsLAL(int value) { yellowCardsLAL.set(value); }
    public IntegerProperty yellowCardsLALProperty() { return yellowCardsLAL; }

    public int getRedCardsLAL() { return redCardsLAL.get(); }
    public void setRedCardsLAL(int value) { redCardsLAL.set(value); }
    public IntegerProperty redCardsLALProperty() { return redCardsLAL; }

    public int getYellowCardsUCL() { return yellowCardsUCL.get(); }
    public void setYellowCardsUCL(int value) { yellowCardsUCL.set(value); }
    public IntegerProperty yellowCardsUCLProperty() { return yellowCardsUCL; }

    public int getRedCardsUCL() { return redCardsUCL.get(); }
    public void setRedCardsUCL(int value) { redCardsUCL.set(value); }
    public IntegerProperty redCardsUCLProperty() { return redCardsUCL; }

    public int getYellowCardsCDR() { return yellowCardsCDR.get(); }
    public void setYellowCardsCDR(int value) { yellowCardsCDR.set(value); }
    public IntegerProperty yellowCardsCDRProperty() { return yellowCardsCDR; }

    public int getRedCardsCDR() { return redCardsCDR.get(); }
    public void setRedCardsCDR(int value) { redCardsCDR.set(value); }
    public IntegerProperty redCardsCDRProperty() { return redCardsCDR; }

    public int getYellowCardsUSC() { return yellowCardsUSC.get(); }
    public void setYellowCardsUSC(int value) { yellowCardsUSC.set(value); }
    public IntegerProperty yellowCardsUSCProperty() { return yellowCardsUSC; }

    public int getRedCardsUSC() { return redCardsUSC.get(); }
    public void setRedCardsUSC(int value) { redCardsUSC.set(value); }
    public IntegerProperty redCardsUSCProperty() { return redCardsUSC; }

    public int getMissUpcomingGames() { return missUpcomingGames.get(); }
    public void setMissUpcomingGames(int value) { missUpcomingGames.set(value); }
    public IntegerProperty missUpcomingGamesProperty() { return missUpcomingGames; }

    public int getMissLAL() { return missLAL.get(); }
    public void setMissLAL(int value) { missLAL.set(value); }
    public IntegerProperty missLALProperty() { return missLAL; }

    public int getMissUCL() { return missUCL.get(); }
    public void setMissUCL(int value) { missUCL.set(value); }
    public IntegerProperty missUCLProperty() { return missUCL; }

    public int getMissCDR() { return missCDR.get(); }
    public void setMissCDR(int value) { missCDR.set(value); }
    public IntegerProperty missCDRProperty() { return missCDR; }

    public int getMissUSC() { return missUSC.get(); }
    public void setMissUSC(int value) { missUSC.set(value); }
    public IntegerProperty missUSCProperty() { return missUSC; }

    public String getMedicalDescription() { return medicalDescription.get(); }
    public void setMedicalDescription(String value) { medicalDescription.set(value); }
    public StringProperty medicalDescriptionProperty() { return medicalDescription; }

    public String getImagePath() { return imagePath.get(); }
    public void setImagePath(String value) { imagePath.set(value); }
    public StringProperty imagePathProperty() { return imagePath; }
}
