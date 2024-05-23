package com.example.fxtest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isFocused;
import static org.testfx.util.NodeQueryUtils.hasText;

@ExtendWith(ApplicationExtension.class)
public class StartPageKeyboardTest {
    private Button twoplayerButton;
    private Button startButton;
    @Start
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartPageKeyboardTest.class.getResource("Start.fxml"));
        Parent root = fxmlLoader.load();
        twoplayerButton = (Button) root.lookup("#twoplayerButton");
        startButton = (Button) root.lookup("#startButton");

        Scene scene = new Scene(root, 400, 400);;
        stage.setScene(scene);
        stage.show();
    }
    @Test
    public void MovingMenu(FxRobot robot) {
        //robot.clickOn("#settingsButton");
        robot.sleep(1000);

        robot.push(KeyCode.DOWN);
        robot.sleep(1000);
        verifyThat(twoplayerButton, isFocused());

        robot.push(KeyCode.LEFT);
        robot.sleep(1000);
        verifyThat("#infoLabel", hasText("Available keys: Up, Down, Enter"));

        robot.push(KeyCode.UP);
        robot.sleep(1000);
        verifyThat(startButton, isFocused());
    }
    @Test
    public void Enter(FxRobot robot) {
        robot.sleep(1000);

        robot.push(KeyCode.ENTER);
        robot.sleep(1000);
        verifyThat(".button", hasText("아이템 모드 실행"));
    }

}