package com.example.fxtest;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class KeySettingController implements Initializable {
    @FXML
    private Label rotate;
    @FXML
    private Label moveLeft;
    @FXML
    private Label moveRight;
    @FXML
    private Label moveDown;
    @FXML
    private Label hardDrop;

    private Map<Label,String> keyMap = new HashMap<>();
    private Boolean[] buttonClicked = {false,false,false,false,false};
    private final String[] buttonName = {"rotate", "moveLeft", "moveRight", "moveDown", "hardDrop"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getKey(); //기존 키 startKey list로 가져오기 및 값 표시
        // 핸들러
        initHandler(rotate,0);
        initHandler(moveLeft,1);
        initHandler(moveRight,2);
        initHandler(moveDown,3);
        initHandler(hardDrop,4);
    }

    private void getKey() {
        // Properties 객체 생성
        Properties prop = new Properties();
        try {
            // setting.properties 파일 로드
            FileInputStream in = new FileInputStream("src/main/resources/setting.properties");
            prop.load(in);
            in.close();

            // 각 키에 해당하는 값 읽어오기
            String rotateV = prop.getProperty("rotate");
            String moveLeftV = prop.getProperty("moveLeft");
            String moveRightV = prop.getProperty("moveRight");
            String moveDownV = prop.getProperty("moveDown");
            String hardDropV = prop.getProperty("hardDrop");

            // 읽어온 값 출력
            System.out.println("rotate: " + rotateV);
            System.out.println("moveLeft: " + moveLeftV);
            System.out.println("moveRight: " + moveRightV);
            System.out.println("moveDown: " + moveDownV);
            System.out.println("hardDrop: " + hardDropV);

            // startKey에 값 입력
            rotate.setText(rotateV);
            moveLeft.setText(moveLeftV);
            moveRight.setText(moveRightV);
            moveDown.setText(moveDownV);
            hardDrop.setText(hardDropV);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setKeyMap(){
        keyMap.put(moveDown,moveDown.getText());
        keyMap.put(moveLeft,moveLeft.getText());
        keyMap.put(moveRight,moveRight.getText());
        keyMap.put(rotate,rotate.getText());
        keyMap.put(hardDrop,hardDrop.getText());
    }

    public void initHandler(Label label, int idx){
        label.setOnMouseClicked(event ->{
            for(int i = 0; i < 5; i++) {
                if(i == idx) continue;
                buttonClicked[i] = false;
            }
            buttonClicked[idx] = !buttonClicked[idx];
            buttonDrawing();
        });
    }
    private void buttonDrawing()
    {
        for (int i = 0; i < 5; i++) {
            String style;
            if (buttonClicked[i]) {
                style = "-fx-border-color: red; -fx-border-width: 2px;";
            } else {
                style = "-fx-border-color: black; -fx-border-width: 2px;";
            }
            switch (i) {
                case 0:
                    rotate.setStyle(style);
                    break;
                case 1:
                    moveLeft.setStyle(style);
                    break;
                case 2:
                    moveRight.setStyle(style);
                    break;
                case 3:
                    moveDown.setStyle(style);
                    break;
                case 4:
                    hardDrop.setStyle(style);
                    break;
                default:
                    break;
            }

        }
    }
}
