package com.example.realmadrid.Model;

import javafx.beans.property.*;
import java.time.LocalDate;

public class UpcomingMatch {

    private final ObjectProperty<LocalDate> date;
    private final StringProperty time;
    private final StringProperty venue;
    private final StringProperty opponentTeam;
    private final StringProperty tournament;
    private final IntegerProperty myScore;
    private final IntegerProperty opponentScore;
    private final StringProperty stadium;

    public UpcomingMatch(LocalDate date, String time, String venue, String opponentTeam, String tournament,
                         int myScore, int opponentScore, String stadium) {
        this.date = new SimpleObjectProperty<>(date);
        this.time = new SimpleStringProperty(time);
        this.venue = new SimpleStringProperty(venue);
        this.opponentTeam = new SimpleStringProperty(opponentTeam);
        this.tournament = new SimpleStringProperty(tournament);
        this.myScore = new SimpleIntegerProperty(myScore);
        this.opponentScore = new SimpleIntegerProperty(opponentScore);
        this.stadium = new SimpleStringProperty(stadium);
    }

    // Getters for properties
    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public StringProperty timeProperty() {
        return time;
    }

    public StringProperty venueProperty() {
        return venue;
    }

    public StringProperty opponentTeamProperty() {
        return opponentTeam;
    }

    public StringProperty tournamentProperty() {
        return tournament;
    }

    public IntegerProperty myScoreProperty() {
        return myScore;
    }

    public IntegerProperty opponentScoreProperty() {
        return opponentScore;
    }

    public StringProperty stadiumProperty() {
        return stadium;
    }

    // Getters and setters for values
    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public String getTime() {
        return time.get();
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getVenue() {
        return venue.get();
    }

    public void setVenue(String venue) {
        this.venue.set(venue);
    }

    public String getOpponentTeam() {
        return opponentTeam.get();
    }

    public void setOpponentTeam(String opponentTeam) {
        this.opponentTeam.set(opponentTeam);
    }

    public String getTournament() {
        return tournament.get();
    }

    public void setTournament(String tournament) {
        this.tournament.set(tournament);
    }

    public int getMyScore() {
        return myScore.get();
    }

    public void setMyScore(int myScore) {
        this.myScore.set(myScore);
    }

    public int getOpponentScore() {
        return opponentScore.get();
    }

    public void setOpponentScore(int opponentScore) {
        this.opponentScore.set(opponentScore);
    }

    public String getStadium() {
        return stadium.get();
    }

    public void setStadium(String stadium) {
        this.stadium.set(stadium);
    }
}
