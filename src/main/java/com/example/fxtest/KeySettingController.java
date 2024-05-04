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
    private AnchorPane keySettingPage;
    @FXML
    private VBox keySettingVbox;

    private final static int LABELNUM = 5;
    private Map<Label,String> keyMap = new HashMap<>();
    private static Boolean[] buttonClicked = {false,false,false,false,false};
    private Label[] labelSet = new Label[LABELNUM];

    private final String[] buttonName = {"rotate", "moveLeft", "moveRight", "moveDown", "hardDrop"};
    private static Properties settingProperties;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Node> children = keySettingVbox.getChildren();
        int idx = 0;
        for(Node child : children){
            if (child instanceof Label) {
                Label label = (Label) child;
                labelSet[idx] = label;
                idx++;
            }
        }

        getKey(); // 기존 값을 list로 가져오기 및 표시하기

        // 핸들러
        for(int i = 0; i < LABELNUM; i++) {
            initHandler(labelSet[i],i);
        }
        for (Label label : labelSet) {
            label.setFocusTraversable(true);
        }
        keySettingPage.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                focusSelectedLabel();
                event.consume(); // 기본 엔터 이벤트를 처리하지 않도록 consume()
            }
        });
    }

    // 포커스를 다음 라벨로 이동하는 메서드
    private void focusNextLabel() {
        int currentFocusedIndex = -1;  // 현재 포커스된 라벨의 인덱스를 추적

        // 현재 포커스된 라벨을 찾아 다음 라벨로 포커스 이동
        for (int i = 0; i < LABELNUM; i++) {
            if (labelSet[i].isFocused()) {
                currentFocusedIndex = i;
                int nextIndex = (i + 1) % LABELNUM; // 다음 라벨의 인덱스 계산
                labelSet[nextIndex].requestFocus(); // 다음 라벨로 포커스 이동
                break;
            }
        }

        // 만약 포커스를 설정하지 못했다면 모든 라벨이 포커스를 잃은 상태이므로 첫 번째 라벨에 포커스를 설정합니다.
        if (currentFocusedIndex == -1) {
            labelSet[0].requestFocus();
        }

        // 포커스된 라벨을 시각적으로 강조합니다.
        highlightFocusedLabel();
    }
    private boolean isAnyButtonClicked() {
        for (boolean clicked : buttonClicked) {
            if (clicked) return true;
        }
        return false;
    }

    private void highlightFocusedLabel() {
        for (int i = 0; i < LABELNUM; i++) {
            if (labelSet[i].isFocused()) {
                // 포커스된 라벨에 파란색 테두리를 적용
                labelSet[i].setStyle("-fx-border-color: BLUE; -fx-border-width: 2px;");
            } else {
                // 포커스 되지 않은 라벨은 기본 테두리 색상을 적용
                labelSet[i].setStyle("-fx-border-color: BLACK; -fx-border-width: 2px;");
            }
        }
    }
    private void focusSelectedLabel() {
        for (int i = 0; i < LABELNUM; i++) {
            if (buttonClicked[i]) {
                labelSet[i].requestFocus();
            }
        }
    }

    public void initPageHandler(Scene pScene, Parent root){
        VBox vbox = (VBox) root.lookup("#keySettingVbox");
        if (vbox != null) {
            ObservableList<Node> children = vbox.getChildren();
            int idx = 0;
            for (Node child : children) {
                if (child instanceof Label) { // 정확한 타입 확인
                    Label label = (Label) child;
                    labelSet[idx] = label;
                    idx++;
                }
            }
        }

        // key event for scene
        pScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            KeyCode keyCode = event.getCode();

            if (keyCode == KeyCode.BACK_SPACE) {
                event.consume(); // 이벤트 처리를 여기서 중단
                return; // 아무런 작업도 수행하지 않음
            }
            if(!isAnyButtonClicked()) {
                if (event.getCode() == KeyCode.TAB) {
                    focusNextLabel();
                    event.consume(); // 기본 탭 이벤트를 처리하지 않도록 consume()
                }else if(event.getCode() == KeyCode.DOWN||event.getCode() == KeyCode.UP||event.getCode() == KeyCode.LEFT||event.getCode() == KeyCode.RIGHT){
                    event.consume();
                }
            }


            int change=-1; // 바뀔 키의 위치
            for(int i = 0; i < LABELNUM; i++) {
                System.out.println((buttonClicked[i]));
                if(buttonClicked[i]){
                    change = i;
                }
            }

            if(change == -1) { // not selected
                if(keyCode == KeyCode.ESCAPE){ // esc
                    // properties
                    boolean update = true; // if same key is used then do not update
                    for(Label label: labelSet){ // detecting same key
                        if(label.getTextFill() == Color.RED) {
                            update = false;
                        }
                    }
                    if (update){
                        for(int i = 0; i < LABELNUM; i++){
                            keyMap.put(labelSet[i],labelSet[i].getText());
                        }
                        keyMap.forEach((key,value)->{
                            settingProperties.setProperty(key.getId(),value);
                        });

                        FileOutputStream fos = null;
                        try {
                            fos = new FileOutputStream("src/main/resources/setting.properties");

                            settingProperties.store(fos,null);
                            System.out.println("propeties update done");
                            BrickController.getBrickController().updateBrickController();
                        } catch (IOException e) {}
                        finally {
                            try {
                                fos.close();
                            } catch (IOException e) {}
                        }
                    }

                    // go back to setting page
                    Properties properties = null;
                    try {
                        properties = Main.loadProperties();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Stage stage = (Stage) pScene.getWindow(); // 현재 창(Stage) 가져오기
                    stage.close(); // 창 닫기
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

    }

    private void initHandler(Label label, int idx){
        label.setOnMouseClicked(event ->{
            if (!buttonClicked[idx]) {
                for (int i = 0; i < LABELNUM; i++) {
                    if (i == idx) continue;
                    buttonClicked[i] = false;
                }
                buttonDrawing();
            }
        });
        label.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (!buttonClicked[idx]) {
                    for (int i = 0; i < LABELNUM; i++) {
                        if (i == idx) continue;
                        buttonClicked[i] = false;
                    }
                    buttonClicked[idx] = true;
                    buttonDrawing();
                }
                focusSelectedLabel();
            }
        });
    }

    private void getKey() {
        // Properties 객체 생성
        settingProperties = new Properties();
        try {
            // setting.properties 파일 로드
            FileInputStream in = new FileInputStream("src/main/resources/setting.properties");
            settingProperties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 기존 properties 값 받아오기
        for(int i = 0; i < LABELNUM; i++){
            labelSet[i].setText(settingProperties.getProperty(buttonName[i]));
            System.out.println(buttonName[i]+ ": " + labelSet[i].getText());
        }
    }

    private void buttonDrawing()
    {
        for (int i = 0; i < LABELNUM; i++) {
            String style;
            if (buttonClicked[i]) {
                style = "-fx-border-color: RED; -fx-border-width: 2px;";
            } else {
                style = "-fx-border-color: BLACK; -fx-border-width: 2px;";
            }
            labelSet[i].setStyle(style);
        }
    }

    private void colorSame() {
        for(Label label : labelSet){
            label.setTextFill(Color.BLACK);
        }
        for(int i = 0; i < LABELNUM; i++) {
            for(int j = 0; j < LABELNUM; j++) {
                if(j<=i) continue;
                if(labelSet[i].getText().equals(labelSet[j].getText())){
                    labelSet[i].setTextFill(Color.RED);
                    labelSet[j].setTextFill(Color.RED);
                }
            }
        }
    }

}
