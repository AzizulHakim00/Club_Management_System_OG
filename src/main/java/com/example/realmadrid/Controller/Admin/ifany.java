package com.example.realmadrid.Controller.Admin;

public interface ifany {
     /*
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println(tournament);



        touches_btn.setDefaultButton(true);
        for_shots_vbox.setVisible(false);
        againts_vBox.setVisible(false);

        bindLabels();

        ObservableList<String> tournament_options = FXCollections.observableArrayList("SSL", "UCL");
        tournament_choicebox.setItems(tournament_options);

        tournament_choicebox.setValue(Model.getInstance().getTournamentOption().toString());

        try {
             setPlan();
            setStatistics();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        playStyle_list.setItems(playStyle);
        playStyle_list.setCellFactory(e -> new StrengtsCellFactory());
        Model.getInstance().getPlayStyle(club, playStyle);

        // choice box weakness or strength

        streght_list.setItems(strengthWeaknessList);
        streght_list.setCellFactory(e -> new StrengtsCellFactory());

        onselctor();

        ObservableList<String> selector = FXCollections.observableArrayList("Strength", "weakness");
        choiceBox.setItems(selector);
        choiceBox.setValue("Strength");
        choiceBox.valueProperty().addListener((obs, oldVal, newVal) -> onselctor());

        tournament_choicebox.valueProperty().addListener((obs, oldVal, newVal) -> {
            try {
                setPlan();
                setStatistics();
                playStyle.clear();
                Model.getInstance().getPlayStyle(club, playStyle);
                onselctor();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });


    }

     */
}
