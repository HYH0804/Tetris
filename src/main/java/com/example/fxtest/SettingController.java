package com.example.fxtest;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SettingController implements Initializable {
//확인 버튼 누르면 Scene 다시 띄우는걸로


    @FXML
    private Button confirmButton;

    @FXML
    private Label moveDown;
    @FXML
    private Label moveRight;
    @FXML
    private Label moveLeft;
    @FXML
    private Label rotate;
    //@FXML
    //private Label hardDrop;

    private List<String> startKey = new ArrayList<>();
    private Map<Label,String> keyMap = new HashMap<>();

    @FXML
    public void onConfirmButtonClick() throws IOException {
        System.out.println("버튼 메서드 실행");
        setKeyMap();
        updateProperty(keyMap); // Properties 파일 업데이트
    }

    //각각 TextField에 이벤트 달아서 해당 텍스트를 가져와서 properties 파일에 저장
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getKey(); //기존 키 startKey list로 가져오기

        moveDown.setText(startKey.get(0));
        moveRight.setText(startKey.get(1));
        moveLeft.setText(startKey.get(2));
        rotate.setText(startKey.get(3));
        //hardDrop.setText(startKey.get(4));
        
        //List로 한번에 처리 고려
        initHandler(moveDown);
        initHandler(moveRight);
        initHandler(moveLeft);
        initHandler(rotate);
        //initHandler(hardDrop);
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
            String moveDownV = prop.getProperty("moveDown");
            String moveLeftV = prop.getProperty("moveLeft");
            String moveRightV = prop.getProperty("moveRight");
            String rotateV = prop.getProperty("rotate");
            //String hardDropV = prop.getProperty("hardDrop");

            // 읽어온 값 출력
            System.out.println("moveDown: " + moveDownV);
            System.out.println("moveLeft: " + moveLeftV);
            System.out.println("moveRight: " + moveRightV);
            System.out.println("rotate: " + rotateV);
            //System.out.println("hardDrop: " + hardDropV);

            startKey.add(moveDownV);
            startKey.add(moveLeftV);
            startKey.add(moveRightV);
            startKey.add(rotateV);
            //startKey.add(hardDropV);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setKeyMap(){
        keyMap.put(moveDown,moveDown.getText());
        keyMap.put(moveLeft,moveLeft.getText());
        keyMap.put(moveRight,moveRight.getText());
        keyMap.put(rotate,rotate.getText());
        //keyMap.put(hardDrop,hardDrop.getText());
    }

    private void initHandler(Label label){
        // TextField에 키 이벤트 핸들러 추가
        label.setOnKeyPressed(event ->{
            KeyCode keyCode = event.getCode();
            // when used, can occur some problems
            if(keyCode == KeyCode.WINDOWS || keyCode == KeyCode.ESCAPE) {
                return;
            }
            label.setText(keyCode.toString());
        });
    }

    private void updateProperty(Map<Label,String> keyMap) {
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
