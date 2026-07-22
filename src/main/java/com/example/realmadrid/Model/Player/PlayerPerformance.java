package com.example.realmadrid.Model.Player;

import javafx.beans.property.*;

public class PlayerPerformance {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty clubName = new SimpleStringProperty();
    private final StringProperty playerName = new SimpleStringProperty();
    private final StringProperty nationality = new SimpleStringProperty();
    private final IntegerProperty appearancesTotal = new SimpleIntegerProperty();
    private final IntegerProperty appearancesLaLiga = new SimpleIntegerProperty();
    private final IntegerProperty appearancesChampionsLeague = new SimpleIntegerProperty();
    private final IntegerProperty appearancesCopaDelRey = new SimpleIntegerProperty();
    private final IntegerProperty appearancesUEFASuperCup = new SimpleIntegerProperty();
    private final IntegerProperty appearancesNationalTeam = new SimpleIntegerProperty();
    private final IntegerProperty goalsTotal = new SimpleIntegerProperty();
    private final IntegerProperty goalsLaLiga = new SimpleIntegerProperty();
    private final IntegerProperty goalsChampionsLeague = new SimpleIntegerProperty();
    private final IntegerProperty goalsCopaDelRey = new SimpleIntegerProperty();
    private final IntegerProperty goalsUEFASuperCup = new SimpleIntegerProperty();
    private final IntegerProperty goalsNationalTeam = new SimpleIntegerProperty();
    private final IntegerProperty assistsTotal = new SimpleIntegerProperty();
    private final IntegerProperty assistsLaLiga = new SimpleIntegerProperty();
    private final IntegerProperty assistsChampionsLeague = new SimpleIntegerProperty();
    private final IntegerProperty assistsCopaDelRey = new SimpleIntegerProperty();
    private final IntegerProperty assistsUEFASuperCup = new SimpleIntegerProperty();
    private final IntegerProperty manOfTheMatchCount = new SimpleIntegerProperty();
    private final DoubleProperty averageRating = new SimpleDoubleProperty();
    private final IntegerProperty seasonYear = new SimpleIntegerProperty();
    private final StringProperty imagePath = new SimpleStringProperty();

    // ✅ Constructor
    public PlayerPerformance(int id, String clubName, String playerName, String nationality,
                             int appearancesTotal, int appearancesLaLiga, int appearancesChampionsLeague,
                             int appearancesCopaDelRey, int appearancesUEFASuperCup, int appearancesNationalTeam,
                             int goalsTotal, int goalsLaLiga, int goalsChampionsLeague, int goalsCopaDelRey,
                             int goalsUEFASuperCup, int goalsNationalTeam,
                             int assistsTotal, int assistsLaLiga, int assistsChampionsLeague,
                             int assistsCopaDelRey, int assistsUEFASuperCup,
                             int manOfTheMatchCount, double averageRating, int seasonYear,
                             String imagePath) {
        setId(id);
        setClubName(clubName);
        setPlayerName(playerName);
        setNationality(nationality);
        setAppearancesTotal(appearancesTotal);
        setAppearancesLaLiga(appearancesLaLiga);
        setAppearancesChampionsLeague(appearancesChampionsLeague);
        setAppearancesCopaDelRey(appearancesCopaDelRey);
        setAppearancesUEFASuperCup(appearancesUEFASuperCup);
        setAppearancesNationalTeam(appearancesNationalTeam);
        setGoalsTotal(goalsTotal);
        setGoalsLaLiga(goalsLaLiga);
        setGoalsChampionsLeague(goalsChampionsLeague);
        setGoalsCopaDelRey(goalsCopaDelRey);
        setGoalsUEFASuperCup(goalsUEFASuperCup);
        setGoalsNationalTeam(goalsNationalTeam);
        setAssistsTotal(assistsTotal);
        setAssistsLaLiga(assistsLaLiga);
        setAssistsChampionsLeague(assistsChampionsLeague);
        setAssistsCopaDelRey(assistsCopaDelRey);
        setAssistsUEFASuperCup(assistsUEFASuperCup);
        setManOfTheMatchCount(manOfTheMatchCount);
        setAverageRating(averageRating);
        setSeasonYear(seasonYear);
        setImagePath(imagePath);
    }

    public PlayerPerformance() {

    }

    // ✅ Setters
    public void setId(int id) { this.id.set(id); }
    public void setClubName(String clubName) { this.clubName.set(clubName); }
    public void setPlayerName(String playerName) { this.playerName.set(playerName); }
    public void setNationality(String nationality) { this.nationality.set(nationality); }
    public void setAppearancesTotal(int value) { this.appearancesTotal.set(value); }
    public void setAppearancesLaLiga(int value) { this.appearancesLaLiga.set(value); }
    public void setAppearancesChampionsLeague(int value) { this.appearancesChampionsLeague.set(value); }
    public void setAppearancesCopaDelRey(int value) { this.appearancesCopaDelRey.set(value); }
    public void setAppearancesUEFASuperCup(int value) { this.appearancesUEFASuperCup.set(value); }
    public void setAppearancesNationalTeam(int value) { this.appearancesNationalTeam.set(value); }
    public void setGoalsTotal(int value) { this.goalsTotal.set(value); }
    public void setGoalsLaLiga(int value) { this.goalsLaLiga.set(value); }
    public void setGoalsChampionsLeague(int value) { this.goalsChampionsLeague.set(value); }
    public void setGoalsCopaDelRey(int value) { this.goalsCopaDelRey.set(value); }
    public void setGoalsUEFASuperCup(int value) { this.goalsUEFASuperCup.set(value); }
    public void setGoalsNationalTeam(int value) { this.goalsNationalTeam.set(value); }
    public void setAssistsTotal(int value) { this.assistsTotal.set(value); }
    public void setAssistsLaLiga(int value) { this.assistsLaLiga.set(value); }
    public void setAssistsChampionsLeague(int value) { this.assistsChampionsLeague.set(value); }
    public void setAssistsCopaDelRey(int value) { this.assistsCopaDelRey.set(value); }
    public void setAssistsUEFASuperCup(int value) { this.assistsUEFASuperCup.set(value); }
    public void setManOfTheMatchCount(int value) { this.manOfTheMatchCount.set(value); }
    public void setAverageRating(double value) { this.averageRating.set(value); }
    public void setSeasonYear(int value) { this.seasonYear.set(value); }
    public void setImagePath(String value) { this.imagePath.set(value); }

    // ✅ Getters
    public int getId() { return id.get(); }
    public String getClubName() { return clubName.get(); }
    public String getPlayerName() { return playerName.get(); }
    public String getNationality() { return nationality.get(); }
    public int getAppearancesTotal() { return appearancesTotal.get(); }
    public int getAppearancesLaLiga() { return appearancesLaLiga.get(); }
    public int getAppearancesChampionsLeague() { return appearancesChampionsLeague.get(); }
    public int getAppearancesCopaDelRey() { return appearancesCopaDelRey.get(); }
    public int getAppearancesUEFASuperCup() { return appearancesUEFASuperCup.get(); }
    public int getAppearancesNationalTeam() { return appearancesNationalTeam.get(); }
    public int getGoalsTotal() { return goalsTotal.get(); }
    public int getGoalsLaLiga() { return goalsLaLiga.get(); }
    public int getGoalsChampionsLeague() { return goalsChampionsLeague.get(); }
    public int getGoalsCopaDelRey() { return goalsCopaDelRey.get(); }
    public int getGoalsUEFASuperCup() { return goalsUEFASuperCup.get(); }
    public int getGoalsNationalTeam() { return goalsNationalTeam.get(); }
    public int getAssistsTotal() { return assistsTotal.get(); }
    public int getAssistsLaLiga() { return assistsLaLiga.get(); }
    public int getAssistsChampionsLeague() { return assistsChampionsLeague.get(); }
    public int getAssistsCopaDelRey() { return assistsCopaDelRey.get(); }
    public int getAssistsUEFASuperCup() { return assistsUEFASuperCup.get(); }
    public int getManOfTheMatchCount() { return manOfTheMatchCount.get(); }
    public double getAverageRating() { return averageRating.get(); }
    public int getSeasonYear() { return seasonYear.get(); }
    public String getImagePath() { return imagePath.get(); }

    // ✅ JavaFX Properties
    public IntegerProperty idProperty() { return id; }
    public StringProperty clubNameProperty() { return clubName; }
    public StringProperty playerNameProperty() { return playerName; }
    public StringProperty nationalityProperty() { return nationality; }
    public IntegerProperty appearancesTotalProperty() { return appearancesTotal; }
    public IntegerProperty appearancesLaLigaProperty() { return appearancesLaLiga; }
    public IntegerProperty appearancesChampionsLeagueProperty() { return appearancesChampionsLeague; }
    public IntegerProperty appearancesCopaDelReyProperty() { return appearancesCopaDelRey; }
    public IntegerProperty appearancesUEFASuperCupProperty() { return appearancesUEFASuperCup; }
    public IntegerProperty appearancesNationalTeamProperty() { return appearancesNationalTeam; }
    public IntegerProperty goalsTotalProperty() { return goalsTotal; }
    public IntegerProperty goalsLaLigaProperty() { return goalsLaLiga; }
    public IntegerProperty goalsChampionsLeagueProperty() { return goalsChampionsLeague; }
    public IntegerProperty goalsCopaDelReyProperty() { return goalsCopaDelRey; }
    public IntegerProperty goalsUEFASuperCupProperty() { return goalsUEFASuperCup; }
    public IntegerProperty goalsNationalTeamProperty() { return goalsNationalTeam; }
    public IntegerProperty assistsTotalProperty() { return assistsTotal; }
    public IntegerProperty assistsLaLigaProperty() { return assistsLaLiga; }
    public IntegerProperty assistsChampionsLeagueProperty() { return assistsChampionsLeague; }
    public IntegerProperty assistsCopaDelReyProperty() { return assistsCopaDelRey; }
    public IntegerProperty assistsUEFASuperCupProperty() { return assistsUEFASuperCup; }
    public IntegerProperty manOfTheMatchCountProperty() { return manOfTheMatchCount; }
    public DoubleProperty averageRatingProperty() { return averageRating; }
    public IntegerProperty seasonYearProperty() { return seasonYear; }
    public StringProperty imagePathProperty() { return imagePath; }
}
