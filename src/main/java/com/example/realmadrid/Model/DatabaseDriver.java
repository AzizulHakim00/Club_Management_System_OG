package com.example.realmadrid.Model;

import com.example.realmadrid.Views.TournamentOption;

import java.sql.*;
import java.time.LocalDate;


/* INSERT INTO match_statistics (
    matchday, match_date, tournament, opponent_club, venue, stadium,
    my_score, opponent_score, my_goal_scorers, opponent_goal_scorers,
    result, my_total_shots, opponent_total_shots, my_shots_on_target, opponent_shots_on_target,
    my_possession, opponent_possession, my_passes, opponent_passes,
    my_pass_accuracy, opponent_pass_accuracy, my_fouls, opponent_fouls,
    my_yellow_cards, opponent_yellow_cards, my_red_cards, opponent_red_cards,
    my_offsides, opponent_offsides, my_corners, opponent_corners)

 */


  /*  for matches

    match_date, tournament, my_score, opponent_score, result,
   */
/* for preview

 */

public class DatabaseDriver {
    private Connection conn;
    public DatabaseDriver(){
        try{
            String url = DatabaseConfig.url();
            String username = DatabaseConfig.username();
            String password = DatabaseConfig.password();

            this.conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection to MySQL database established.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // match section

    public ResultSet getAllMatchData(){
        Statement statement ;
        ResultSet resultSet = null;
        try{
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM match_statistics ;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public ResultSet getOnlySmallMatchData(int Limit){
        Statement statement ;
        ResultSet resultSet = null;

        String query = "SELECT match_date, tournament, opponent_club, my_score, opponent_score, result FROM match_statistics ORDER BY match_date DESC LIMIT " + Limit + ";";

        try{
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }


    public void inserMatch(){

    }

    public void updateMatch(){

    }
    public void deleteMatch(){

    }


   // String date = "2025-03-10";

    public ResultSet getNextOrLastMatch(String tournament, LocalDate day) {
      //  String date = day.toString();
        Statement statement;
        ResultSet resultSet;

        String date = "2025-03-10";

        String nextMatchQuery = "SELECT match_date, match_time, opponent_club, venue, stadium " +
                "FROM match_statistics " +
                "WHERE tournament = '" + tournament + "' " +
                "AND match_date > '" + date + "' " +
                "ORDER BY match_date ASC, match_time ASC " +
                "LIMIT 1";

        String lastMatchQuery = "SELECT match_date, match_time , venue ,  opponent_club, my_score, opponent_score, result , stadium " +
                "FROM match_statistics " +
                "WHERE tournament = '" + tournament + "' " +
                "AND result IS NOT NULL " +
                "AND stadium IS NOT NULL " +
                "AND my_score IS NOT NULL " +
                "AND opponent_score IS NOT NULL " +
                "AND match_date <= '" + date + "' " +
                "ORDER BY match_date DESC " +
                "LIMIT 1";

        try {
            statement = this.conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );

            resultSet = statement.executeQuery(nextMatchQuery);

            if (resultSet.next()) {
                resultSet.beforeFirst();  // Safe now
                Model.getInstance().setNextOrLastMatch(false);
            } else {
                resultSet = statement.executeQuery(lastMatchQuery);
                if (resultSet.next()) {
                    resultSet.beforeFirst();  // Also safe
                    Model.getInstance().setNextOrLastMatch(true);
                }
            }

            return resultSet;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getStrengthsResultSet(String clubName) {
        ResultSet rs = null;
        String query = "SELECT strength, strength_level FROM club_info WHERE club_name = ? AND strength IS NOT NULL";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, clubName);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }


    public ResultSet getWeaknessesResultSet(String clubName) {
        ResultSet rs = null;
        String query = "SELECT weakness, weakness_level FROM club_info WHERE club_name = ? AND weakness IS NOT NULL";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, clubName);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getPlayingStylesResultSet(String clubName) {
        ResultSet rs = null;
        String query = "SELECT playing_style FROM club_info WHERE club_name = ? AND playing_style IS NOT NULL";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, clubName);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    //         SELECT " +
//                   "touches_left_percent, touches_middle_percent, touches_right_percent, " +
//                    "touches_own_third_percent, touches_middle_third_percent, touches_opponent_third_percent, " +
//                    "shots_left_percent, shots_middle_percent, shots_right_percent, " +
//                 "shots_small_box_percent, shots_penalty_box_percent, shots_outside_box_percent, " +
//                  "against_shots_left_percent, against_shots_middle_percent, against_shots_right_percent, " +
//                "against_shots_small_box_percent, against_shots_penalty_box_percent, against_shots_outside_box_percent " +
//                  "WHERE club_name = 'Barcelona' " +
//                "LIMIT 1;";


    public ResultSet getClubStatistics(String clubName) throws SQLException {
        String query = "SELECT * FROM club_statistics WHERE club_name = ?";

        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, clubName);

        return pstmt.executeQuery();
    }



    ///  GET PLAYER LIST BY CLUB NAME

    public ResultSet getPlayerListByClub(String clubName) throws SQLException {
        String query = "Select * from allplayers where clubName = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, clubName);
        return pstmt.executeQuery();
    }

    ///  get player fifa rating by name

    public ResultSet getFifaStatsByname(String player) throws SQLException {
        String query = "SELECT * FROM playerfifaratings WHERE name = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1,player);
        return stmt.executeQuery();
    }



  // get allplayer data base ;;

    public ResultSet getSinglePlayerData(String playerName) throws SQLException {
        String query = "SELECT * FROM allplayers WHERE name = ?";

        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, playerName);
        return pstmt.executeQuery();
    }


    public ResultSet getAllPlayersData() throws SQLException {
        String query = "SELECT * FROM allplayers";
        PreparedStatement pstmt = conn.prepareStatement(query);
        return pstmt.executeQuery();
    }


    // get all transfer window player

    public ResultSet getAllTransferPlayer(){
        String query = "SELECT * FROM transferwindow";
        Statement statement ;
        ResultSet resultSet = null;
        try{
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery(query);
        }
        catch (SQLException e){
           e.printStackTrace();
        }
        return resultSet;

    }


    public ResultSet getSinglePlayerDataFromTransfer(String playerName) throws SQLException {
        String query = "SELECT * FROM transferwindow WHERE playerName = ?";

        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, playerName);
        return pstmt.executeQuery();
    }


    // ------  after buy save data ------


    // transfer window db


        // Insert method
// Assumes: this.conn is a valid open java.sql.Connection
// Uses java.time.LocalDate for UI dates

    /* ---------------------------- INSERT ---------------------------- */
    public void insertTransferWindowRecord(
            String playerName,
            String position,
            int rating,
            String nationality,
            String leftClub,
            LocalDate leftDate,
            String joiningClub,
            LocalDate joiningDate,
            double marketValue,
            String tradeType,
            String imagePath // optional, nullable
    ) throws SQLException {

        String sql = """
        INSERT INTO transferwindow
        (playerName, position, rating, nationality,
         leftClub, leftDate, joiningClub, joiningDate,
         marketValue, tradeType, imagePath)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, playerName);
            stmt.setString(2, position);
            stmt.setInt(3, rating);
            stmt.setString(4, nationality);
            stmt.setString(5, leftClub);

            stmt.setDate(6, leftDate != null ? java.sql.Date.valueOf(leftDate) : null);
            stmt.setString(7, joiningClub);
            stmt.setDate(8, joiningDate != null ? java.sql.Date.valueOf(joiningDate) : null);

            stmt.setDouble(9, marketValue);
            stmt.setString(10, tradeType);

            if (imagePath == null || imagePath.isBlank()) {
                stmt.setNull(11, java.sql.Types.VARCHAR);
            } else {
                stmt.setString(11, imagePath);
            }

            stmt.executeUpdate();
        }
    }

    /* ---------------------------- UPDATE ---------------------------- */
    public void updateTransferWindowRecordByPlayerName(
            String playerName, // lookup key (not ideal long-term)
            String position,
            int rating,
            String nationality,
            String leftClub,
            LocalDate leftDate,
            String joiningClub,
            LocalDate joiningDate,
            double marketValue,
            String tradeType,
            String imagePath // optional
    ) throws SQLException {

        String sql = """
        UPDATE transferwindow SET
            position = ?, rating = ?, nationality = ?,
            leftClub = ?, leftDate = ?, joiningClub = ?, joiningDate = ?,
            marketValue = ?, tradeType = ?, imagePath = ?
        WHERE playerName = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, position);
            stmt.setInt(2, rating);
            stmt.setString(3, nationality);
            stmt.setString(4, leftClub);
            stmt.setDate(5, leftDate != null ? java.sql.Date.valueOf(leftDate) : null);
            stmt.setString(6, joiningClub);
            stmt.setDate(7, joiningDate != null ? java.sql.Date.valueOf(joiningDate) : null);
            stmt.setDouble(8, marketValue);
            stmt.setString(9, tradeType);

            if (imagePath == null || imagePath.isBlank()) {
                stmt.setNull(10, java.sql.Types.VARCHAR);
            } else {
                stmt.setString(10, imagePath);
            }

            stmt.setString(11, playerName); // WHERE clause
            stmt.executeUpdate();
        }
    }

    /* ---------------------------- DELETE ---------------------------- */
    public void deleteTransferWindowRecordByPlayerName(String playerName) throws SQLException {
        String sql = "DELETE FROM transferwindow WHERE playerName = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, playerName);
            stmt.executeUpdate();
        }
    }

    public void sellPlayer(String playerName, String leftClub, LocalDate leftDate) throws SQLException {
        String sql = "UPDATE transferwindow SET " +
                "leftClub = ?, " +
                "leftDate = ?, " +
                "joiningClub = NULL, " +
                "joiningDate = NULL " +
                "WHERE playerName = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, leftClub);
            stmt.setDate(2, java.sql.Date.valueOf(leftDate));
            stmt.setString(3, playerName);
            int updated = stmt.executeUpdate();
            if (updated == 0) {
                throw new SQLException("No player updated for playerName=" + playerName);
            }
        }
    }





    ///   matchd preview Section


        public MatchPreview getMatchByDate(LocalDate matchDate) {
            MatchPreview match = new MatchPreview();

            String sql = "SELECT * FROM match_statistics WHERE match_date = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setDate(1, Date.valueOf(matchDate));
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    match.setMyScore(rs.getInt("my_score"));
                    match.setOpponentScore(rs.getInt("opponent_score"));
                    match.setMyTotalShots(rs.getInt("my_total_shots"));
                    match.setOpponentTotalShots(rs.getInt("opponent_total_shots"));
                    match.setMyShotsOnTarget(rs.getInt("my_shots_on_target"));
                    match.setOpponentShotsOnTarget(rs.getInt("opponent_shots_on_target"));
                    match.setMyPossession(rs.getDouble("my_possession"));
                    match.setOpponentPossession(rs.getDouble("opponent_possession"));
                    match.setMyPasses(rs.getInt("my_passes"));
                    match.setOpponentPasses(rs.getInt("opponent_passes"));
                    match.setMyPassAccuracy(rs.getDouble("my_pass_accuracy"));
                    match.setOpponentPassAccuracy(rs.getDouble("opponent_pass_accuracy"));
                    match.setMyFouls(rs.getInt("my_fouls"));
                    match.setOpponentFouls(rs.getInt("opponent_fouls"));
                    match.setMyYellowCards(rs.getInt("my_yellow_cards"));
                    match.setOpponentYellowCards(rs.getInt("opponent_yellow_cards"));
                    match.setMyRedCards(rs.getInt("my_red_cards"));
                    match.setOpponentRedCards(rs.getInt("opponent_red_cards"));
                    match.setMyOffsides(rs.getInt("my_offsides"));
                    match.setOpponentOffsides(rs.getInt("opponent_offsides"));
                    match.setMyCorners(rs.getInt("my_corners"));
                    match.setOpponentCorners(rs.getInt("opponent_corners"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return match;
        }






        ///  admin section here ;;



}








