package com.example.realmadrid.Model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Match {

    private ObjectProperty<LocalDate> matchDate;
    private StringProperty tournament;
    private StringProperty opponentClub;
    private StringProperty opinion;

    private IntegerProperty myScore;
    private IntegerProperty opponentScore;
    private StringProperty result;

    public Match( LocalDate matchDate, String tournament, String opponentClub,
                 int myScore, int opponentScore,
                 String result , String opinion) {

        this.matchDate = new SimpleObjectProperty(matchDate);
        this.tournament = new SimpleStringProperty(tournament);
        this.opponentClub = new SimpleStringProperty(opponentClub);
        this.myScore = new SimpleIntegerProperty(myScore);
        this.opponentScore = new SimpleIntegerProperty(opponentScore);
        this.result = new SimpleStringProperty(result);
        this.opinion = new SimpleStringProperty(opinion);

    }

    public ObjectProperty<LocalDate> getMatchDateProperty() {
        return this.matchDate;
    }
    public StringProperty getTournamentProperty() {
        return this.tournament;
    }
    public StringProperty getOpponentClubProperty() {
        return this.opponentClub;

    }

    public IntegerProperty getMyScoreProperty() {
        return this.myScore;
    }
    public IntegerProperty getOpponentScoreProperty() {
        return this.opponentScore;
    }
    public StringProperty getResultProperty() {
        return this.result;
    }
    public StringProperty getOpinionProperty() {
        return this.opinion;
    }


}

