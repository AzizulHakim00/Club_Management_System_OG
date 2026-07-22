package com.example.realmadrid.Model.Player;

import javafx.beans.property.*;
import javafx.scene.image.Image;

public class FifaPlayer {
    private final StringProperty name = new SimpleStringProperty();
    private final ObjectProperty<Image> fifaImage = new SimpleObjectProperty<>();
    private final IntegerProperty pac = new SimpleIntegerProperty();
    private final IntegerProperty shot = new SimpleIntegerProperty();
    private final IntegerProperty pass = new SimpleIntegerProperty();
    private final IntegerProperty dribbling = new SimpleIntegerProperty();
    private final IntegerProperty defense = new SimpleIntegerProperty();
    private final IntegerProperty physical = new SimpleIntegerProperty();

    public  FifaPlayer() {

    }

    public FifaPlayer(String name, Image fifaImage, int pac, int shot, int pass, int dribbling, int defense, int physical) {
        this.name.set(name);
        this.fifaImage.set(fifaImage);
        this.pac.set(pac);
        this.shot.set(shot);
        this.pass.set(pass);
        this.dribbling.set(dribbling);
        this.defense.set(defense);
        this.physical.set(physical);
    }

    public StringProperty nameProperty() { return name; }
    public ObjectProperty<Image> fifaImageProperty() { return fifaImage; }
    public IntegerProperty pacProperty() { return pac; }
    public IntegerProperty shotProperty() { return shot; }
    public IntegerProperty passProperty() { return pass; }
    public IntegerProperty dribblingProperty() { return dribbling; }
    public IntegerProperty defenseProperty() { return defense; }
    public IntegerProperty physicalProperty() { return physical; }

    // Getters (optional if needed)
    public String getName() { return name.get(); }
    public Image getFifaImage() { return fifaImage.get(); }
    public int getPac() { return pac.get(); }
    public int getShot() { return shot.get(); }
    public int getPass() { return pass.get(); }
    public int getDribbling() { return dribbling.get(); }
    public int getDefense() { return defense.get(); }
    public int getPhysical() { return physical.get(); }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setFifaImage(Image fifaImage) {
        this.fifaImage.set(fifaImage);
    }

    public void setPac(int pac) {
        this.pac.set(pac);
    }

    public void setShot(int shot) {
        this.shot.set(shot);
    }

    public void setPass(int pass) {
        this.pass.set(pass);
    }

    public void setDribbling(int dribbling) {
        this.dribbling.set(dribbling);
    }

    public void setDefense(int defense) {
        this.defense.set(defense);
    }

    public void setPhysical(int physical) {
        this.physical.set(physical);
    }


}
