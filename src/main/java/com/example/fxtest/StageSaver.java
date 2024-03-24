package com.example.fxtest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StageSaver {
    public static Stage pStage;
    public static void setStage(Stage stage) throws IOException {
        pStage = stage;
    }
}
