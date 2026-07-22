package com.example.realmadrid.Model.Player;

import com.example.realmadrid.Views.TransferOptions;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.Date;

public class TransferPlayer {
        private SimpleStringProperty playerName;

        private StringProperty nationality;
        private StringProperty position;
        private IntegerProperty rating;
        private StringProperty leftClub;
        private StringProperty joinClub;
        private ObjectProperty<LocalDate> leftDate;
        private ObjectProperty<LocalDate> joinDate;
        private DoubleProperty marketValue;
        private StringProperty tradeoption;

        public TransferPlayer() {


        }

        public TransferPlayer(

                String playerName, String nationality , String position, int rating , String leftClub, LocalDate leftDate ,  String joinClub ,  LocalDate joinDate , Double marketValue , String tradeOption) {

                this.playerName = new SimpleStringProperty(playerName);
                this.leftClub = new SimpleStringProperty(leftClub);
                 this.nationality = new SimpleStringProperty(nationality);
                 this.position = new SimpleStringProperty(position);
                 this.rating =  new SimpleIntegerProperty(rating);
                 this.joinClub = new SimpleStringProperty(joinClub);
                 this.leftDate = new SimpleObjectProperty<>(leftDate);
                 this.joinDate = new SimpleObjectProperty<>(joinDate);
                this.marketValue = new SimpleDoubleProperty(marketValue);
                 this.tradeoption = new SimpleStringProperty(tradeOption);


        }

        public String getPlayerName() {
                return playerName.get();
        }

        public SimpleStringProperty playerNameProperty() {
                return playerName;
        }

        public void setPlayerName(String playerName) {
                this.playerName.set(playerName);
        }

        public String getNationality() {
                return nationality.get();
        }

        public StringProperty nationalityProperty() {
                return nationality;
        }

        public void setNationality(String nationality) {
                this.nationality.set(nationality);
        }

        public String getPosition() {
                return position.get();
        }

        public StringProperty positionProperty() {
                return position;
        }

        public void setPosition(String position) {
                this.position.set(position);
        }

        public int getRating() {
                return rating.get();
        }

        public IntegerProperty ratingProperty() {
                return rating;
        }

        public void setRating(int rating) {
                this.rating.set(rating);
        }

        public String getLeftClub() {
                return leftClub.get();
        }

        public StringProperty leftClubProperty() {
                return leftClub;
        }

        public void setLeftClub(String leftClub) {
                this.leftClub.set(leftClub);
        }

        public String getJoinClub() {
                return joinClub.get();
        }

        public StringProperty joinClubProperty() {
                return joinClub;
        }

        public void setJoinClub(String joinClub) {
                this.joinClub.set(joinClub);
        }

        public LocalDate getLeftDate() {
                return leftDate.get();
        }

        public ObjectProperty<LocalDate> leftDateProperty() {
                return leftDate;
        }

        public void setLeftDate(LocalDate leftDate) {
                this.leftDate.set(leftDate);
        }

        public LocalDate getJoinDate() {
                return joinDate.get();
        }

        public ObjectProperty<LocalDate> joinDateProperty() {
                return joinDate;
        }

        public void setJoinDate(LocalDate joinDate) {
                this.joinDate.set(joinDate);
        }

        public double getMarketValue() {
                return marketValue.get();
        }

        public DoubleProperty marketValueProperty() {
                return marketValue;
        }

        public void setMarketValue(double marketValue) {
                this.marketValue.set(marketValue);
        }

        public String getTradeoption() {
                return tradeoption.get();
        }

        public StringProperty tradeoptionProperty() {
                return tradeoption;
        }

        public void setTradeoption(String tradeoption) {
                this.tradeoption.set(tradeoption);
        }
}
