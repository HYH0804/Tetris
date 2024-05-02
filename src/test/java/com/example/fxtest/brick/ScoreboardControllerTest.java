package com.example.fxtest.brick;

import com.example.fxtest.ScoreboardController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ScoreboardControllerTest  {

    ScoreboardController scoreboardController = new ScoreboardController();
    @Test
    void getText() {
        ScoreboardController.difficulty=0;
        String test = ScoreboardController.getText();
        ScoreboardController.difficulty=1;
        String test1 = ScoreboardController.getText();
        ScoreboardController.difficulty=2;
        String test2 = ScoreboardController.getText();

        Assertions.assertEquals(test,"easy");
        Assertions.assertEquals(test1,"normal");
        Assertions.assertEquals(test2,"hard");
    }

    @Test
    void getRanking() {
        ScoreboardController.getRanking();
    }

    @Test
    void writeRanking() {
        ScoreboardController.writeRanking();
    }

}