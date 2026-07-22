package com.example.realmadrid.Model.Player;

public class Position {
    private double x; // normalized 0-1 (percent across width)
    private double y; // normalized 0-1 (percent down height)

    public Position() {
    }

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public void setX(double x) { this.x = x; }

    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
}
