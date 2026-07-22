package com.example.realmadrid.Model;

import com.example.realmadrid.Model.Player.PlayerPerformance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerPerformanceDAO {
    public Connection getConnection() throws SQLException {
        return DatabaseConfig.getConnection();
    }

    public PlayerPerformance getByPlayerName(String name) throws SQLException {
        String sql = "SELECT * FROM playerperformance WHERE playerName = ?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                } else {
                    return null;
                }
            }
        }
    }



    public List<PlayerPerformance> getAll() throws SQLException {
        String sql = "SELECT * FROM playerperformance";
        try (Connection c = getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            List<PlayerPerformance> list = new ArrayList<>();
            while (rs.next()) {
                PlayerPerformance p = mapRow(rs);
                list.add(p);
            }
            return list;
        }
    }

    public void insert(PlayerPerformance p) throws SQLException {
        String sql = "INSERT INTO playerperformance "
                + "(clubName, playerName, nationality, appearancesTotal, appearancesLaLiga, "
                + "appearancesChampionsLeague, appearancesCopaDelRey, appearancesUEFASuperCup, "
                + "appearancesNationalTeam, goalsTotal, goalsLaLiga, goalsChampionsLeague, "
                + "goalsCopaDelRey, goalsUEFASuperCup, goalsNationalTeam, assistsTotal, "
                + "assistsLaLiga, assistsChampionsLeague, assistsCopaDelRey, assistsUEFASuperCup, "
                + "manOfTheMatchCount, averageRating, seasonYear, imagePath) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            bind(ps, p, false);
            ps.executeUpdate();
        }
    }

    public void update(PlayerPerformance p) throws SQLException {
        String sql = "UPDATE playerperformance SET "
                + "clubName=?, playerName=?, nationality=?, appearancesTotal=?, appearancesLaLiga=?, "
                + "appearancesChampionsLeague=?, appearancesCopaDelRey=?, appearancesUEFASuperCup=?, "
                + "appearancesNationalTeam=?, goalsTotal=?, goalsLaLiga=?, goalsChampionsLeague=?, "
                + "goalsCopaDelRey=?, goalsUEFASuperCup=?, goalsNationalTeam=?, assistsTotal=?, "
                + "assistsLaLiga=?, assistsChampionsLeague=?, assistsCopaDelRey=?, assistsUEFASuperCup=?, "
                + "manOfTheMatchCount=?, averageRating=?, seasonYear=?, imagePath=? WHERE id=?;";

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            bind(ps, p, true);
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM playerperformance WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private void bind(PreparedStatement ps, PlayerPerformance p, boolean includeId) throws SQLException {
        int i = 1;
        ps.setString(i++, p.getClubName());
        ps.setString(i++, p.getPlayerName());
        ps.setString(i++, p.getNationality());
        ps.setInt(i++, p.getAppearancesTotal());
        ps.setInt(i++, p.getAppearancesLaLiga());
        ps.setInt(i++, p.getAppearancesChampionsLeague());
        ps.setInt(i++, p.getAppearancesCopaDelRey());
        ps.setInt(i++, p.getAppearancesUEFASuperCup());
        ps.setInt(i++, p.getAppearancesNationalTeam());
        ps.setInt(i++, p.getGoalsTotal());
        ps.setInt(i++, p.getGoalsLaLiga());
        ps.setInt(i++, p.getGoalsChampionsLeague());
        ps.setInt(i++, p.getGoalsCopaDelRey());
        ps.setInt(i++, p.getGoalsUEFASuperCup());
        ps.setInt(i++, p.getGoalsNationalTeam());
        ps.setInt(i++, p.getAssistsTotal());
        ps.setInt(i++, p.getAssistsLaLiga());
        ps.setInt(i++, p.getAssistsChampionsLeague());
        ps.setInt(i++, p.getAssistsCopaDelRey());
        ps.setInt(i++, p.getAssistsUEFASuperCup());
        ps.setInt(i++, p.getManOfTheMatchCount());
        ps.setDouble(i++, p.getAverageRating());
        ps.setInt(i++, p.getSeasonYear());
        ps.setString(i++, p.getImagePath());
        if (includeId) {
            ps.setInt(i, p.getId());
        }
    }

    private PlayerPerformance mapRow(ResultSet rs) throws SQLException {
        PlayerPerformance p = new PlayerPerformance();
        p.setId(rs.getInt("id"));
        p.setClubName(rs.getString("clubName"));
        p.setPlayerName(rs.getString("playerName"));
        p.setNationality(rs.getString("nationality"));
        p.setAppearancesTotal(rs.getInt("appearancesTotal"));
        p.setAppearancesLaLiga(rs.getInt("appearancesLaLiga"));
        p.setAppearancesChampionsLeague(rs.getInt("appearancesChampionsLeague"));
        p.setAppearancesCopaDelRey(rs.getInt("appearancesCopaDelRey"));
        p.setAppearancesUEFASuperCup(rs.getInt("appearancesUEFASuperCup"));
        p.setAppearancesNationalTeam(rs.getInt("appearancesNationalTeam"));
        p.setGoalsTotal(rs.getInt("goalsTotal"));
        p.setGoalsLaLiga(rs.getInt("goalsLaLiga"));
        p.setGoalsChampionsLeague(rs.getInt("goalsChampionsLeague"));
        p.setGoalsCopaDelRey(rs.getInt("goalsCopaDelRey"));
        p.setGoalsUEFASuperCup(rs.getInt("goalsUEFASuperCup"));
        p.setGoalsNationalTeam(rs.getInt("goalsNationalTeam"));
        p.setAssistsTotal(rs.getInt("assistsTotal"));
        p.setAssistsLaLiga(rs.getInt("assistsLaLiga"));
        p.setAssistsChampionsLeague(rs.getInt("assistsChampionsLeague"));
        p.setAssistsCopaDelRey(rs.getInt("assistsCopaDelRey"));
        p.setAssistsUEFASuperCup(rs.getInt("assistsUEFASuperCup"));
        p.setManOfTheMatchCount(rs.getInt("manOfTheMatchCount"));
        p.setAverageRating(rs.getDouble("averageRating"));
        p.setSeasonYear(rs.getInt("seasonYear"));
        p.setImagePath(rs.getString("imagePath"));
        return p;
    }
}
