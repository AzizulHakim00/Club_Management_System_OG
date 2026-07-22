package com.example.realmadrid.Model.PointsTable;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LaLigaClub {
    private final SimpleStringProperty clubName;
    private final SimpleIntegerProperty win;
    private final SimpleIntegerProperty loss;
    private final SimpleIntegerProperty draw;
    private final SimpleIntegerProperty goalDistributed;
    private final SimpleIntegerProperty totalPoints;

    public LaLigaClub(String clubName, int win, int loss, int draw, int goalDistributed, int totalPoints) {
        this.clubName = new SimpleStringProperty(clubName);
        this.win = new SimpleIntegerProperty(win);
        this.loss = new SimpleIntegerProperty(loss);
        this.draw = new SimpleIntegerProperty(draw);
        this.goalDistributed = new SimpleIntegerProperty(goalDistributed);
        this.totalPoints = new SimpleIntegerProperty(totalPoints);
    }

    public String getClubName() { return clubName.get(); }
    public int getWin() { return win.get(); }
    public int getLoss() { return loss.get(); }
    public int getDraw() { return draw.get(); }
    public int getGoalDistributed() { return goalDistributed.get(); }
    public int getTotalPoints() { return totalPoints.get(); }
}
