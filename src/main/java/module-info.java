module com.example.cab302 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.cab302 to javafx.fxml;
    exports com.example.cab302;
    exports com.example.cab302.controller;
    opens com.example.cab302.controller to javafx.fxml;
}