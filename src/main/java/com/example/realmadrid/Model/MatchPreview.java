package com.example.realmadrid.Model;

import javafx.beans.property.*;

public class MatchPreview {
    private final IntegerProperty myScore = new SimpleIntegerProperty();
    private final IntegerProperty opponentScore = new SimpleIntegerProperty();
    private final StringProperty opponentTeamName = new SimpleStringProperty();

    private final IntegerProperty myTotalShots = new SimpleIntegerProperty();
    private final IntegerProperty opponentTotalShots = new SimpleIntegerProperty();
    private final IntegerProperty myShotsOnTarget = new SimpleIntegerProperty();
    private final IntegerProperty opponentShotsOnTarget = new SimpleIntegerProperty();

    private final DoubleProperty myPossession = new SimpleDoubleProperty();
    private final DoubleProperty opponentPossession = new SimpleDoubleProperty();

    private final IntegerProperty myPasses = new SimpleIntegerProperty();
    private final IntegerProperty opponentPasses = new SimpleIntegerProperty();
    private final DoubleProperty myPassAccuracy = new SimpleDoubleProperty();
    private final DoubleProperty opponentPassAccuracy = new SimpleDoubleProperty();

    private final IntegerProperty myFouls = new SimpleIntegerProperty();
    private final IntegerProperty opponentFouls = new SimpleIntegerProperty();
    private final IntegerProperty myYellowCards = new SimpleIntegerProperty();
    private final IntegerProperty opponentYellowCards = new SimpleIntegerProperty();
    private final IntegerProperty myRedCards = new SimpleIntegerProperty();
    private final IntegerProperty opponentRedCards = new SimpleIntegerProperty();

    private final IntegerProperty myOffsides = new SimpleIntegerProperty();
    private final IntegerProperty opponentOffsides = new SimpleIntegerProperty();
    private final IntegerProperty myCorners = new SimpleIntegerProperty();
    private final IntegerProperty opponentCorners = new SimpleIntegerProperty();
    public MatchPreview() {
    }
    public MatchPreview(
            int myScore, int opponentScore, String opponentTeamName ,
            int myTotalShots, int opponentTotalShots,
            int myShotsOnTarget, int opponentShotsOnTarget,
            double myPossession, double opponentPossession,
            int myPasses, int opponentPasses,
            double myPassAccuracy, double opponentPassAccuracy,
            int myFouls, int opponentFouls,
            int myYellowCards, int opponentYellowCards,
            int myRedCards, int opponentRedCards,
            int myOffsides, int opponentOffsides,
            int myCorners, int opponentCorners) {

        this.myScore.set(myScore);
        this.opponentScore.set(opponentScore);
        this.opponentTeamName.set(opponentTeamName);
        this.myTotalShots.set(myTotalShots);
        this.opponentTotalShots.set(opponentTotalShots);
        this.myShotsOnTarget.set(myShotsOnTarget);
        this.opponentShotsOnTarget.set(opponentShotsOnTarget);
        this.myPossession.set(myPossession);
        this.opponentPossession.set(opponentPossession);
        this.myPasses.set(myPasses);
        this.opponentPasses.set(opponentPasses);
        this.myPassAccuracy.set(myPassAccuracy);
        this.opponentPassAccuracy.set(opponentPassAccuracy);
        this.myFouls.set(myFouls);
        this.opponentFouls.set(opponentFouls);
        this.myYellowCards.set(myYellowCards);
        this.opponentYellowCards.set(opponentYellowCards);
        this.myRedCards.set(myRedCards);
        this.opponentRedCards.set(opponentRedCards);
        this.myOffsides.set(myOffsides);
        this.opponentOffsides.set(opponentOffsides);
        this.myCorners.set(myCorners);
        this.opponentCorners.set(opponentCorners);
    }

    // Getters
    public IntegerProperty myScoreProperty() { return myScore; }
    public IntegerProperty opponentScoreProperty() { return opponentScore; }
    public IntegerProperty myTotalShotsProperty() { return myTotalShots; }
    public IntegerProperty opponentTotalShotsProperty() { return opponentTotalShots; }
    public IntegerProperty myShotsOnTargetProperty() { return myShotsOnTarget; }
    public IntegerProperty opponentShotsOnTargetProperty() { return opponentShotsOnTarget; }

    public DoubleProperty myPossessionProperty() { return myPossession; }
    public DoubleProperty opponentPossessionProperty() { return opponentPossession; }

    public IntegerProperty myPassesProperty() { return myPasses; }
    public IntegerProperty opponentPassesProperty() { return opponentPasses; }
    public DoubleProperty myPassAccuracyProperty() { return myPassAccuracy; }
    public DoubleProperty opponentPassAccuracyProperty() { return opponentPassAccuracy; }

    public IntegerProperty myFoulsProperty() { return myFouls; }
    public IntegerProperty opponentFoulsProperty() { return opponentFouls; }
    public IntegerProperty myYellowCardsProperty() { return myYellowCards; }
    public IntegerProperty opponentYellowCardsProperty() { return opponentYellowCards; }
    public IntegerProperty myRedCardsProperty() { return myRedCards; }
    public IntegerProperty opponentRedCardsProperty() { return opponentRedCards; }

    public IntegerProperty myOffsidesProperty() { return myOffsides; }
    public IntegerProperty opponentOffsidesProperty() { return opponentOffsides; }
    public IntegerProperty myCornersProperty() { return myCorners; }
    public IntegerProperty opponentCornersProperty() { return opponentCorners; }

    // Setters
    public void setMyScore(int val) { myScore.set(val); }
    public void setOpponentScore(int val) { opponentScore.set(val); }
    public void setMyTotalShots(int val) { myTotalShots.set(val); }
    public void setOpponentTotalShots(int val) { opponentTotalShots.set(val); }
    public void setMyShotsOnTarget(int val) { myShotsOnTarget.set(val); }
    public void setOpponentShotsOnTarget(int val) { opponentShotsOnTarget.set(val); }
    public void setMyPossession(double val) { myPossession.set(val); }
    public void setOpponentPossession(double val) { opponentPossession.set(val); }
    public void setMyPasses(int val) { myPasses.set(val); }
    public void setOpponentPasses(int val) { opponentPasses.set(val); }
    public void setMyPassAccuracy(double val) { myPassAccuracy.set(val); }
    public void setOpponentPassAccuracy(double val) { opponentPassAccuracy.set(val); }
    public void setMyFouls(int val) { myFouls.set(val); }
    public void setOpponentFouls(int val) { opponentFouls.set(val); }
    public void setMyYellowCards(int val) { myYellowCards.set(val); }
    public void setOpponentYellowCards(int val) { opponentYellowCards.set(val); }
    public void setMyRedCards(int val) { myRedCards.set(val); }
    public void setOpponentRedCards(int val) { opponentRedCards.set(val); }
    public void setMyOffsides(int val) { myOffsides.set(val); }
    public void setOpponentOffsides(int val) { opponentOffsides.set(val); }
    public void setMyCorners(int val) { myCorners.set(val); }
    public void setOpponentCorners(int val) { opponentCorners.set(val); }

    public void setOpponentTeamName(String val){
        opponentTeamName.set(val);
    }
     public StringProperty getOpponentTeamName(){
        return opponentTeamName;
     }

}
