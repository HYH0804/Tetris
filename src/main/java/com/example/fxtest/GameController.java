package com.example.fxtest;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {


    @FXML
    public void onStartButtonClick() throws IOException{
        Stage st = StageSaver.pStage;
        FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("Start.fxml"));
        Scene mainpage = new Scene(fxmlLoader.load(), 320, 240);
        st.setScene(mainpage);
        st.show();
    }

/*    //모달 다이얼로그로 구현?
    @FXML
    public void onScoreboardButtonClick() throws IOException{
        Stage st = StageSaver.pStage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(*//*스코어보드페이지.fxml*//*));
        Scene mainpage = new Scene(fxmlLoader.load(), 320, 240);
        st.setScene(mainpage);
        st.show();
    }*/
}
