package com.example.realmadrid.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class TrainingDAO {

    private Connection connect() throws SQLException {
        return DatabaseConfig.getConnection();
    }

    public ObservableList<TrainingSession> getAllSessions() throws SQLException {
        ObservableList<TrainingSession> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM training_sessions";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new TrainingSession(
                        rs.getInt("id"),
                        rs.getString("player_name"),
                        rs.getDate("date").toLocalDate(),
                        rs.getString("session_type"),
                        rs.getString("attendance_status"),
                        rs.getString("fitness_level"),
                        rs.getString("injury_notes")
                ));
            }
        }
        return list;
    }

    public void insertSession(TrainingSession session) throws SQLException {
        String sql = "INSERT INTO training_sessions (player_name, date, session_type, attendance_status, fitness_level, injury_notes) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, session.getPlayerName());
            ps.setDate(2, Date.valueOf(session.getDate()));
            ps.setString(3, session.getSessionType());
            ps.setString(4, session.getAttendanceStatus());
            ps.setString(5, session.getFitnessLevel());
            ps.setString(6, session.getInjuryNotes());
            ps.executeUpdate();
        }
    }

    public void updateSession(TrainingSession session) throws SQLException {
        String sql = "UPDATE training_sessions SET player_name=?, date=?, session_type=?, attendance_status=?, fitness_level=?, injury_notes=? WHERE id=?";
        try (Connection conn = connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, session.getPlayerName());
            ps.setDate(2, Date.valueOf(session.getDate()));
            ps.setString(3, session.getSessionType());
            ps.setString(4, session.getAttendanceStatus());
            ps.setString(5, session.getFitnessLevel());
            ps.setString(6, session.getInjuryNotes());
            ps.setInt(7, session.getPlayerId());
            ps.executeUpdate();
        }
    }

    public void deleteSession(int id) throws SQLException {
        String sql = "DELETE FROM training_sessions WHERE id=?";
        try (Connection conn = connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public double getAverageFitnessForPlayer(String playerName) {
        String sql = "SELECT fitness_level FROM training_sessions WHERE player_name = ?";
        List<Integer> fitnessValues = new ArrayList<>();

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, playerName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String level = rs.getString("fitness_level");
                int score = switch (level.toLowerCase()) {
                    case "high" -> 3;
                    case "medium" -> 2;
                    case "low" -> 1;
                    default -> 0;
                };
                fitnessValues.add(score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fitnessValues.isEmpty() ? 0 : fitnessValues.stream().mapToInt(i -> i).average().orElse(0);
    }

    public List<TrainingSession> getSessionsForPlayer(String playerName) throws SQLException {
        return getAllSessions().stream().filter(s -> s.getPlayerName().equals(playerName)).toList();
    }

    public long countByType(String type) throws SQLException {
        return getAllSessions().stream().filter(s -> s.getSessionType().equalsIgnoreCase(type)).count();
    }

    public long countByAttendance(String status) throws SQLException {
        return getAllSessions().stream().filter(s -> s.getAttendanceStatus().equalsIgnoreCase(status)).count();
    }
}
