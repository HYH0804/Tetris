package com.example.fxtest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

@ExtendWith(ApplicationExtension.class)
public class StartPageTest {
    @Start
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartPageKeyboardTest.class.getResource("Start.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 400, 400);;
        stage.setScene(scene);
        stage.show();
    }
    @Test
    public void startButtonTest(FxRobot robot) {
        robot.sleep(100);

        robot.clickOn("#startButton");
        robot.sleep(100);
        verifyThat(".button", hasText("아이템 모드 실행"));
    }
    @Test
    public void twoplayerButtonTest(FxRobot robot) {
        robot.sleep(100);

        robot.clickOn("#twoplayerButton");
        robot.sleep(100);
        verifyThat(".button", hasText("아이템 모드 실행"));
    }
    @Test
    public void settingsButtonTest(FxRobot robot) {
        robot.sleep(100);

        robot.clickOn("#settingsButton");
        robot.sleep(100);
        verifyThat(".label", hasText("화면 크기 설정"));
    }
    @Test
    public void scoreboardButtonTest(FxRobot robot) {
        robot.sleep(100);

        robot.clickOn("#scoreboardButton");
        robot.sleep(100);
        verifyThat("#GoHomeButton", hasText("Go Home"));
    }
}
