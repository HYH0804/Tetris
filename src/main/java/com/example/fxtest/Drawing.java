package com.example.fxtest;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.Iterator;

public class Drawing {
    private static GridPane gridPane = HelloApplication.gridPane;
    private static Label label;
    private static Color[] Colors = {Color.BLACK,Color.SKYBLUE,Color.YELLOW, Color.PURPLE,
    Color.GREEN, Color.RED, Color.BLUE, Color.ORANGE}; //0: default, from 1 to 7: IOTSZJL

    //Drawing Brick / 합칠 때 BrickZ 대신 Brick으로
    public static void BrickDrawing(BrickZ B) {
        for (Block block : B.blockList) {
            GridAdd(block.getX(),block.getY(), Colors[0]);
        }
    }

    //Removing Brick / 합칠 때 BrickZ 대신 Brick으로
    public static void BrickRemoving(BrickZ B) {
        ObservableList<Node> board_node = gridPane.getChildren();
        Iterator<Node> iterator = board_node.iterator();

       while(iterator.hasNext()) {
           Node node = iterator.next();
            if(GridPane.getColumnIndex(node) == null
                    || GridPane.getRowIndex(node) == null) {
                continue;
            }
            for(Block block : B.blockList) {
                if(GridPane.getColumnIndex(node) == block.getX()
                        && GridPane.getRowIndex(node) == block.getY()){
                    iterator.remove();
                }
            }
        }
    }

    //display only board
    public static void BoardDrawing(int[][] board) {
        //first removing
        ObservableList<Node> board_node = gridPane.getChildren();
        Iterator<Node> iterator = board_node.iterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            if (GridPane.getColumnIndex(node) == null || GridPane.getRowIndex(node) == null) {
                continue;
            }
            iterator.remove();
        }
        //then drawing
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 10; x++) {
                if (board[y][x] != 0) {
                    GridAdd(x, y, Colors[board[y][x]]);
                }
            }
        }
    }

    //add label to gridPane
    private static void GridAdd ( int x, int y, Color color) {
        label = new Label("O");
        label.setFont(Font.font("Arial", FontWeight.BOLD, 20)); //set size
        label.setTextFill(color);
        GridPane.setHalignment(label, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(label, javafx.geometry.VPos.CENTER);
        gridPane.add(label, x, y);
    }
}
