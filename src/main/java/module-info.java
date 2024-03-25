module com.example.fxtest {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;

    opens com.example.fxtest to javafx.fxml;
    exports com.example.fxtest;
    exports com.example.fxtest.brick;
    opens com.example.fxtest.brick to javafx.fxml;
}