package com.example.fxtest;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    public void onHelloButtonClick() throws IOException {
        welcomeText.setText("Welcome to JavaFX Application!");
        Stage st = StageSaver.pStage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gamepage-view.fxml"));
        Scene mainpage = new Scene(fxmlLoader.load(), 600, 1000);
        st.setScene(mainpage);
        st.show();
    }
}