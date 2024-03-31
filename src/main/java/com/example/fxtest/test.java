package com.example.fxtest;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

//테스트용

public class test implements Initializable {

    @FXML
    private GridPane boardView; //컨트롤View 매핑


    // GridPane에 Rectangle 추가


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Rectangle rectangle = new Rectangle(20, 20);
        boardView.add(rectangle, 1,2);
    }
}
