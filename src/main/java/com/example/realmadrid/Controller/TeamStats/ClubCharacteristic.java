package com.example.realmadrid.Controller.TeamStats;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClubCharacteristic {
        private StringProperty levelText ;
        private StringProperty elseText;
    public ClubCharacteristic(String levelText , String elseText) {
       this.levelText = new SimpleStringProperty(levelText);
       this.elseText = new SimpleStringProperty(elseText);
    }


    public StringProperty levelTextProperty() {
        return levelText;
    }




    public StringProperty elseTextProperty() {
        return elseText;
    }


}
