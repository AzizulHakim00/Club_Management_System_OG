package com.example.realmadrid.Model;

import com.example.realmadrid.Model.Player.Player;
import com.example.realmadrid.Model.Player.Position;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class FormationDataManager {

    private static final Gson gson = new Gson();

    public static void saveFormation(File file, String formationName, List<Player> players, List<Position> positions) throws IOException {
        FormationData data = new FormationData(formationName, players, positions);
        try (Writer writer = new FileWriter(file)) {
            gson.toJson(data, writer);
        }
    }

    public static FormationData loadFormation(File file) throws IOException {
        try (Reader reader = new FileReader(file)) {
            Type type = new TypeToken<FormationData>(){}.getType();
            return gson.fromJson(reader, type);
        }
    }

    public static class FormationData {
        private String formationName;
        private List<Player> players;
        private List<Position> positions;

        public FormationData() { }

        public FormationData(String formationName, List<Player> players, List<Position> positions) {
            this.formationName = formationName;
            this.players = players;
            this.positions = positions;
        }

        public String getFormationName() { return formationName; }
        public void setFormationName(String formationName) { this.formationName = formationName; }

        public List<Player> getPlayers() { return players; }
        public void setPlayers(List<Player> players) { this.players = players; }

        public List<Position> getPositions() { return positions; }
        public void setPositions(List<Position> positions) { this.positions = positions; }
    }
}
