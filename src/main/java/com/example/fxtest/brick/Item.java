package com.example.fxtest.brick;

import com.example.fxtest.Drawing;
import com.example.fxtest.GameBoard;
import javafx.animation.PauseTransition;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.List;

public enum Item {
    NORMAL(1),
    WEIGHT(2),
    ROWDELETE(3),
    COLUMNDELETE(4),
    BLIND(5),
    NUCLEAR(6);


    private final int num;

    private static PauseTransition hidePause;

    Item(int num) {
        this.num = num;
    }

    public int getNum(){
        return this.num;
    }

    // 정수 값에 해당하는 Item enum을 반환하는 정적 메서드
    public static Item fromNum(int num) {
        for (Item item : Item.values()) {
            if (item.getNum() == num) {
                return item;
            }
        }
        throw new IllegalArgumentException("Invalid Item number: " + num);
    }

    public static void doItem(GameBoard gameBoard, GridPane gridPane,Block itemBlock){
        Item item = itemBlock.getItem();

        if(item==ROWDELETE){
            int deleteRow = itemBlock.getX();
            Drawing.updateBoardView(deleteRow);
            gameBoard.removeRow(deleteRow);
        }
        else if (item==COLUMNDELETE) {
            int deleteColumn = itemBlock.getY();
            Drawing.updateBoardColumnView(deleteColumn);
            gameBoard.removeFullColumn(deleteColumn);
        }
        else if(item==BLIND){
            gridPane.setVisible(false);
            System.out.println("Blind 처리");
        }
        else if(item==NUCLEAR){
            System.out.println("NUCLEAR 처리");
        }
        else{
            System.out.println(item+" 은 해당 순서에 실행하지 않음.");
        }

    }

    //이걸로 GameBoardController에서 가져와서 Stop 실행
    public static PauseTransition getHidePause() {
        return hidePause;
    }

    //GameBoardController minute10에서...
    public static void turnEndDoItem(Brick currentBrick,GameBoard gameBoard, GridPane gridPane){
        Block itemBlock = currentBrick.getItem();
        if(itemBlock!=null) {
            Item item = itemBlock.getItem();
            if (item == Item.ROWDELETE || item == Item.COLUMNDELETE) {
                doItem(gameBoard, gridPane, itemBlock);
            }
        }

    }

    public static void sponDoItem(Brick currentBrick,GameBoard gameBoard, GridPane gridPane){
        Block itemBlock = currentBrick.getItem();
        if(itemBlock!=null) {
            Item item = itemBlock.getItem();
            if (item == Item.BLIND) {
                doItem(gameBoard, gridPane, itemBlock);
            }
        }
    }

    public static void fullLineDoItem(Brick currentBrick,GameBoard gameBoard, GridPane gridPane){
        Block itemBlock = currentBrick.getItem();
        Item item = itemBlock.getItem();
        if(item==Item.NUCLEAR) {
            doItem(gameBoard, gridPane, itemBlock);
        }
    }

}
