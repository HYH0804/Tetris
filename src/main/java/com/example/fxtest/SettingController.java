package com.example.fxtest;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SettingController implements Initializable {
//확인 버튼 누르면 Scene 다시 띄우는걸로

    /*@FXML
    public void onConfirmButtonClick() throws IOException {
        System.out.println("버튼 메서드 실행");
        setKeyMap();
        updateProperty(keyMap); // Properties 파일 업데이트
    }*/


    //각각 TextField에 이벤트 달아서 해당 텍스트를 가져와서 properties 파일에 저장
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void initHandler(TextField textField){
        // TextField에 키 이벤트 핸들러 추가
        textField.setOnKeyPressed(event ->{
            KeyCode keyCode = event.getCode();
            // when used, can occur some problems
            if(keyCode == KeyCode.WINDOWS || keyCode == KeyCode.ESCAPE) {
                return;
            }
            textField.setText(keyCode.toString());
        });
    }

    private void updateProperty(Map<TextField,String> keyMap) {
        Properties prop = new Properties();
        keyMap.forEach((key,value)->{
            prop.setProperty(key.getId(),value);
        });

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("src/main/resources/setting.properties");

            prop.store(fos,null);
            System.out.println("property 업뎃 완료");

        } catch (IOException e) {}
        finally {
            try {
                fos.close();
            } catch (IOException e) {}
        }
    }

}
