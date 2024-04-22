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

    private final static int LABELNUM = 5;
    private Map<Label,String> keyMap = new HashMap<>();
    private static Boolean[] buttonClicked = {false,false,false,false,false};
    private Label[] labelSet = new Label[LABELNUM];
    private final String[] buttonName = {"rotate", "moveLeft", "moveRight", "moveDown", "straight"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Node> children = keySettingPage.getChildren();
        int idx = 0;
        for(Node child : children){
            Label label = (Label) child;
            labelSet[idx] = label;
            idx++;
        }

        getKey(); // 기존 값을 list로 가져오기 및 표시하기

        // 핸들러
        for(int i = 0; i < LABELNUM; i++) {
            initHandler(labelSet[i],i);
        }
    }

    public static Scene KeySettingScene() throws IOException {
        Properties properties = Main.loadProperties();
        String resolution = properties.getProperty("resolution", "800x600");
        String[] dimensions = resolution.split("x");
        double width = Double.parseDouble(dimensions[0]);
        double height = Double.parseDouble(dimensions[1]);

        FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("keysetting-view.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        KeySettingController key = new KeySettingController();
        Scene scene = new Scene(root, width, height);
        key.initPageHandler(scene, root);

        return scene;
    }

    public void initPageHandler(Scene pScene, Parent root){
        AnchorPane anchorPane = (AnchorPane) root;
        ObservableList<Node> children = anchorPane.getChildren();
        int idx = 0;
        for(Node child : children){
            Label label = (Label) child;
            labelSet[idx] = label;
            idx++;
        }
        // key event for scene
        pScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            KeyCode keyCode = event.getCode();

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
                    Properties properties = null;
                    try {
                        properties = Main.loadProperties();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    String resolution = properties.getProperty("resolution", "800x600");
                    String[] dimensions = resolution.split("x");
                    double width = Double.parseDouble(dimensions[0]);
                    double height = Double.parseDouble(dimensions[1]);

                    // 세팅 페이지 로드
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("setting-view.fxml"));;
                    Parent root1 = null;
                    try {
                        root1 = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Scene scene = new Scene(root1, width, height);

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

    }

    private void initHandler(Label label, int idx){
        label.setOnMouseClicked(event ->{
            for(int i = 0; i < LABELNUM; i++) {
                if(i == idx) continue;
                buttonClicked[i] = false;
            }
            buttonClicked[idx] = !buttonClicked[idx];
            buttonDrawing();
        });
    }

    private void getKey() {
        // Properties 객체 생성
        Properties prop = new Properties();
        try {
            // setting.properties 파일 로드
            FileInputStream in = new FileInputStream("src/main/resources/setting.properties");
            prop.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 기존 properties 값 받아오기
        for(int i = 0; i < LABELNUM; i++){
            labelSet[i].setText(prop.getProperty(buttonName[i]));
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
