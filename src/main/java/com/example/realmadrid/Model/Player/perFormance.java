package com.example.realmadrid.Model.Player;

import javafx.beans.property.*;

public class perFormance {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty clubName = new SimpleStringProperty();
    private final StringProperty playerName = new SimpleStringProperty();
    private final StringProperty nationality = new SimpleStringProperty();
    private final IntegerProperty appearancesTotal = new SimpleIntegerProperty();
    private final IntegerProperty goalsTotal = new SimpleIntegerProperty();
    private final IntegerProperty assistsTotal = new SimpleIntegerProperty();
    private final DoubleProperty averageRating = new SimpleDoubleProperty();
    private final IntegerProperty seasonYear = new SimpleIntegerProperty();
    private final StringProperty imagePath = new SimpleStringProperty();

    // Default constructor
    public perFormance() {
    }

    // Full constructor
    public perFormance(int id, String clubName, String playerName, String nationality,
                             int appearancesTotal, int goalsTotal, int assistsTotal,
                             double averageRating, int seasonYear, String imagePath) {
        this.id.set(id);
        this.clubName.set(clubName);
        this.playerName.set(playerName);
        this.nationality.set(nationality);
        this.appearancesTotal.set(appearancesTotal);
        this.goalsTotal.set(goalsTotal);
        this.assistsTotal.set(assistsTotal);
        this.averageRating.set(averageRating);
        this.seasonYear.set(seasonYear);
        this.imagePath.set(imagePath);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getClubName() {
        return clubName.get();
    }

    public void setClubName(String clubName) {
        this.clubName.set(clubName);
    }

    public StringProperty clubNameProperty() {
        return clubName;
    }

    public String getPlayerName() {
        return playerName.get();
    }

    public void setPlayerName(String playerName) {
        this.playerName.set(playerName);
    }

    public StringProperty playerNameProperty() {
        return playerName;
    }

    public String getNationality() {
        return nationality.get();
    }

    public void setNationality(String nationality) {
        this.nationality.set(nationality);
    }

    public StringProperty nationalityProperty() {
        return nationality;
    }

    public int getAppearancesTotal() {
        return appearancesTotal.get();
    }

    public void setAppearancesTotal(int appearancesTotal) {
        this.appearancesTotal.set(appearancesTotal);
    }

    public IntegerProperty appearancesTotalProperty() {
        return appearancesTotal;
    }

    public int getGoalsTotal() {
        return goalsTotal.get();
    }

    public void setGoalsTotal(int goalsTotal) {
        this.goalsTotal.set(goalsTotal);
    }

    public IntegerProperty goalsTotalProperty() {
        return goalsTotal;
    }

    public int getAssistsTotal() {
        return assistsTotal.get();
    }

    public void setAssistsTotal(int assistsTotal) {
        this.assistsTotal.set(assistsTotal);
    }

    public IntegerProperty assistsTotalProperty() {
        return assistsTotal;
    }

    public double getAverageRating() {
        return averageRating.get();
    }

    public void setAverageRating(double averageRating) {
        this.averageRating.set(averageRating);
    }

    public DoubleProperty averageRatingProperty() {
        return averageRating;
    }

    public int getSeasonYear() {
        return seasonYear.get();
    }

    public void setSeasonYear(int seasonYear) {
        this.seasonYear.set(seasonYear);
    }

    public IntegerProperty seasonYearProperty() {
        return seasonYear;
    }

    public String getImagePath() {
        return imagePath.get();
    }

    public void setImagePath(String imagePath) {
        this.imagePath.set(imagePath);
    }

    public StringProperty imagePathProperty() {
        return imagePath;
    }

}
