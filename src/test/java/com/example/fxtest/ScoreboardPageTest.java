package com.example.fxtest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
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
public class ScoreboardPageTest {
    private Label selected;


    @Start
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartPageKeyboardTest.class.getResource("scoreboard-view.fxml"));
        Parent root = fxmlLoader.load();
        selected = (Label) root.lookup("#selected");
        Scene scene = new Scene(root, 400, 400);;
        stage.setScene(scene);
        stage.show();
    }
    @Test
    public void difficultyItemButtonTest(FxRobot robot) {
        robot.sleep(100);

        robot.clickOn("#easyBtn");
        robot.sleep(100);
        verifyThat(selected, hasText("easy"));

        robot.clickOn("#normalBtn");
        robot.sleep(100);
        verifyThat(selected, hasText("normal"));

        robot.clickOn("#hardBtn");
        robot.sleep(100);
        verifyThat(selected, hasText("hard"));

        robot.clickOn("#itemModeBtn");
        robot.sleep(100);
        verifyThat(selected, hasText("hard(item)"));

    }
    @Test
    public void goHomeButtonTest(FxRobot robot) {
        robot.sleep(100);

        robot.clickOn("#GoHomeButton");
        robot.sleep(100);
        verifyThat("#startButton", hasText("Start Game"));
    }

}
