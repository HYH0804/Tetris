package com.example.fxtest;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.StageStyle;


import java.io.*;
import java.net.URL;
import java.util.*;


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
    @FXML
    private Button toggleColorBlindModeButton;

    private ToggleGroup sizeToggleGroup = new ToggleGroup();

    //private final Preferences preferences = Preferences.userNodeForPackage(SettingController.class);

    //각각 TextField에 이벤트 달아서 해당 텍스트를 가져와서 properties 파일에 저장
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        smallSize.setToggleGroup(sizeToggleGroup);
        mediumSize.setToggleGroup(sizeToggleGroup);
        largeSize.setToggleGroup(sizeToggleGroup);
        changeColorBlindnessText();

        SettingModel.init();
    }

    @FXML
    private void onSetKeyButtonClick() throws IOException {
        // 현재 스테이지 창을 가져옵니다.
        Stage currentStage = (Stage) setKeyButton.getScene().getWindow();

        // FXML 로더를 사용하여 새로운 Scene을 로드합니다.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("keysetting-view.fxml"));
        Parent root = loader.load();

        // 새 Scene을 설정하고 새로운 Stage를 생성합니다.
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);

        // 모달 창 설정: 다른 창을 사용할 수 없도록 설정
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.initOwner(currentStage);
        // 창의 타이틀 바 제거
        newStage.initStyle(StageStyle.UNDECORATED);
        // 스테이지 제목 설정
        newStage.setTitle("Key Settings Page");
        double centerX = currentStage.getX() + currentStage.getWidth() / 2;
        double centerY = currentStage.getY() + currentStage.getHeight() / 2;
        double width = 400; // 임시 값, 필요에 따라 조정 가능
        double height = 550; // 임시 값, 필요에 따라 조정 가능
        newStage.setWidth(width);
        newStage.setHeight(height);
        newStage.setX(centerX - width / 2); // 새 스테이지를 현재 스테이지의 중앙에 위치시키기
        newStage.setY(centerY - height / 2);


        // 키 설정 컨트롤러 가져오기 및 핸들러 초기화
        KeySettingController keyController = loader.getController();
        keyController.initPageHandler(newScene, root);

        // 새로운 스테이지를 표시합니다.
        newStage.show();
    }

    @FXML
    public void onToggleColorBlindModeButton() throws IOException {
        System.out.println("Color mode Changed");
        SettingModel.changeColorBlindness();
        changeColorBlindnessText();
    }

    @FXML
    public void onResetSettingsButton(){
        System.out.println("Reset Properties Execute");
        SettingModel.resetSetting(); // reset properties

        changeResolution("800x600");
        changeColorBlindnessText();
    }

    @FXML
    public void onResetScoreBButton(){
        String[] difficulty = {"easy", "normal", "hard"};
        for(String difficult : difficulty){
            String path = System.getProperty("user.home") + File.separator + "score" + File.separator + difficult + ".txt";
            try {
                // FileWriter를 사용하여 파일 쓰기 스트림 열기
                FileWriter fileWriter = new FileWriter(path);

                // BufferedWriter를 사용하여 버퍼링을 추가하여 효율적으로 파일에 쓰기
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                // 배열의 각 줄을 파일에 쓰기
                for (int i = 0; i < 10; i++) {
                    bufferedWriter.write( " 0");
                    bufferedWriter.newLine(); // 개행 문자 추가
                }

                // 버퍼 비우고 스트림 닫기
                bufferedWriter.flush();
                bufferedWriter.close();

                System.out.println("파일에 여러 줄 쓰기 완료");

            } catch (IOException e) {
                e.printStackTrace();
            }
            path = System.getProperty("user.home") + File.separator + "score" + File.separator + difficult + "(item).txt";
            try {
                // FileWriter를 사용하여 파일 쓰기 스트림 열기
                FileWriter fileWriter = new FileWriter(path);

                // BufferedWriter를 사용하여 버퍼링을 추가하여 효율적으로 파일에 쓰기
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                // 배열의 각 줄을 파일에 쓰기
                for (int i = 0; i < 10; i++) {
                    bufferedWriter.write( " 0");
                    bufferedWriter.newLine(); // 개행 문자 추가
                }

                // 버퍼 비우고 스트림 닫기
                bufferedWriter.flush();
                bufferedWriter.close();

                System.out.println("파일에 여러 줄 쓰기 완료");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void onConfirmButtonClick() throws IOException {
        System.out.println("Button Method Execute");
        applyResolution(); //해상도 변경
        SettingModel.saveProp(); // 저장
    }

    @FXML
    private void onBackButtonClick() throws IOException {
        // 현재 스테이지를 가져옵니다.
        Stage stage = (Stage) backButton.getScene().getWindow();

        SettingModel.init();

        int width = SettingModel.getWidth();
        int height = SettingModel.getHeight();

        // 세팅 페이지 로드
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Start.fxml"));
        Parent root = loader.load();
        Scene scene = backButton.getScene(); // 현재 Scene을 가져옵니다.
        scene.setRoot(root); // 현재 Scene의 root를 새로운 root로 설정합니다.
        stage.setTitle("Start Page");
        stage.setWidth(width); // 현재 Stage의 너비를 설정합니다.
        stage.setHeight(height); // 현재 Stage의 높이를 설정합니다.
        stage.show();
    }

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

        // saveResolution(resolution); //프로퍼티에 저장
        changeResolution(resolution); // 선택된 해상도로 변경
    }

    //해상도 바꿔주는 함수
    private void changeResolution(String resolution) {
        Stage stage = (Stage)smallSize.getScene().getWindow();

        String[] dimensions = resolution.split("x");
        SettingModel.resolutionVal[0] = Integer.parseInt(dimensions[0]);
        SettingModel.resolutionVal[1] = Integer.parseInt(dimensions[1]);

        stage.setWidth(SettingModel.resolutionVal[0]);
        stage.setHeight(SettingModel.resolutionVal[1]);
    }

    // change ColorBlindness text
    private void changeColorBlindnessText() {
        if(SettingModel.colorBlindnessVal == 1){
            toggleColorBlindModeButton.setText("색맹모드: ON");
        }
        else{
            toggleColorBlindModeButton.setText("색맹모드: OFF");
        }
    }

}
