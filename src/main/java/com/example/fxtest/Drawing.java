
package com.example.fxtest;

import com.example.fxtest.brick.Block;
import com.example.fxtest.brick.Brick;
import com.example.fxtest.brick.Item;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


//Drawing에 BoardView Set 장착해서 그리는게 아니라 그때그때 파라미터로
public class Drawing {


    //그리드페인에 현재블록 그리기
    public static void colorFill(Brick brick,GridPane boardView){

        for (Block block : brick.getBlockList()) { // currentBrick에서 Block 배열을 가져오는 가정
            int x = block.getX();
            int y = block.getY();

            String string = returnItemSymbol(block);

            Label label = new Label(string); //여기서 아이템들 폰트 바꾸고
            label.setFont(Font.font("Arial", FontWeight.BOLD, GameBoardController.cellWidth)); //set size
            label.setTextFill(block.getColor()); //색깔도 바꾸고
            boardView.setHalignment(label, javafx.geometry.HPos.CENTER);
            boardView.setValignment(label, javafx.geometry.VPos.CENTER);

            // GridPane에 Rectangle 추가
            boardView.add(label, y, x);
        }
    }


    //Brick 색 삭제
    public static void colorErase(Brick brick,GridPane boardView) {
        for(Block block : brick.getBlockList()) {
            Label LabelAt = getLableAt(boardView,block.getY(),block.getX());
            boardView.getChildren().remove(LabelAt);
        }
    }

    //블록 색 삭제
    public static void colorErase(int x,int y,GridPane boardView){
        Label lableAt = getLableAt(boardView, x, y);
        boardView.getChildren().remove(lableAt);
    }


    //특정 GridPane의 인덱스에 있는 Lable 객체 반환
    public static Label getLableAt(GridPane gridPane, int columnIndex, int rowIndex) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            Integer nodeColumnIndex = gridPane.getColumnIndex(node);
            Integer nodeRowIndex = gridPane.getRowIndex(node);

            if (nodeColumnIndex != null && nodeRowIndex != null && nodeColumnIndex == columnIndex && nodeRowIndex == rowIndex) {
                if (node instanceof Label) {
                    return (Label) node;
                }
            }
        }
        return null;
    }
    public static void removeAnimeRow(GridPane gridPane, int rowIndex) {
        // 자식 노드들을 삭제하기 위해 루프를 돌리며 순회
        gridPane.getChildren().removeIf(node ->
                gridPane.getRowIndex(node) != null && gridPane.getRowIndex(node) == rowIndex && (node instanceof Rectangle)
        );
    }
    public static void removeAnimeColumn(GridPane gridPane, int columnIndex) {
        // 자식 노드들을 삭제하기 위해 루프를 돌리며 순회
        gridPane.getChildren().removeIf(node ->
                gridPane.getColumnIndex(node) != null && gridPane.getColumnIndex(node) == columnIndex &&(node instanceof Rectangle)
        );
    }

    public static void animeRow(int fullRow, GridPane boardView) {
        //가득찬 줄을 가져온다
        for (int col = 0; col < GameBoard1.WIDTH; col++) {
            Rectangle rectangle = new Rectangle(GameBoardController.cellWidth,GameBoardController.cellWidth,Color.BLACK);
 /*           GridPane.setHalignment(label, javafx.geometry.HPos.CENTER);
            GridPane.setValignment(label, javafx.geometry.VPos.CENTER);*/

            // GridPane에 Rectangle 추가
            boardView.add(rectangle, col, fullRow);
        }
        Timeline timeline = new Timeline(
                // 1초 후에 아무 동작도 하지 않는 KeyFrame을 추가
                new KeyFrame(Duration.seconds(0.3), event -> {
                        removeAnimeRow(boardView,fullRow);
                    // 아무 동작도 하지 않음
                })
        );
        timeline.setCycleCount(1);
        // 타임라인 실행
        timeline.play();

        //Gameboard클래스의 removerow함수에서 anime(fullrow)를 실행한다.
    }

    public static void animeCol(int col,GridPane boardView) {
        //가득찬 줄을 가져온다
        for (int row = 0; row < GameBoard1.HEIGHT; row++) {
            Rectangle rectangle = new Rectangle(GameBoardController.cellWidth,GameBoardController.cellWidth,Color.BLACK);
 /*           GridPane.setHalignment(label, javafx.geometry.HPos.CENTER);
            GridPane.setValignment(label, javafx.geometry.VPos.CENTER);*/

            // GridPane에 Rectangle 추가
            boardView.add(rectangle, col, row);
        }
        Timeline timeline = new Timeline(
                // 1초 후에 아무 동작도 하지 않는 KeyFrame을 추가
                new KeyFrame(Duration.seconds(0.3), event -> {
                    removeAnimeColumn(boardView,col);
                    // 아무 동작도 하지 않음
                })
        );
        timeline.setCycleCount(1);
        // 타임라인 실행
        timeline.play();
        //가득찬 줄의 boardview를 색칠해서 애니메이션 기능 만든다.
        //Gameboard클래스의 removerow함수에서 anime(fullrow)를 실행한다.
    }

    //줄 지웠을때 Gridpane만 냅두고 위에 객체들 새로 그리기
    public static void updateBoardView(GridPane boardView, int[][] board){ //업데이트 전 GUI board를 업데이트 된 배열 board에 맞춰 새로 그리기
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
        for (int y = 0; y < board.length; y++) { //높이
            for (int x = 0; x < board[0].length; x++) { //가로
                // board에서 1이면 Label 생성 후 GridPane에 추가
                if (board[y][x] >= 1) {
                    String string = returnItemSymbol(board[y][x]);
                    Label label = new Label(string);

                    label.setFont(Font.font("Arial", FontWeight.BOLD, GameBoardController.cellWidth)); //set size
                    label.setTextFill(Color.BLUE);
                    boardView.setHalignment(label, javafx.geometry.HPos.CENTER);
                    boardView.setValignment(label, javafx.geometry.VPos.CENTER);
                    boardView.add(label, x, y);
                }
            }
        }
        System.out.println("보드 업데이트 완료");
    }

/*    public static void updateBoardView2(){
        // GridPane에서 제거할 Label 객체들을 담을 리스트 생성
        List<Node> labelsToRemove = new ArrayList<>();

        // GridPane의 모든 자식 노드를 순회하며 Label 타입의 노드를 찾는다
        for (Node child : boardView2.getChildren()) {
            if (child instanceof Label) {
                labelsToRemove.add(child);
            }
        }

        // 찾은 Label 객체들을 GridPane에서 제거
        boardView2.getChildren().removeAll(labelsToRemove);

        // board 배열을 순회
        for (int y = 0; y < GameBoard1.HEIGHT; y++) {
            for (int x = 0; x < GameBoard1.WIDTH; x++) {
                // board에서 1이면 Label 생성 후 GridPane에 추가
                if (GameBoard2.board[y][x] >= 1) {
                    String string = returnItemSymbol(GameBoard2.board[y][x]);
                    Label label = new Label(string);

                    label.setFont(Font.font("Arial", FontWeight.BOLD, GameBoard2Controller.cellWidth)); //set size
                    label.setTextFill(Color.BLUE);
                    GridPane.setHalignment(label, javafx.geometry.HPos.CENTER);
                    GridPane.setValignment(label, javafx.geometry.VPos.CENTER);
                    boardView2.add(label, x, y);
                }
            }
        }
        System.out.println("보드 업데이트 완료");
    }*/

/*    public static void updateBoardView(List<Integer> removeLineList){
        for(int line : removeLineList){ //삭제해야 될 라인수만큼 반복

            for(int coloumn=0; coloumn<GameBoard.WIDTH; coloumn++){
                for(int row=line-1; row>1 ; row--){
                    if(GameBoard.board[row][coloumn]!=0) {
                        System.out.println("없어질 라인 " + line+ " coloumn 값 " +coloumn + " row 값 "+ row);
                        Label lableAt = getLableAt(boardView, coloumn, row);
                        String text = lableAt.getText();
                        Color textColor = (Color) lableAt.getTextFill();
                        boardView.getChildren().remove(lableAt);
                        Label label = newLabel(text, textColor);
                        boardView.add(label, coloumn, row); //가로세로
                    }
                    else{
                        continue;
                    }


                }
            }
        }
    }*/
    public static void updateBoardView(List<Integer> removeLineList,GridPane boardView, int[][] board){
        Collections.sort(removeLineList); // 내림차순으로 정렬
        for (int line : removeLineList) { // 삭제해야 될 라인 수만큼 반복
            removeRow(boardView, line);
            for (int column = 0; column < board[0].length; column++) {
                for (int row = line - 1; row >= 0; row--) { // 최상단부터 시작
                    Label labelAbove = getLableAt(boardView, column, row);
                    if (labelAbove != null) {
                        boardView.setRowIndex(labelAbove, row + 1); // 기존 라벨을 한 칸 아래로 이동
                    }
                }
            }
        }
    }
/*    public static void updateBoardView2(List<Integer> removeLineList){
        Collections.sort(removeLineList); // 내림차순으로 정렬
        for (int line : removeLineList) { // 삭제해야 될 라인 수만큼 반복
            removeRow(boardView2, line);
            for (int column = 0; column < GameBoard1.WIDTH; column++) {
                for (int row = line - 1; row >= 0; row--) { // 최상단부터 시작
                    Label labelAbove = getLableAt(boardView2, column, row);
                    if (labelAbove != null) {
                        GridPane.setRowIndex(labelAbove, row + 1); // 기존 라벨을 한 칸 아래로 이동
                    }
                }
            }
        }
    }*/
    //-1 이면 한칸 위로 , +1 이면 한칸 아래로

    public static void updateAttackBoardView1(List<Integer> removeLineList, Brick currentBrick, int topRowIndex){

    }

    //어택 받으면 위로 gui올리는 함수
    public static void attackUpdateBoardView(List<List<Integer>> attackRows ,GridPane boardView){
        if(attackRows.size()!=0){
            int i=0;
            for (List<Integer> count : attackRows) { // 삭제해야 될 라인 수만큼 반복
                for (int column = 0; column < 10; column++) {
                    for (int row = 1; row < 22; row++) { // 최상단부터 시작
                        Label labelAbove = getLableAt(boardView, column, row);
                        if (labelAbove != null) {
                            GridPane.setRowIndex(labelAbove, row - 1); // 기존 라벨을 한 칸 아래로 이동
                        }
                    }
                }
                //여기서 어택보드 한칸 추가 gui
                List<Integer> temp = null;
                temp = attackRows.get(i);
                i+=1;
                for (int y = 0; y < 10; y++) {
                    Label label = new Label("H");

                    label.setFont(Font.font("Arial", FontWeight.BOLD, GameBoardController.cellWidth)); //set size
                    label.setTextFill(Color.BLACK);
                    GridPane.setHalignment(label, javafx.geometry.HPos.CENTER);
                    GridPane.setValignment(label, javafx.geometry.VPos.CENTER);
                    if (temp.get(y) != 0) {
                        boardView.add(label, y, 21);
                    }
                }
            }
        }
        else {
            System.out.println("NO_________________");
        }
    }


    //가로줄 삭제 후 새로 아예 쓰는게 아니라 라인 한줄씩 내리면서 업데이트
    public static void updateBoardView(int line,GridPane boardView){
            removeRow(boardView, line);
            for (int column = 0; column < GameBoard1.WIDTH; column++) {
                for (int row = line - 1; row >= 0; row--) { // 최상단부터 시작
                    Label labelAbove = getLableAt(boardView, column, row);
                    if (labelAbove != null) {
                        boardView.setRowIndex(labelAbove, row + 1); // 기존 라벨을 한 칸 아래로 이동
                    }
                }
            }
    }


    //세로줄 삭제 후 업데이트
    public static void updateBoardColumnView(int column,GridPane boardView){
        removeColumn(boardView,column);

    }


    public static void removeRow(GridPane gridPane, int rowIndex) {
        // 자식 노드들을 삭제하기 위해 루프를 돌리며 순회
        gridPane.getChildren().removeIf(node ->
                gridPane.getRowIndex(node) != null && gridPane.getRowIndex(node) == rowIndex
        );
    }


    public static void removeColumn(GridPane gridPane, int columnIndex) {
        // 자식 노드들을 삭제하기 위해 루프를 돌리며 순회
        gridPane.getChildren().removeIf(node ->
                gridPane.getColumnIndex(node) != null && gridPane.getColumnIndex(node) == columnIndex
        );
    }


    public static Label newLabel(String text , Color color){
        Label label = new Label(text); //이거 아이템 들어가면 고민 좀 해야될듯
        label.setFont(Font.font("Arial", FontWeight.BOLD, GameBoardController.cellWidth)); //set size
        label.setTextFill(color);
        GridPane.setHalignment(label, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(label, javafx.geometry.VPos.CENTER);
        return label;
    }


    public static String returnItemSymbol(Block block){
        Item item = block.getItem();
        if(item==Item.NORMAL){
            return "O";
        }
        else if (item==Item.WEIGHT) {
            return "M";
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
            return "M";
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

    public static void displayNextBrick(Brick brick, GridPane nextBrickView) {
        nextBrickView.getChildren().clear(); // 이전에 표시된 블록 제거

        for (Block block : brick.getBlockList()) { // currentBrick에서 Block 배열을 가져오는 가정
            int x = block.getX();
            int y = block.getY();

            String string = returnItemSymbol(block);

            Label label = new Label(string); //여기서 아이템들 폰트 바꾸고
            label.setFont(Font.font("Arial", FontWeight.BOLD, GameBoardController.cellWidth)); //set size
            label.setTextFill(block.getColor()); //색깔도 바꾸고
            nextBrickView.setHalignment(label, javafx.geometry.HPos.CENTER);
            nextBrickView.setValignment(label, javafx.geometry.VPos.CENTER);

            // GridPane에 Rectangle 추가
            nextBrickView.add(label, y, x);
        }
    }
}
