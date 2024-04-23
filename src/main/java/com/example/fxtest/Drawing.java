
package com.example.fxtest;

import com.example.fxtest.brick.Block;
import com.example.fxtest.brick.Brick;
import com.example.fxtest.brick.Item;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;

public class Drawing {

    private static GridPane boardView;

    //GameBoard FXML이 생성되면서 GameBoardController가 initialize 될때 자동으로 GridPane 주입
    public static void setBoardView(GridPane boardView) {
        Drawing.boardView = boardView;
    }

    public static void colorFill(Brick brick){

        for (Block block : brick.getBlockList()) { // currentBrick에서 Block 배열을 가져오는 가정
            int x = block.getX();
            int y = block.getY();

            String string = returnItemSymbol(block);

            Label label = new Label(string); //여기서 아이템들 폰트 바꾸고
            label.setFont(Font.font("Arial", FontWeight.BOLD, GameBoardController.cellWidth)); //set size
            label.setTextFill(Color.BLUE); //색깔도 바꾸고
            GridPane.setHalignment(label, javafx.geometry.HPos.CENTER);
            GridPane.setValignment(label, javafx.geometry.VPos.CENTER);

            // GridPane에 Rectangle 추가
            boardView.add(label, y, x);
        }
    }

    public static void colorErase(Brick brick) {
        for(Block block : brick.getBlockList()) {
            Label LabelAt = getLableAt(boardView,block.getY(),block.getX());
            boardView.getChildren().remove(LabelAt);
        }
    }

    public static Label getLableAt(GridPane gridPane, int columnIndex, int rowIndex) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            Integer nodeColumnIndex = GridPane.getColumnIndex(node);
            Integer nodeRowIndex = GridPane.getRowIndex(node);

            if (nodeColumnIndex != null && nodeRowIndex != null && nodeColumnIndex == columnIndex && nodeRowIndex == rowIndex) {
                if (node instanceof Label) {
                    return (Label) node;
                }
            }
        }
        return null;
    }

    //줄 지웠을때 Gridpane만 냅두고 위에 객체들 새로 그리기
    public static void updateBoardView(){
        // GridPane에서 제거할 Label 객체들을 담을 리스트 생성
        List<Node> labelsToRemove = new ArrayList<>();

        // GridPane의 모든 자식 노드를 순회하며 Label 타입의 노드를 찾는다
        for (Node child : boardView.getChildren()) {
            if (child instanceof Label) {
                labelsToRemove.add(child);
            }
        }

        // 찾은 Label 객체들을 GridPane에서 제거
        boardView.getChildren().removeAll(labelsToRemove);

        // board 배열을 순회
        for (int y = 0; y < GameBoard.HEIGHT; y++) {
            for (int x = 0; x < GameBoard.WIDTH; x++) {
                // board에서 1이면 Label 생성 후 GridPane에 추가
                if (GameBoard.board[y][x] >= 1) {
                    String string = returnItemSymbol(GameBoard.board[y][x]);
                    Label label = new Label(string);

                    label.setFont(Font.font("Arial", FontWeight.BOLD, GameBoardController.cellWidth)); //set size
                    label.setTextFill(Color.BLUE);
                    GridPane.setHalignment(label, javafx.geometry.HPos.CENTER);
                    GridPane.setValignment(label, javafx.geometry.VPos.CENTER);
                    boardView.add(label, x, y);
                }
            }
        }
        System.out.println("보드 업데이트 완료");


    }


    public static String returnItemSymbol(Block block){
        Item item = block.getItem();
        if(item==Item.NORMAL){
            return "O";
        }
        else if (item==Item.WEIGHT) {
            return "W";
        }
        else if(item==Item.ROWDELETE){
            return "R";
        }
        else if (item==Item.COLUMNDELETE) {
            return "C";
        }
        else if (item==Item.BLIND) {
            return "B";
        }
        else{
            return "N";
        }
    }
    public static String returnItemSymbol(int num){
        if(num==1){
            return "O";
        }
        else if (num==2) {
            return "W";
        }
        else if(num==3){
            return "R";
        }
        else if (num==4) {
            return "C";
        }
        else if (num==5) {
            return "B";
        }
        else{
            return "N";
        }
    }
}
