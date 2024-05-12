<<<<<<< HEAD
/*
package fxtest;

import com.example.fxtest.GameBoard1;
import org.junit.jupiter.api.*;
=======
package fxtest;
import static org.mockito.Mockito.*;

import com.example.fxtest.Drawing;
import com.example.fxtest.GameBoard;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
>>>>>>> bbe697f0f626ce69284da1bd18325a3529e425b5

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

<<<<<<< HEAD
    GameBoard1 gameBoard;
    @BeforeEach
    public void set() {
        gameBoard = new GameBoard1();
=======
    GameBoard gameBoard;
    @BeforeEach
    public void set() {
        gameBoard = new GameBoard();
>>>>>>> bbe697f0f626ce69284da1bd18325a3529e425b5

    }


    @Test
    @DisplayName("isRowFull() 확인")
    public void testLineFull() {
        //given
        // 한 줄을 완전히 채우기
        for (int col = 0; col < 10; col++) {
<<<<<<< HEAD
            GameBoard1.board[0][col]=1;
=======
            GameBoard.board[0][col]=1;
>>>>>>> bbe697f0f626ce69284da1bd18325a3529e425b5
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
<<<<<<< HEAD
        GameBoard1.updateScore(0);
        int result = GameBoard1.getScore();
=======
        GameBoard.updateScore(0);
        int result = GameBoard.getScore();
>>>>>>> bbe697f0f626ce69284da1bd18325a3529e425b5
        //then
        assertEquals(testScore,result);
    }

    @Test
<<<<<<< HEAD
=======
    @DisplayName("RemoveRows() 확인")
    public void testRemoveRows(){

        //given
        GridPane gridPane = new GridPane();
        for (int col = 0; col < 10; col++) {
            Rectangle rect = new Rectangle(50, 50);  // 각 셀을 위한 사각형
            rect.setStroke(Color.BLACK);  // 사각형의 테두리 색상 설정
            rect.setFill(null);  // 사각형 내부는 채우지 않음
            gridPane.add(rect, 0, col);  // 사각형을 그리드에 추가
        }
        Drawing.setBoardView(gridPane);
        // 한 줄을 완전히 채우기
        for (int col = 0; col < 10; col++) {
            GameBoard.board[1][col]=1;
        }
        GameBoard.board[0][0]=1;
        gameBoard.removeRow(1);
        int front = GameBoard.board[0][0];
        int back = GameBoard.board[1][0];

        //then
        assertNotEquals(front, back);
    }

    @Test
    @DisplayName("removeFullRows() 확인")
    public void testRemoveFullRows(){

        //given
        for (int col = 0; col < 10; col++) {
            GameBoard.board[0][col]=1;
        }
        GameBoard.updateScore(0);
        gameBoard.removeFullRows();
        int score = GameBoard.getScore();
        int test = 0;

        //then
        assertNotEquals(test, score);
    }

    @Test
    @DisplayName("removeFullColumn() 확인")
    public void testRemoveFullColumn(){

        //given
        for (int col = 0; col < 10; col++) {
            GameBoard.board[col][0]=1;
        }
        gameBoard.removeFullColumn(0);
        int test = 0;

        //then
        assertEquals(test, GameBoard.board[0][0]);
    }

    @Test
>>>>>>> bbe697f0f626ce69284da1bd18325a3529e425b5
    @DisplayName("getRemovedRows 확인")
    public void testGetRemovedRows(){

        //given
        for (int col = 0; col < 10; col++) {
<<<<<<< HEAD
            GameBoard1.board[0][col]=1;
=======
            GameBoard.board[0][col]=1;
>>>>>>> bbe697f0f626ce69284da1bd18325a3529e425b5
        }
        List<Integer> test = gameBoard.getRemovedRows();
        int result = test.size();
        //then
        assertEquals(result, 1);
    }

<<<<<<< HEAD
    @Test
    public void testScore(){
        gameBoard.updateScoreLine(1);
        Assertions.assertEquals(GameBoard1.deleteLine, 1 );

        gameBoard.updateScoreLine(2);
        Assertions.assertEquals(GameBoard1.deleteLine, 3 );

        gameBoard.updateScoreLine(3);
        Assertions.assertEquals(GameBoard1.deleteLine, 6 );

        gameBoard.updateScoreLine(4);
        Assertions.assertEquals(GameBoard1.deleteLine, 10 );
    }


}*/
=======

}
>>>>>>> bbe697f0f626ce69284da1bd18325a3529e425b5
