package com.example.fxtest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
public class GameBoard2PageTest {
    @Start
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartPageKeyboardTest.class.getResource("GameBoard2.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 400, 400);;
        stage.setScene(scene);
        stage.show();
    }
    @Test
    public void loadPageTest(FxRobot robot) {
        robot.sleep(1000);
    }
}
