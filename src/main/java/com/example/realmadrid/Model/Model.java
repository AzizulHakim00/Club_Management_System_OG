package com.example.realmadrid.Model;

import com.example.realmadrid.Controller.Couch.TransferAllPopUp.AllPlayersController;
import com.example.realmadrid.Model.Player.AllPlayer;
import com.example.realmadrid.Model.Player.Player;
import com.example.realmadrid.Model.Player.PlayerStatus;
import com.example.realmadrid.Model.Player.TransferPlayer;
import com.example.realmadrid.Views.TournamentOption;
import com.example.realmadrid.Controller.TeamStats.ClubCharacteristic;
import com.example.realmadrid.Views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Model {



    private static  Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;

    private String clubName;
    private String playerName;

    // in dash board

    private boolean clientLoginSuccessFlag ;


    // for matach data ucoming  match


    private final UpcomingMatch upcomingMatch;
    private TournamentOption tournamentOption;
    private boolean nextOrLastMatch ;


    //  tema_stats ;


    // lates and all match


    private final Match match;
    private final ObservableList<Match> latestMatchList ;
    private final ObservableList<Match> allMatchList ;


    // out dash board

    // in gameplan


    private final ClubCharacteristic clubCharacteristic;
    private final ObservableList<ClubCharacteristic> characteristics;



    // out gameplan



    // in transfer window

    private final TransferPlayer transferPlayer;
    private final ObservableList<TransferPlayer> allTransferPlayers;
    private final ObservableList<TransferPlayer> ownedPlayers;




    private boolean isAllPlayerDataHave ;

    private final ObservableList<AllPlayer> allPlayers;
    private final AllPlayer allPlayer;

    private final PlayerStatus playerStatus;
    private final ObservableList<PlayerStatus> playerStatusList;


    // out transfer window

    private final MatchPreview matchPreview;



    // admin section

    private boolean adminLoginSuccessFlag;

    private Model(){


        this.matchPreview = new MatchPreview(0, 0,"", 0, 0, 0, 0, 0.0, 0.0, 0, 0, 0.0, 0.0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

        // couch section

        this.clientLoginSuccessFlag = false;
        this.playerName = "";

        // gameplan --- charactaristics ----


        this.clubName = "";

       //   this.tournamentName = "";
        this.characteristics = FXCollections.observableArrayList();

        this.clubCharacteristic = new ClubCharacteristic("" ,"");
        this.tournamentOption = TournamentOption.SSL;

        ///  next or last match ----

        this.nextOrLastMatch = false;
        this.upcomingMatch = new UpcomingMatch( null, "" ,"" ,"" , "" ,0,0,"" );


        // -----   match history --- and all match----

        this.latestMatchList = FXCollections.observableArrayList();
        this.allMatchList = FXCollections.observableArrayList();
        this.match =  new Match(null,"","",0,0,"" ,"");


        // transfer window
        this.allTransferPlayers = FXCollections.observableArrayList();

        this.transferPlayer = new TransferPlayer( "" , "" , "" , 0 , "" , null , "" ,null , 0.00 , "" );
        this.ownedPlayers = FXCollections.observableArrayList();

        this.isAllPlayerDataHave = false;

        this.allPlayers = FXCollections.observableArrayList();
        this.allPlayer = new AllPlayer( "" , "" , "" , "" , 0 , 'M' , 0 , 0 , 0 , "" , "");

        this.playerStatus = new PlayerStatus();
        this.playerStatusList = FXCollections.observableArrayList();

        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();


        // admin section

        this.adminLoginSuccessFlag = false;


    }



    public static synchronized Model getInstance(){
        if(model == null){
            model = new Model();
        }
        return model;
    }



    ///  evaluation
  //  evaluateClientCred(payee_address_field.getText(), password_field.getText());

    public void evaluateClientCred(String name , String password){
        if(name.isEmpty() || password.isEmpty()){
            this.clientLoginSuccessFlag = false;
        }
        if(name.equals("xabi") && password.equals("1234")){
            this.clientLoginSuccessFlag = true;
        }
    }



    public void evaluateAdminCred(String name , String password){
        if(name.isEmpty() || password.isEmpty()){
            model.adminLoginSuccessFlag = false;
        } else if (name.equals("admin") && password.equals("1234")) {
            this.adminLoginSuccessFlag = true;

        }
    }

    // couch section



    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubName() {
        return this.clubName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public String getPlayerName() {
        return this.playerName;
    }



    public boolean getClientLoginSuccessFlag() {
        return this.clientLoginSuccessFlag;

    }
    public void setClientLoginSuccessFlag(boolean flag) {
        this.clientLoginSuccessFlag = flag;
    }

    // view factory --- and other main packages ------


    public ViewFactory getViewFactory(){
        return viewFactory;
    }

    public DatabaseDriver getDataBaseDriver() {
        return databaseDriver;
    }


    //  ---- get latest and all match section ----


    public Match getMatch() {
        return match;
    }

    public ObservableList<Match> getLatestMatchList() {
        return latestMatchList;
    }
    public ObservableList<Match> getAllMatchList() {
        return allMatchList;
    }


    public void setAllMatchList() {
        prepareSmallMatchList(this.allMatchList , 7); ;
    }



    public void setLatestMatchList() {
        prepareSmallMatchList(this.latestMatchList , 5); ;
    }

    public void prepareSmallMatchList(ObservableList<Match> matchList , int limit){
        ResultSet resultSet = databaseDriver.getOnlySmallMatchData(limit);
        try{
                    // SELECT match_date, tournament, opponent_club, my_score, opponent_score, result FROM match_statistics where LIMIT "+Limit+" ;";

            while(resultSet.next()){

                String[] dateParts = resultSet.getString("match_date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                String tournament = resultSet.getString("Tournament");
                String opponent_club = resultSet.getString("opponent_club");
                int myScore = resultSet.getInt("my_score");
                int opponent_score = resultSet.getInt("opponent_score");
                String result = resultSet.getString("result");
                matchList.add( new Match(date , tournament , opponent_club , myScore , opponent_score, result, ""));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }










    // ---- upcomig match controller section ----


    public LocalDate date ;  // if needed i kbow put past value predefined date


    public UpcomingMatch getUpcomingMatch() {
        return this.upcomingMatch;
    }




    public void setTournamentOption(TournamentOption tournamentOption) {
        this.tournamentOption = tournamentOption;
    }

    public TournamentOption getTournamentOption() {
        return tournamentOption;
    }



    public void setNextOrLastMatch(boolean flag){
        this.nextOrLastMatch = flag;
    }
    public Boolean getNextOrLastMatch(){
        return this.nextOrLastMatch;
    }




    public void getOneMatch(TournamentOption tournament) throws SQLException {

        LocalDate today = LocalDate.now();
        ResultSet resultSet = databaseDriver.getNextOrLastMatch(String.valueOf(tournament), today);
        boolean lastMatchFlag = Model.getInstance().nextOrLastMatch;  // fetch the flag


        try {
            if (resultSet.next()) {
                String[] dateParts = resultSet.getString("match_date").split("-");
                LocalDate matchDate = LocalDate.of(
                        Integer.parseInt(dateParts[0]),
                        Integer.parseInt(dateParts[1]),
                        Integer.parseInt(dateParts[2])
                );

                String venue = resultSet.getString("venue");
                String opponent_club = resultSet.getString("opponent_club");
                String stadium = resultSet.getString("stadium");

                if (lastMatchFlag) {
                    int myScore = resultSet.getInt("my_score");
                    int opponentScore = resultSet.getInt("opponent_score");
                    String result = resultSet.getString("result");

                    this.upcomingMatch.dateProperty().set(matchDate);
                    this.upcomingMatch.tournamentProperty().set(String.valueOf(tournament));
                    this.upcomingMatch.opponentTeamProperty().set(opponent_club);
                    this.upcomingMatch.myScoreProperty().set(myScore);
                    this.upcomingMatch.opponentScoreProperty().set(opponentScore);

                } else {
                    String time = resultSet.getString("match_time");

                     // new UpcomingMatch(matchDate, time, venue, opponent_club, tournament, 0, 0, stadium);
                    this.upcomingMatch.dateProperty().set(matchDate);
                    this.upcomingMatch.tournamentProperty().set(String.valueOf(tournament));
                    this.upcomingMatch.opponentTeamProperty().set(opponent_club);
                    this.upcomingMatch.timeProperty().set(time);
                    this.upcomingMatch.venueProperty().set(venue);
                    this.upcomingMatch.stadiumProperty().set(stadium);

                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }






    // gameplan --- controller section -----


    public ClubCharacteristic getClubCharacteristic() {
        return clubCharacteristic;
    }

    public ObservableList<ClubCharacteristic> getCharacteristic() {
        return characteristics;
    }




    public void getStrengthOnly(String clubName) {
        ResultSet resultSet = databaseDriver.getStrengthsResultSet(clubName);
        try {
            while (resultSet.next()) {
                String strength = resultSet.getString("strength");
                String level = resultSet.getString("strength_level");
                characteristics.add(new ClubCharacteristic(strength, level));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


  public  void getWeaknessOnly(String clubName) {
        ResultSet resultSet = databaseDriver.getWeaknessesResultSet(clubName);
        try {
            while (resultSet.next()) {
                String strength = resultSet.getString("weakness");
                String level = resultSet.getString("weakness_level");
                characteristics.add(new ClubCharacteristic(strength, level));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void getPlayStyle(String clubName , ObservableList<ClubCharacteristic> playStyle) {
        ResultSet resultSet = databaseDriver.getPlayingStylesResultSet(clubName);
        try {
            while (resultSet.next()) {
                String strength = resultSet.getString("playing_style");

                playStyle.add(new ClubCharacteristic(strength, ""));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<String> getClubPlayerList(String clubName) throws SQLException {
        List<String> playerList = new ArrayList<>();
        ResultSet resultSet = databaseDriver.getPlayerListByClub(clubName);
        try {
            while (resultSet.next()) {
                String player = resultSet.getString("name");
                playerList.add(player);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playerList;

    }



  public  void setCharacteristics(){
        this.characteristics.clear();
    }


    //  transfer ---window section -----

    public ObservableList<TransferPlayer>  getAllTransferPlayer(){
        return this.allTransferPlayers;
    }


    public void setAllTransferPlayers() {
        ResultSet resultSet = databaseDriver.getAllTransferPlayer();

        try {
            while (resultSet.next()) {

                String name = "";
                String playerName = resultSet.getString("playerName");
                if(playerName != null) {
                    name = playerName;
                }

                String nationality = resultSet.getString("nationality");
                int rating = resultSet.getInt("rating");
                String position = resultSet.getString("position");

                String thisClub = "";
                String leftClub = resultSet.getString("leftClub");

                if(leftClub != null){
                    thisClub = leftClub;
                }

                String thatClub = "";
                String joinClub = resultSet.getString("joiningClub");

                if(joinClub != null){
                    thatClub = joinClub;
                }

                // Safely handle nullable joiningDate
                LocalDate joiningDate = null;
                String joiningDateStr = resultSet.getString("joiningDate");
                if (joiningDateStr != null) {
                    String[] dateParts = joiningDateStr.split("-");
                    joiningDate = LocalDate.of(
                            Integer.parseInt(dateParts[0]),
                            Integer.parseInt(dateParts[1]),
                            Integer.parseInt(dateParts[2])
                    );
                }

                // Safely handle nullable leftDate
                LocalDate leftDate = null;
                String leftDateStr = resultSet.getString("leftDate");
                if (leftDateStr != null) {
                    String[] dateParts1 = leftDateStr.split("-");
                    leftDate = LocalDate.of(
                            Integer.parseInt(dateParts1[0]),
                            Integer.parseInt(dateParts1[1]),
                            Integer.parseInt(dateParts1[2])
                    );
                }

                Double marketValue = resultSet.getDouble("marketValue");
                String tradeOption = resultSet.getString("tradeType");

                System.out.println("Loaded from DB successfully");

                allTransferPlayers.add(new TransferPlayer(
                        name, nationality, position, rating,
                        thisClub, leftDate, thatClub, joiningDate,
                        marketValue, tradeOption
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ObservableList<String> getComboList( String type){
        ObservableList<String> comboList = FXCollections.observableArrayList();
        if(this.allTransferPlayers.isEmpty()){
            setCharacteristics();
        }
        if(type.equals("Club")){
            for(TransferPlayer p : this.allTransferPlayers){
                    if(!comboList.contains(p.getLeftClub())){
                        comboList.add(p.getLeftClub());
                    }
            }
        }
        else if(type.equals("Position")){
            for(TransferPlayer p : this.allTransferPlayers){
                if(!comboList.contains(p.getPosition())){
                    comboList.add(p.getPosition());
                }
            }
        }

        FXCollections.sort(comboList);

        return  comboList;
    }



        ///  get transfer player data default in pop up show


        public TransferPlayer getTransferPlayer(){
            return this.transferPlayer;
        }


        public void setTransferPlayer(String Name) throws SQLException {


            ResultSet resultSet = databaseDriver.getSinglePlayerDataFromTransfer(Name);

            if (resultSet.next()) {

                String name = "";
                String playerName = resultSet.getString("playerName");
                if (playerName != null) {
                    name = playerName;
                }
                this.transferPlayer.setPlayerName(name);

                String nationality = resultSet.getString("nationality");
                this.transferPlayer.setNationality(nationality);
                int rating = resultSet.getInt("rating");
                this.transferPlayer.setRating(rating);
                String position = resultSet.getString("position");
                this.transferPlayer.setPosition(position);

                String thisClub = "";
                String leftClub = resultSet.getString("leftClub");


                if (leftClub != null) {
                    thisClub = leftClub;
                }
                this.transferPlayer.setLeftClub(thisClub);

                String thatClub = "";
                String joinClub = resultSet.getString("joiningClub");


                if (joinClub != null) {
                    thatClub = leftClub;
                }
                this.transferPlayer.setJoinClub(thatClub);

                // Safely handle nullable joiningDate
                LocalDate joiningDate = null;
                String joiningDateStr = resultSet.getString("joiningDate");
                if (joiningDateStr != null) {
                    String[] dateParts = joiningDateStr.split("-");
                    joiningDate = LocalDate.of(
                            Integer.parseInt(dateParts[0]),
                            Integer.parseInt(dateParts[1]),
                            Integer.parseInt(dateParts[2])
                    );
                }
                this.transferPlayer.setJoinDate(joiningDate);

                // Safely handle nullable leftDate
                LocalDate leftDate = null;
                String leftDateStr = resultSet.getString("leftDate");
                if (leftDateStr != null) {
                    String[] dateParts1 = leftDateStr.split("-");
                    leftDate = LocalDate.of(
                            Integer.parseInt(dateParts1[0]),
                            Integer.parseInt(dateParts1[1]),
                            Integer.parseInt(dateParts1[2])
                    );
                }
                this.transferPlayer.setLeftDate(leftDate);

                Double marketValue = resultSet.getDouble("marketValue");
                this.transferPlayer.setMarketValue(marketValue);
                String tradeOption = resultSet.getString("tradeType");
                this.transferPlayer.setTradeoption(tradeOption);

            }

        }

        public void setIsAllDataHave(boolean flag)  {
            this.isAllPlayerDataHave = flag;
        }
        public boolean getIsAllPlayerDataHave()  {
            return this.isAllPlayerDataHave;
        }


        public void setAllPlayerSingle(String playerName) throws SQLException {
                ResultSet resultSet = databaseDriver.getSinglePlayerData(playerName);

            if (resultSet.next()) {
                String clubName = resultSet.getString("clubName");
               allPlayer.setClubName(clubName);
                String name = resultSet.getString("name");
                allPlayer.setName(name);
                String position = resultSet.getString("position");
                allPlayer.setPosition(position);
                String nationality = resultSet.getString("nationality");
                allPlayer.setNationality(nationality);
                int age = resultSet.getInt("age");
                allPlayer.setAge(age);
                char sex = resultSet.getString("sex").charAt(0);
                allPlayer.setSex(sex);
                int weightKg = resultSet.getInt("weight_kg");
                allPlayer.setWeightKg(weightKg);
                int heightCm = resultSet.getInt("height_cm");
                allPlayer.setHeightCm(heightCm);
                int ratingFifaOverall = resultSet.getInt("ratingFifaOverall");
                allPlayer.setRatingFifaOverall(ratingFifaOverall);
                String midIssues = resultSet.getString("midIssues");
                allPlayer.setMidIssues(midIssues);
                String imagePath = resultSet.getString("imagePath");
                allPlayer.setImagePath(imagePath);
                setIsAllDataHave(true);
            }
        }


        public AllPlayer getAllPlayerSingle(String playerName){
            return this.allPlayer;
        }

         public ObservableList<AllPlayer> getAllPlayersAll(){
            return  this.allPlayers;
         }

        public void setAllPlayersAll() throws SQLException {
            ResultSet resultSet = databaseDriver.getAllPlayersData();
            try {
                while (resultSet.next()) {
                                String clubName = resultSet.getString("clubName");
                                String name = resultSet.getString("name");
                                String position = resultSet.getString("position");
                                String nationality = resultSet.getString("nationality");
                                int age = resultSet.getInt("age");
                                char sex = resultSet.getString("sex").charAt(0);
                                int weightKg = resultSet.getInt("weight_kg");
                                int heightCm = resultSet.getInt("height_cm");
                                int ratingFifaOverall = resultSet.getInt("ratingFifaOverall");
                                String midIssues = resultSet.getString("midIssues");
                                String imagePath = resultSet.getString("imagePath");

                               allPlayers.add(new AllPlayer(clubName,name,position,nationality,age,sex,weightKg,heightCm,ratingFifaOverall,midIssues,imagePath));

                            }
                        }
            catch (Exception e) {
                e.printStackTrace();
            }
        }


    ///  ----  now tricks for player status -------

    private PlayerStatus pendingStatus ;

    public void setPendingStatus(PlayerStatus p) {
        this.pendingStatus = p;
    }

    public PlayerStatus getPendingStatus() {
        return this.pendingStatus;
    }


    public ObservableList<PlayerStatus> getPlayerStatusList() {
        return this.playerStatusList;
    }
    public PlayerStatus getPlayerStatus() {
        return this.playerStatus;
    }


    public void setPlayerStatus(String playerName) {

        String sql = "SELECT * FROM playerstatus WHERE playerName = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, playerName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
               // PlayerStatus status = new PlayerStatus();
                playerStatus.setPlayerName(rs.getString("playerName"));
                playerStatus.setPlayerClub(rs.getString("playerClub"));
                playerStatus.setSalary(Double.parseDouble(rs.getString("salary")));
                playerStatus.setJoiningDate(rs.getDate("joiningDate").toLocalDate());
                playerStatus.setLeavingDate(rs.getDate("leavingDate").toLocalDate());
                playerStatus.setPlayerBonus(Double.parseDouble(rs.getString("playerBonus")));
                playerStatus.setMedicalStatus(rs.getString("medicalStatus"));
                playerStatus.setLeaveInDate(rs.getDate("leaveInDate") != null ? rs.getDate("leaveInDate").toLocalDate() : null);
                playerStatus.setYellowCardsLAL(rs.getInt("yellowCardsLAL"));
                playerStatus.setRedCardsLAL(rs.getInt("redCardsLAL"));
                playerStatus.setYellowCardsUCL(rs.getInt("yellowCardsUCL"));
                playerStatus.setRedCardsUCL(rs.getInt("redCardsUCL"));
                playerStatus.setYellowCardsCDR(rs.getInt("yellowCardsCDR"));
                playerStatus.setRedCardsCDR(rs.getInt("redCardsCDR"));
                playerStatus.setYellowCardsUSC(rs.getInt("yellowCardsUSC"));
                playerStatus.setRedCardsUSC(rs.getInt("redCardsUSC"));
                playerStatus.setMissUpcomingGames(rs.getInt("missUpcomingGames"));
                playerStatus.setMissLAL(rs.getInt("missLAL"));
                playerStatus.setMissUCL(rs.getInt("missUCL"));
                playerStatus.setMissCDR(rs.getInt("missCDR"));
                playerStatus.setMissUSC(rs.getInt("missUSC"));
                playerStatus.setMedicalDescription(rs.getString("medicalDescription"));
                playerStatus.setImagePath(rs.getString("imagePath"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void setPlayerStatusList() {
        String sql = "SELECT * FROM playerstatus";

        try (Connection conn = DatabaseConfig.getConnection();
            Statement stmt = conn.createStatement();) {

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                PlayerStatus status = new PlayerStatus();
                status.setPlayerName(rs.getString("playerName"));
                status.setPlayerClub(rs.getString("playerClub"));
                status.setSalary(Double.parseDouble(rs.getString("salary")));
                status.setJoiningDate(rs.getDate("joiningDate").toLocalDate());
                status.setLeavingDate(rs.getDate("leavingDate").toLocalDate());
                status.setPlayerBonus(Double.parseDouble(rs.getString("playerBonus")));
                status.setMedicalStatus(rs.getString("medicalStatus"));
                status.setLeaveInDate(rs.getDate("leaveInDate") != null ? rs.getDate("leaveInDate").toLocalDate() : null);
                status.setYellowCardsLAL(rs.getInt("yellowCardsLAL"));
                status.setRedCardsLAL(rs.getInt("redCardsLAL"));
                status.setYellowCardsUCL(rs.getInt("yellowCardsUCL"));
                status.setRedCardsUCL(rs.getInt("redCardsUCL"));
                status.setYellowCardsCDR(rs.getInt("yellowCardsCDR"));
                status.setRedCardsCDR(rs.getInt("redCardsCDR"));
                status.setYellowCardsUSC(rs.getInt("yellowCardsUSC"));
                status.setRedCardsUSC(rs.getInt("redCardsUSC"));
                status.setMissUpcomingGames(rs.getInt("missUpcomingGames"));
                status.setMissLAL(rs.getInt("missLAL"));
                status.setMissUCL(rs.getInt("missUCL"));
                status.setMissCDR(rs.getInt("missCDR"));
                status.setMissUSC(rs.getInt("missUSC"));
                status.setMedicalDescription(rs.getString("medicalDescription"));
                status.setImagePath(rs.getString("imagePath"));

               playerStatusList.add(status);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public MatchPreview getMatchPreview() {
        return this.matchPreview;
    }
    public void setMatchPreview(LocalDate date) {

        String sql = "SELECT * FROM match_statistics WHERE match_date = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("No data found for match date: " + date);
                return;
            }

            if (rs.next()) {
                matchPreview.setMyScore(rs.getInt("my_score"));
                matchPreview.setOpponentScore(rs.getInt("opponent_score"));
                matchPreview.setOpponentTeamName(rs.getString("opponent_club"));
                matchPreview.setMyTotalShots(rs.getInt("my_total_shots"));
                matchPreview.setOpponentTotalShots(rs.getInt("opponent_total_shots"));
                matchPreview.setMyShotsOnTarget(rs.getInt("my_shots_on_target"));
                matchPreview.setOpponentShotsOnTarget(rs.getInt("opponent_shots_on_target"));
                matchPreview.setMyPossession(rs.getDouble("my_possession"));
                matchPreview.setOpponentPossession(rs.getDouble("opponent_possession"));
                matchPreview.setMyPasses(rs.getInt("my_passes"));
                matchPreview.setOpponentPasses(rs.getInt("opponent_passes"));
                matchPreview.setMyPassAccuracy(rs.getDouble("my_pass_accuracy"));
                matchPreview.setOpponentPassAccuracy(rs.getDouble("opponent_pass_accuracy"));
                matchPreview.setMyFouls(rs.getInt("my_fouls"));
                matchPreview.setOpponentFouls(rs.getInt("opponent_fouls"));
                matchPreview.setMyYellowCards(rs.getInt("my_yellow_cards"));
                matchPreview.setOpponentYellowCards(rs.getInt("opponent_yellow_cards"));
                matchPreview.setMyRedCards(rs.getInt("my_red_cards"));
                matchPreview.setOpponentRedCards(rs.getInt("opponent_red_cards"));
                matchPreview.setMyOffsides(rs.getInt("my_offsides"));
                matchPreview.setOpponentOffsides(rs.getInt("opponent_offsides"));
                matchPreview.setMyCorners(rs.getInt("my_corners"));
                matchPreview.setOpponentCorners(rs.getInt("opponent_corners"));
                System.out.println("✅ Data loaded for match date: " + date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Database error");
        }
    }


    // admin section



    public boolean getAdminLoginSuccessFlag() {
        return this.adminLoginSuccessFlag;
    }
    public void setAdminLoginSuccessFlag(boolean flag) {
        this.adminLoginSuccessFlag = flag;
    }




}
