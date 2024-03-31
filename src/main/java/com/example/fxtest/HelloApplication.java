package com.example.fxtest;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static GridPane gridPane;
    @Override
    public void start(Stage stage) throws IOException {
        StageSaver.setStage(stage);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("GameBoard.fxml"));

        //save griddPane / fx:id를 사용해서 처리해도 무방
        Parent root = fxmlLoader.load();
        AnchorPane anchorPane = (AnchorPane) root;
        ObservableList<Node> children = anchorPane.getChildren();
        gridPane = (GridPane)children.get(0);

        //BrickZ Z = new BrickZ(5,2);
        //Drawing.BrickDrawing(Z);
        //Drawing.BrickRemoving(Z);

        //GameBoard.board[10][1] = 1;
        //GameBoard.board[10][2] = 2;
        //GameBoard.board[10][3] = 3;
        //GameBoard.board[10][4] = 4;
        //GameBoard.board[10][5] = 5;
        //GameBoard.board[10][6] = 6;
        //GameBoard.board[10][7] = 7;
        //Drawing.BoardDrawing(GameBoard.board);

        Scene scene = new Scene(root,300, 400);
        stage.setTitle("Tetris");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        launch();
    }

}