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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    GameBoard gameBoard;
    @BeforeEach
    public void set() {
        gameBoard = new GameBoard();

    }


    @Test
    @DisplayName("isRowFull() 확인")
    public void testLineFull() {
        //given
        // 한 줄을 완전히 채우기
        for (int col = 0; col < 10; col++) {
            GameBoard.board[0][col]=1;
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
        GameBoard.updateScore(0);
        int result = GameBoard.getScore();
        //then
        assertEquals(testScore,result);
    }

    @Test
    @DisplayName("getRemovedRows 확인")
    public void testGetRemovedRows(){

        //given
        for (int col = 0; col < 10; col++) {
            GameBoard.board[0][col]=1;
        }
        List<Integer> test = gameBoard.getRemovedRows();
        int result = test.size();
        //then
        assertEquals(result, 1);
    }


}