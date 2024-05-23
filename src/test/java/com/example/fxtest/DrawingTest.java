package com.example.fxtest;

import com.example.fxtest.brick.Block;
import com.example.fxtest.brick.BrickI;
import com.example.fxtest.brick.BrickO;
import com.example.fxtest.brick.Item;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DrawingTest {

    private GridPane boardView;
    private double cellWidth;
    private GameBoard1 gameBoard1;

    @BeforeEach
    public void setUp() {
        boardView = new GridPane();
        cellWidth = 20.0; // 임의의 셀 너비 값
    }
    @Test
    public void testGetLabelAt() {
        // 준비: 그리드판과 라벨 추가

        GridPane gridPane = new GridPane();
        Drawing.animeCol(1);
        Label label = new Label("T");
        gridPane.add(label, 1, 1);

        // 테스트: getLabelAt 메서드 호출하여 해당 위치의 라벨 가져오기
        Label resultLabel = Drawing.getLableAt(gridPane,1,1);

        // 확인: 가져온 라벨이 추가한 라벨과 같아야 함
        assertEquals(label, resultLabel, "Label at position (1, 1) should be the added label");

        // 테스트: 존재하지 않는 위치의 라벨 가져오기
        Label nullLabel = Drawing.getLableAt(gridPane, 0, 0);

        // 확인: 존재하지 않는 위치의 라벨은 null이어야 함
        assertNull(nullLabel, "No label should be found at position (0, 0)");
    }
    @Test
    @DisplayName("colorErase")
    public void testColorErase() {
        // Block 및 Brick 객체 생성
        Item item =Item.WEIGHT;
        Block block1 = new Block(0, 0, item, Color.BLUE);
        //Block block2 = new Block(1, 1, "B", "blue");
        BrickO brick = new BrickO(1,1,Color.BLUE,gameBoard1);
        // colorFill 메서드 호출
        Drawing.colorErase(brick, boardView);
        assertEquals(0, boardView.getChildren().size(), "No labels should be in the gridpane after erase");

    }
    @Test
    @DisplayName("returnItem1")
    public void testreturnItemSymbol() {
        Item item = Item.ROWDELETE;
        // Brick과 Block 객체를 생성하여 설정
        Block block1 = new Block(0, 0, item, Color.BLUE);
        String test1=Drawing.returnItemSymbol(block1);
        assertEquals("R", test1);

        block1.setItem(Item.WEIGHT);
        String test2=Drawing.returnItemSymbol(block1);
        assertEquals("M", test2);

        block1.setItem(Item.COLUMNDELETE);
        String test3=Drawing.returnItemSymbol(block1);
        assertEquals("C", test3);

        block1.setItem(Item.NUCLEAR);
        String test4=Drawing.returnItemSymbol(block1);
        assertEquals("N", test4);

        block1.setItem(Item.NORMAL);
        String test5=Drawing.returnItemSymbol(block1);
        assertEquals("O", test5);

        block1.setItem(Item.BLIND);
        String test6=Drawing.returnItemSymbol(block1);
        assertEquals("B", test6);
    }
    @Test
    @DisplayName("returnItem2")
    public void testreturnItemSymbol2() {
        // Brick과 Block 객체를 생성하여 설정
        String test1=Drawing.returnItemSymbol(1);
        assertEquals("O", test1);

        String test2=Drawing.returnItemSymbol(2);
        assertEquals("M", test2);

        String test3=Drawing.returnItemSymbol(3);
        assertEquals("R", test3);

        String test4=Drawing.returnItemSymbol(4);
        assertEquals("C", test4);

        String test5=Drawing.returnItemSymbol(5);
        assertEquals("B", test5);

        String test6=Drawing.returnItemSymbol(6);
        assertEquals("N", test6);
    }
}