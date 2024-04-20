module com.example.cab302 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.scripting;
    requires com.sun.jna;
    requires com.sun.jna.platform;
    requires java.desktop;
    requires applescript;


    opens com.example.cab302 to javafx.fxml;
    exports com.example.cab302;
}