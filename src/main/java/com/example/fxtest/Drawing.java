package com.example.fxtest;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;
import java.util.Iterator;

public class Drawing {
    private static GridPane gridPane = HelloApplication.gridPane;

    public static void BrickDrawing(BrickZ B) {
        // interface 쓰기위해 field도 정의
        Rectangle rect;
        for (Block block : B.blockList) {
            rect = new Rectangle(20, 20, Color.BLUE);
            gridPane.add(rect, block.getX(), block.getY());
        }
    }

    public static void BrickRemoving(BrickZ B) {
        ObservableList<Node> board_node = gridPane.getChildren();
        Iterator<Node> iterator = board_node.iterator();

       while(iterator.hasNext()) {
           Node node = iterator.next();
            if(GridPane.getColumnIndex(node) == null || GridPane.getRowIndex(node) == null) {
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

    public static void BoardDrawing(int[][] board) {
        Rectangle rect;
        for(int y = 0; y < 20; y++) {
            for (int x = 0; x < 10; x++) {
                if(board[y][x] != 0) {
                    rect = new Rectangle(20,20,Color.BLUE);
                    gridPane.add(rect,x,y);
                }
            }
        }
    }

}
