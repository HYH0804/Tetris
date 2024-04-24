package com.example.fxtest.brick;

import com.example.fxtest.Drawing;
import com.example.fxtest.GameBoard;
import javafx.scene.layout.GridPane;

import java.util.List;

public enum Item {
    NORMAL(1),
    WEIGHT(2),
    ROWDELETE(3),
    COLUMNDELETE(4),
    BLIND(5),
    NUCLEAR(6);


    private final int num;

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

    public static void doItem(Brick currentBrick,GameBoard gameBoard, GridPane gridPane){
        Block itemBlock = currentBrick.getItem();
        Item item = itemBlock.getItem();

        if(item==ROWDELETE){
            int deleteRow = itemBlock.getX();
            Drawing.updateBoardView(deleteRow);
            gameBoard.removeFullRows();
        }
        else if (item==COLUMNDELETE) {
            int deleteColumn = itemBlock.getY();
            Drawing.updateBoardColumnView(deleteColumn);
        }
        else if(item==BLIND){

        }
        else if(item==NUCLEAR){

        }
        else{
            System.out.println("Item 클래스 : 아이템 숫자 없음");
        }

    }

}
