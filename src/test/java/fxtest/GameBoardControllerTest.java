package fxtest;

import com.example.fxtest.GameBoardController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameBoardControllerTest {

    GameBoardController gameBoardController = new GameBoardController();

    @Test
    void testChange() {
        double v1 = gameBoardController.changeSpeed(0, 1);
        double v2 = gameBoardController.changeSpeed(1, 1);
        double v3 = gameBoardController.changeSpeed(2, 1);
        Assertions.assertEquals(v1,0.92);
        Assertions.assertEquals(v2,0.9);
        Assertions.assertEquals(v3,0.88);
    }

    @Test
    void setOption(){
        GameBoardController.setOptions(0,true);
        Assertions.assertEquals(0, GameBoardController.difficulty);
        Assertions.assertEquals(true, GameBoardController.itemMode);
    }

    @Test
    void fixed(){
        gameBoardController.fixed();
    }

    @Test
    void isGameOver(){
        boolean gameOver = gameBoardController.isGameOver();
        Assertions.assertFalse(gameOver);
    }


}
