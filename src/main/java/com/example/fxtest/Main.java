package com.example.fxtest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.prefs.Preferences;

public class Main extends Application {
    private static final String PROPERTIES_FILE = "src/main/resources/resolution.properties";

    @Override
    public void start(Stage primaryStage) throws IOException {
        // 프로퍼티 파일에서 해상도 값을 가져옴
        Properties properties = loadProperties();
        StageSaver.setStage(primaryStage);
        String resolution = properties.getProperty("resolution", "800x600");
        String[] dimensions = resolution.split("x");
        double width = Double.parseDouble(dimensions[0]);
        double height = Double.parseDouble(dimensions[1]);

        // 세팅 페이지 로드
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Start.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, width, height);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Start Page");
        primaryStage.setWidth(width); // 현재 Stage의 너비를 설정합니다.
        primaryStage.setHeight(height); // 현재 Stage의 높이를 설정합니다.
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    //properties에서 값가져오는 함수
    public static Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        try (FileInputStream in = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(in);
        }
        return properties;
    }
    public static void main(String[] args) {
        launch(args);
    }
}

