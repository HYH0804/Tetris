module com.example.fxtest {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.prefs;

    opens com.example.fxtest to javafx.fxml;
    exports com.example.fxtest;
    exports com.example.fxtest.brick;
    opens com.example.fxtest.brick to javafx.fxml;
}