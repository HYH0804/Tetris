package com.example.fxtest;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.Key;
import java.util.*;

public class KeySettingController implements Initializable {
    @FXML
    private VBox keySettingVBox;


    private Map<Label,String> keyMap = new HashMap<>();
    private static Boolean[] buttonClicked = {false,false,false,false,false};
    private Label[] labelSet = new Label[5];
    private final String[] buttonName = {"rotate", "moveLeft", "moveRight", "moveDown", "hardDrop"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Node> children = keySettingVBox.getChildren();
        int idx = 0;
        for(Node child : children){
            Label label = (Label) child;
            labelSet[idx] = label;
            idx++;
            System.out.println(child);
        }

        getKey(); //기존 키 startKey list로 가져오기 및 값 표시
        // 핸들러
        for(int i = 0; i < 5; i++) {
            initHandler(labelSet[i],i);
        }

        //initPageHandler();
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
            labelSet[0].setText(rotateV);
            labelSet[1].setText(moveLeftV);
            labelSet[2].setText(moveRightV);
            labelSet[3].setText(moveDownV);
            labelSet[4].setText(hardDropV);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setKeyMap(){
        for(int i = 0; i < 5; i++){
            keyMap.put(labelSet[i],labelSet[i].getText());
        }
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
            labelSet[i].setStyle(style);
        }
    }

    public void initPageHandler(Scene pScene, Parent root) {
        AnchorPane anchorPane = (AnchorPane) root;
        Node A = anchorPane.getChildren().get(0);
        VBox prevVBox = (VBox)A;
        ObservableList<Node> children = prevVBox.getChildren();
        int idx = 0;
        for(Node child : children){
            Label label = (Label) child;
            labelSet[idx] = label;
            idx++;
            System.out.println(child);
        }

        // key event
        pScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            System.out.println("!@#");
            KeyCode keyCode = event.getCode();
            int change=-1;
            for(int i = 0; i < 5; i++) {
                    System.out.println(buttonClicked[i]);
                if(buttonClicked[i]){
                    change = i;
                }
            }
            if(change == -1) {
                if(keyCode == KeyCode.ESCAPE){
                    // properties
                    boolean update = true;
                    for(Label label: labelSet){
                        if(label.getTextFill() == Color.RED) {
                            update = false;
                        }
                    }
                    if (update){
                        setKeyMap();
                        Properties prop = new Properties();
                        keyMap.forEach((key,value)->{
                            prop.setProperty(key.getId(),value);
                        });

                        FileOutputStream fos = null;
                        try {
                            fos = new FileOutputStream("src/main/resources/setting.properties");

                            prop.store(fos,null);
                            System.out.println("propeties update done");

                        } catch (IOException e) {}
                        finally {
                            try {
                                fos.close();
                            } catch (IOException e) {}
                        }
                    }

                    // go back to setting page
                    FXMLLoader loader = new FXMLLoader(test.class.getResource("setting-view.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(loader.load());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Stage stage = (Stage) root.getScene().getWindow();
                    stage.setScene(scene);
                }
                return;
            }

            if(keyCode == KeyCode.UNDEFINED || keyCode == KeyCode.WINDOWS){
                return;
            }
            else if(!(keyCode == KeyCode.ESCAPE)) {
                labelSet[change].setText(keyCode.toString());
            }

            buttonClicked[change] = false;
            buttonDrawing();
            colorSame();
        });

        // mouse control??

    }

    private void colorSame() {
        for(Label label : labelSet){
            label.setTextFill(Color.BLACK);
        }
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(j<=i) continue;
                if(labelSet[i].getText().equals(labelSet[j].getText())){
                    labelSet[i].setTextFill(Color.RED);
                    labelSet[j].setTextFill(Color.RED);
                }
            }
        }
    }
}
