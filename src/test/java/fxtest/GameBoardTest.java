package fxtest;

import com.example.fxtest.GameBoard1;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

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
            GameBoard1.board[0][col]=1;
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
        GameBoard1.updateScore(0);
        int result = GameBoard1.getScore();
        //then
        assertEquals(testScore,result);
    }

    @Test
    @DisplayName("getRemovedRows 확인")
    public void testGetRemovedRows(){

        //given
        for (int col = 0; col < 10; col++) {
            GameBoard1.board[0][col]=1;
        }
        List<Integer> test = gameBoard.getRemovedRows();
        int result = test.size();
        //then
        assertEquals(result, 1);
    }

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


}