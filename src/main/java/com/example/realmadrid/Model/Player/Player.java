package com.example.realmadrid.Model.Player;

import java.util.Objects;

public class Player {
    private String name;
    private String position;
    private String imagePath;

    public Player() {
        // Default constructor needed for JSON deserialization
    }

    public Player(String name, String position, String imagePath) {
        this.name = name;
        this.position = position;
        this.imagePath = imagePath;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    @Override
    public String toString() {
        return name + " (" + position + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                Objects.equals(position, player.position) &&
                Objects.equals(imagePath, player.imagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position, imagePath);
    }
}
