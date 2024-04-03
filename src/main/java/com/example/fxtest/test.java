package com.example.fxtest;

import javafx.application.Application;
//<<<<<<< HEAD
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
//=======
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
//>>>>>>> 5e5d8ff191f6362534413ee21d9f5f891ad6cd61
import javafx.stage.Stage;

import java.io.IOException;

/*UI 담당
 *
 * 이 파일은 첫 시작화면 Controller
 * */

public class test extends Application {

    @FXML
    public Label GameStart;

    //Game 진입 이벤트
    @FXML
    public void OepnGameBoard() throws IOException {
        GameStart.setText("TETRIS");
        Stage st = StageSaver.pStage; //null 값 st에 넣고
        FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("GameBoard.fxml"));
        Scene mainpage = new Scene(fxmlLoader.load(), 300, 440);
        st.setScene(mainpage); //Scene 바꿀려고
        st.show();

    }

    //어플리케이션 진입점
    @Override
    public void start(Stage stage) throws IOException {
        StageSaver.setStage(stage);

        FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("keysetting-view.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Scene scene = new Scene(root,800, 800);
        stage.setTitle("Tetris");
        KeySettingController key = new KeySettingController();
        key.initPageHandler(scene, root);
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}