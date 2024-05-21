package com.example.fxtest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import com.example.fxtest.brick.Brick;
import com.example.fxtest.brick.BrickI;
import com.example.fxtest.brick.BrickO;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
class GameBoard1Test {
    GameBoard1 gameBoard;
    @BeforeEach
    public void set() {
        gameBoard = new GameBoard1();
    }


    @Test
    @DisplayName("isRowFull() 확인")
    public void testLineFull() {
        //given
        // 한 줄을 완전히 채우기
        for (int col = 0; col < 10; col++) {
            gameBoard.board[0][col]=1;
        }
        //when
        boolean full= gameBoard.isRowFull(0);
        //then
        assertTrue(full);
    }

    @Test
    @DisplayName("updateScore() 확인")
    public void testUpdateScore(){
        //given
        int testScore = 0;
        //when
        gameBoard.updateScore(0);
        int result = gameBoard.getScore();
        //then
        assertEquals(testScore,result);
    }

    @Test
    @DisplayName("RemoveRows() 확인")
    public void testRemoveRows(){

        //given
        // 한 줄을 완전히 채우기
        for (int col = 0; col < 10; col++) {
            gameBoard.board[1][col]=1;
        }
        gameBoard.board[0][0]=1;
        gameBoard.removeRow(1);
        int front = gameBoard.board[0][0];
        int back = gameBoard.board[1][0];

        //then
        assertNotEquals(front, back);
    }

    @Test
    @DisplayName("removeFullRows() 확인")
    public void testRemoveFullRows(){

        //given
        for (int col = 0; col < 10; col++) {
            gameBoard.board[0][col]=1;
        }
        gameBoard.updateScore(0);
        gameBoard.removeFullRows();
        int score = gameBoard.getScore();
        int test = 0;

        //then
        assertNotEquals(test, score);
    }

    @Test
    @DisplayName("removeFullColumn() 확인")
    public void testRemoveFullColumn(){

        //given
        for (int col = 0; col < 10; col++) {
            gameBoard.board[col][0]=1;
        }
        gameBoard.removeFullColumn(0);
        int test = 0;

        //then
        assertEquals(test, gameBoard.board[0][0]);
    }

    @Test
    @DisplayName("getRemovedRows 확인")
    public void testGetRemovedRows(){

        //given
        for (int col = 0; col < 10; col++) {
            gameBoard.board[0][col]=1;
        }
        List<Integer> test = gameBoard.getRemovedRows();
        int result = test.size();
        //then
        assertEquals(result, 1);
    }

    @Test
    public void testScore(){
        gameBoard.updateScoreLine(1);
        Assertions.assertEquals(gameBoard.deleteLine, 1 );

        gameBoard.updateScoreLine(2);
        Assertions.assertEquals(gameBoard.deleteLine, 3 );

        gameBoard.updateScoreLine(3);
        Assertions.assertEquals(gameBoard.deleteLine, 6 );

        gameBoard.updateScoreLine(4);
        Assertions.assertEquals(gameBoard.deleteLine, 10 );
    }

    @Test
    @DisplayName("attackLine 테스트")
    public void testAttackLine(){
        List<Integer> removeLine = new ArrayList<>();
        removeLine.add(19);
        removeLine.add(18);
        for(int i = 0; i<10; i++){
            gameBoard.board[19][i]=1;
        }
        for(int i = 0; i<10; i++){
            gameBoard.board[18][i]=1;
        }
        BrickO brick = new BrickO(18,1,Color.BLUE,gameBoard);
        List<List<Integer>> temp = gameBoard.attackLine( removeLine, brick,gameBoard);

        List<List<Integer>> matrix = new ArrayList<>();
        // 각 행을 반복
        for (int i = 0; i < 2; i++) {
            List<Integer> row = new ArrayList<>();
            // 각 열을 반복
            for (int j = 0; j < 10; j++) {
                if (j < 2) {
                    row.add(0); // 처음 두 개의 값은 0
                } else {
                    row.add(1); // 나머지 값은 1
                }
            }
            matrix.add(row); // 완성된 행을 이중 리스트에 추가
        }

        assertEquals(matrix, temp);
    }

    @Test
    @DisplayName("attackStart")
    public void testAttackStart(){
        for(int i = 0; i<9; i++){
            gameBoard.attackBoard[9][i]=1;
        }
        gameBoard.attackStart();

        List<Integer> matrix= new ArrayList<>();
        for(int i=0; i<9; i++){
            matrix.add(1);
        }
        matrix.add(0);

        List<Integer> test= new ArrayList<>();
        for(int i=0; i<10; i++){
            test.add(gameBoard.board[19][i]);
        }

        assertEquals(matrix,test);

    }

    @Test
    @DisplayName("attackboard초기화")
    public void testClean(){
        gameBoard.attackBoard[9][0]=1;

        gameBoard.attackBoardClean();

        assertEquals(0, gameBoard.attackBoard[9][0]);
    }
}