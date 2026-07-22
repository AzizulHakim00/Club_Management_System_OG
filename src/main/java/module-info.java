module com.example.realmadrid {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires com.google.gson;
    requires java.sql;
    requires org.apache.poi.ooxml;
    requires com.calendarfx.view;
    requires java.desktop;
    requires itextpdf;


    // Opens for FXML access via reflection
    opens com.example.realmadrid to javafx.fxml;
    opens com.example.realmadrid.Controller to javafx.fxml;
    opens com.example.realmadrid.Controller.Couch to javafx.fxml;
    opens com.example.realmadrid.Controller.Couch.TransferAllPopUp to javafx.fxml;
    opens com.example.realmadrid.Controller.TeamStats to javafx.fxml;
    opens com.example.realmadrid.Controller.Admin to javafx.fxml;
    opens com.example.realmadrid.Controller.Match to javafx.fxml;
    opens com.example.realmadrid.Model to javafx.fxml;
    opens com.example.realmadrid.Model.Player to javafx.fxml;
    opens com.example.realmadrid.Views to javafx.fxml;



    // Exports for general use
    exports com.example.realmadrid;
    exports com.example.realmadrid.Controller;
    exports com.example.realmadrid.Controller.Couch;
    exports com.example.realmadrid.Controller.Couch.TransferAllPopUp;
    exports com.example.realmadrid.Controller.TeamStats;
    exports com.example.realmadrid.Controller.Admin;
    exports com.example.realmadrid.Controller.Match;
    exports com.example.realmadrid.Model;
    exports com.example.realmadrid.Model.Player;
    exports com.example.realmadrid.Views;
    exports  com.example.realmadrid.Model.PointsTable;
}
