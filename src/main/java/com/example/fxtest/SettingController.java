package com.example.fxtest;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.prefs.Preferences;

import static com.example.fxtest.Main.loadProperties;

public class SettingController implements Initializable {
//확인 버튼 누르면 Scene 다시 띄우는걸로


    @FXML
    private Button confirmButton;
    @FXML
    private Button backButton;
    @FXML
    private RadioButton smallSize;
    @FXML
    private RadioButton mediumSize;
    @FXML
    private RadioButton largeSize;
    @FXML
    private Button setKeyButton;


    private ToggleGroup sizeToggleGroup = new ToggleGroup();

    private final Preferences preferences = Preferences.userNodeForPackage(SettingController.class);

    //각각 TextField에 이벤트 달아서 해당 텍스트를 가져와서 properties 파일에 저장
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //List로 한번에 처리 고려
        smallSize.setToggleGroup(sizeToggleGroup);
        mediumSize.setToggleGroup(sizeToggleGroup);
        largeSize.setToggleGroup(sizeToggleGroup);
    }

    @FXML
    private void onSetKeyButtonClick() {
            Stage stage = (Stage) setKeyButton.getScene().getWindow();
            try {
                stage.setScene(KeySettingController.KeySettingScene());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.show();
    }

    @FXML
    public void onConfirmButtonClick() throws IOException {
        System.out.println("Button Method Execute");
        applyResolution(); //해상도 변경
    }

    @FXML
    private void onBackButtonClick() throws IOException {
        // 현재 스테이지를 가져옵니다.
        Stage stage = (Stage) backButton.getScene().getWindow();

        Properties properties = loadProperties();
        String resolution = properties.getProperty("resolution", "800x600");
        String[] dimensions = resolution.split("x");
        double width = Double.parseDouble(dimensions[0]);
        double height = Double.parseDouble(dimensions[1]);

        // 세팅 페이지 로드
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Start.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    private final Properties properties = new Properties();
    private final String propertiesFileName = "src/main/resources/resolution.properties";

    //해상도 판단 후 적용하는 함수
    @FXML
    private void applyResolution() {
        String resolution = "";
        if (smallSize.isSelected()) {
            resolution = "800x600";
        } else if (mediumSize.isSelected()) {
            resolution = "1024x768";
        } else if (largeSize.isSelected()) {
            resolution = "1280x1024";
        }

        saveResolution(resolution); //프로퍼티에 저장
        changeResolution(resolution); // 선택된 해상도로 변경
    }

    //프로퍼티에 저장
    private void saveResolution(String resolution) {
        properties.setProperty("resolution", resolution);
        try (FileOutputStream out = new FileOutputStream(propertiesFileName)) {
            properties.store(out, "Application Configuration");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //해상도 바꿔주는 함수
    private void changeResolution(String resolution) {
        Stage stage = (Stage)smallSize.getScene().getWindow();

        String[] dimensions = resolution.split("x");
        double width = Double.parseDouble(dimensions[0]);
        double height = Double.parseDouble(dimensions[1]);

        stage.setWidth(width);
        stage.setHeight(height);
    }
}
