package com.example.realmadrid.Model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class TrainingSession {

    private final SimpleIntegerProperty playerId = new SimpleIntegerProperty();
    private final StringProperty playerName = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
    private final StringProperty sessionType = new SimpleStringProperty();
    private final StringProperty attendanceStatus = new SimpleStringProperty();
    private final StringProperty fitnessLevel = new SimpleStringProperty();
    private final StringProperty injuryNotes = new SimpleStringProperty();

    public TrainingSession(int id, String name, LocalDate date, String type, String attendance, String fitness, String injury) {
        this.playerId.set(id);
        this.playerName.set(name);
        this.date.set(date);
        this.sessionType.set(type);
        this.attendanceStatus.set(attendance);
        this.fitnessLevel.set(fitness);
        this.injuryNotes.set(injury);
    }


    public TrainingSession(String name, LocalDate date, String type, String attendance, String fitness, String injury) {

        this.playerName.set(name);
        this.date.set(date);
        this.sessionType.set(type);
        this.attendanceStatus.set(attendance);
        this.fitnessLevel.set(fitness);
        this.injuryNotes.set(injury);
    }


    public TrainingSession() {
    }

    public int getPlayerId() {
        return playerId.get();
    }

    public SimpleIntegerProperty playerIdProperty() {
        return playerId;
    }

    public StringProperty playerNameProperty() { return playerName; }
    public ObjectProperty<LocalDate> dateProperty() { return date; }
    public StringProperty sessionTypeProperty() { return sessionType; }
    public StringProperty attendanceStatusProperty() { return attendanceStatus; }
    public StringProperty fitnessLevelProperty() { return fitnessLevel; }
    public StringProperty injuryNotesProperty() { return injuryNotes; }

    public String getPlayerName() { return playerName.get(); }
    public LocalDate getDate() { return date.get(); }
    public String getSessionType() { return sessionType.get(); }
    public String getAttendanceStatus() { return attendanceStatus.get(); }
    public String getFitnessLevel() { return fitnessLevel.get(); }
    public String getInjuryNotes() { return injuryNotes.get(); }

    public void setPlayerId(int id) { this.playerId.set(id); }
    public void setPlayerName(String name) { this.playerName.set(name); }
    public void setDate(LocalDate date) { this.date.set(date); }
    public void setSessionType(String type) { this.sessionType.set(type); }
    public void setAttendanceStatus(String attendance) { this.attendanceStatus.set(attendance); }
    public void setFitnessLevel(String fitness) { this.fitnessLevel.set(fitness); }
    public  void setInjuryNotes(String injury) { this.injuryNotes.set(injury); }

}