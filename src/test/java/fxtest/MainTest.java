/*
package com.example.fxtest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class MainTest extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Main 클래스의 start 메서드 호출
        Main main = new Main();
        main.start(primaryStage);
    }

    @Test
    public void testStageTitle() {
        Stage primaryStage = StageSaver.pStage;
        assertEquals("Start Page", primaryStage.getTitle(), "Stage title should be 'Start Page'");
    }

    @Test
    public void testSceneLoaded() {
        verifyThat("#root", isVisible()); // root가 FXML 파일의 루트 요소의 fx:id라고 가정합니다.
    }

    @Test
    public void testStageDimensions() {
        Stage primaryStage = StageSaver.pStage;
        assertEquals(SettingModel.getWidth(), primaryStage.getWidth(), "Stage width should match the setting model");
        assertEquals(SettingModel.getHeight(), primaryStage.getHeight(), "Stage height should match the setting model");
    }
}*/
