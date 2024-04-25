package fxtest;

import com.example.fxtest.ScoreboardController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
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