package fxtest;

import com.example.fxtest.ScoreboardController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ScoreboardControllerTest  {

    ScoreboardController scoreboardController = new ScoreboardController();
    @Test
    void getText() {
        ScoreboardController.difficulty=0;
        String test = ScoreboardController.getText();
        Assertions.assertEquals(test,"easy");
    }
}