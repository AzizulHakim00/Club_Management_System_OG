package com.example.realmadrid.Model.Player;

import javafx.beans.property.*;

public class AllPlayer {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty clubName = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty position = new SimpleStringProperty();
    private final StringProperty nationality = new SimpleStringProperty();
    private final IntegerProperty age = new SimpleIntegerProperty();
    private final StringProperty sex = new SimpleStringProperty();
    private final IntegerProperty weightKg = new SimpleIntegerProperty();
    private final IntegerProperty heightCm = new SimpleIntegerProperty();
    private final IntegerProperty ratingFifaOverall = new SimpleIntegerProperty();
    private final StringProperty midIssues = new SimpleStringProperty();
    private final StringProperty imagePath = new SimpleStringProperty();

    public AllPlayer() {
        // empty constructor
    }

    public AllPlayer(String clubName, String name, String position, String nationality,
                     int age, char sex, int weightKg, int heightCm, int ratingFifaOverall,
                     String midIssues, String imagePath) {
        this.clubName.set(clubName);
        this.name.set(name);
        this.position.set(position);
        this.nationality.set(nationality);
        this.age.set(age);
        this.sex.set(String.valueOf(sex));
        this.weightKg.set(weightKg);
        this.heightCm.set(heightCm);
        this.ratingFifaOverall.set(ratingFifaOverall);
        this.midIssues.set(midIssues);
        this.imagePath.set(imagePath);
    }

    // id getter only (no setter)
    public int getId() { return id.get(); }
    public IntegerProperty idProperty() { return id; }
    public void setId(int id) { this.id.set(id); } // you can keep this private or remove if you want

    // Other getters/setters

    public String getClubName() { return clubName.get(); }
    public void setClubName(String clubName) { this.clubName.set(clubName); }
    public StringProperty clubNameProperty() { return clubName; }

    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }
    public StringProperty nameProperty() { return name; }

    public String getPosition() { return position.get(); }
    public void setPosition(String position) { this.position.set(position); }
    public StringProperty positionProperty() { return position; }

    public String getNationality() { return nationality.get(); }
    public void setNationality(String nationality) { this.nationality.set(nationality); }
    public StringProperty nationalityProperty() { return nationality; }

    public int getAge() { return age.get(); }
    public void setAge(int age) { this.age.set(age); }
    public IntegerProperty ageProperty() { return age; }

    public char getSex() { return sex.get().isEmpty() ? ' ' : sex.get().charAt(0); }
    public void setSex(char sex) { this.sex.set(String.valueOf(sex)); }
    public StringProperty sexProperty() { return sex; }

    public int getWeightKg() { return weightKg.get(); }
    public void setWeightKg(int weightKg) { this.weightKg.set(weightKg); }
    public IntegerProperty weightKgProperty() { return weightKg; }

    public int getHeightCm() { return heightCm.get(); }
    public void setHeightCm(int heightCm) { this.heightCm.set(heightCm); }
    public IntegerProperty heightCmProperty() { return heightCm; }

    public int getRatingFifaOverall() { return ratingFifaOverall.get(); }
    public void setRatingFifaOverall(int ratingFifaOverall) { this.ratingFifaOverall.set(ratingFifaOverall); }
    public IntegerProperty ratingFifaOverallProperty() { return ratingFifaOverall; }

    public String getMidIssues() { return midIssues.get(); }
    public void setMidIssues(String midIssues) { this.midIssues.set(midIssues); }
    public StringProperty midIssuesProperty() { return midIssues; }

    public String getImagePath() { return imagePath.get(); }
    public void setImagePath(String imagePath) { this.imagePath.set(imagePath); }
    public StringProperty imagePathProperty() { return imagePath; }

    @Override
    public String toString() {
        return getName() + " (" + getPosition() + ")";
    }
}
