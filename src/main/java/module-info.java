module com.example.cab302 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.scripting;
    requires com.sun.jna;
    requires com.sun.jna.platform;
    requires java.desktop;
    requires applescript;


    opens com.example.cab302 to javafx.fxml;
    exports com.example.cab302;
    exports com.example.cab302.controller;
    exports com.example.cab302.dbmodelling;
    opens com.example.cab302.controller to javafx.fxml;
}