package com.example.fxtest;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;


public class StartController implements Initializable {

    @FXML
    private Button startButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button scoreboardButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button twoplayerButton;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label infoLabel;
    private static final String PROPERTIES_FILE = "src/main/resources/resolution.properties";
    // 이 메서드는 FXML 파일 로딩이 완료된 후 자동으로 호출됩니다.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 버튼에 대한 키 이벤트 처리
        handleButtonKeyEvent(startButton);
        handleButtonKeyEvent(settingsButton);
        handleButtonKeyEvent(scoreboardButton);
        handleButtonKeyEvent(exitButton);
        handleButtonKeyEvent(twoplayerButton);
    }

    private void handleButtonKeyEvent(Button button) {
        // 버튼이 포커스를 받았을 때 키 이벤트를 처리합니다.
        button.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                // 사용 가능한 키: 위, 아래, Enter
                case UP:
                case DOWN:
                case ENTER:
                    break; // 아무 동작도 하지 않습니다.
                case LEFT: // 왼쪽 방향키 처리
                case RIGHT: // 오른쪽 방향키 처리
                case SPACE: // 스페이스바 처리
                    infoLabel.setText("Available keys: Up, Down, Enter");
                    event.consume(); // 기본 동작을 방지하기 위해 이벤트를 소비합니다.
                    break;
                default:
                    // 그 외의 키 입력 시에도 사용 가능한 키 안내 메시지를 표시합니다.
                    infoLabel.setText("Available keys: Up, Down, Enter");
                    event.consume(); // 기본 동작을 방지하기 위해 이벤트를 소비합니다.
                    break;
            }
        });
    }

    // 게임 시작 이벤트 핸들러
    @FXML
    private void startGame() throws IOException {
        Stage stage = (Stage) startButton.getScene().getWindow();
        SettingModel.init();

        int width = SettingModel.getWidth();
        int height = SettingModel.getHeight();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("difficulty.fxml"));
        Parent root = loader.load();
        Scene scene = startButton.getScene(); // 현재 Scene을 가져옵니다.
        scene.setRoot(root); // 현재 Scene의 root를 새로운 root로 설정합니다.
        stage.setTitle("Difficulty Page");
        stage.setWidth(width); // 현재 Stage의 너비를 설정합니다.
        stage.setHeight(height); // 현재 Stage의 높이를 설정합니다.
        stage.show();
    }

    // 설정 화면 열기 이벤트 핸들러
    @FXML
    private void openSettings() throws IOException {
        Stage stage = (Stage) settingsButton.getScene().getWindow();

        SettingModel.init();

        int width = SettingModel.getWidth();
        int height = SettingModel.getHeight();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("setting-view.fxml"));
        Parent root = loader.load();
        Scene scene = settingsButton.getScene(); // 현재 Scene을 가져옵니다.
        scene.setRoot(root); // 현재 Scene의 root를 새로운 root로 설정합니다.
        stage.setTitle("Settings Page");
        stage.setWidth(width); // 현재 Stage의 너비를 설정합니다.
        stage.setHeight(height); // 현재 Stage의 높이를 설정합니다.
        stage.show();
    }

    // 스코어보드 화면 열기 이벤트 핸들러
    @FXML
    private void openScoreboard() throws IOException{
        System.out.println("스코어보드 버튼이 클릭되었습니다. 스코어보드를 보여줍니다.");
        // 여기에 스코어보드 화면을 보여주는 로직 추가
        Stage stage = (Stage) scoreboardButton.getScene().getWindow();

        SettingModel.init();

        int width = SettingModel.getWidth();
        int height = SettingModel.getHeight();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("scoreboard-view.fxml"));
        Parent root = loader.load();
        Scene scene = scoreboardButton.getScene(); // 현재 Scene을 가져옵니다.
        scene.setRoot(root); // 현재 Scene의 root를 새로운 root로 설정합니다.
        stage.setTitle("Score Page");
        stage.setWidth(width); // 현재 Stage의 너비를 설정합니다.
        stage.setHeight(height); // 현재 Stage의 높이를 설정합니다.
        stage.show();
    }

    @FXML
    private void twoPlayer() throws IOException {
        Stage stage = (Stage) twoplayerButton.getScene().getWindow();
        SettingModel.init();

        int width = SettingModel.getWidth();
        int height = SettingModel.getHeight();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("difficulty2P.fxml"));
        Parent root = loader.load();
        Scene scene = twoplayerButton.getScene(); // 현재 Scene을 가져옵니다.
        scene.setRoot(root); // 현재 Scene의 root를 새로운 root로 설정합니다.
        stage.setTitle("2P Game");
        stage.setWidth(width); // 현재 Stage의 너비를 설정합니다.
        stage.setHeight(height); // 현재 Stage의 높이를 설정합니다.
        stage.show();
    }

    // 애플리케이션 종료 이벤트 핸들러

    @FXML
    private void exitGame() {
        Platform.exit();
    }
}
