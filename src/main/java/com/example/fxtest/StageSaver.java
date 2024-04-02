package com.example.fxtest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


//쓸모 있는지 없는지 모르겠네
public class StageSaver {
    public static Stage pStage; //null 값 저장
    public static void setStage(Stage stage) throws IOException {
        pStage = stage;
    }
}
