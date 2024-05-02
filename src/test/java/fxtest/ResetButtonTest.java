package fxtest;

import com.example.fxtest.SettingController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResetButtonTest {

    private SettingController settingController;

    @BeforeEach
    public void setUp() {
        settingController = new SettingController();
    }

    @AfterEach
    public void tearDown() {
        settingController = null;
    }

    @Test
    public void testResetScoreFiles() {
        try {
            settingController.onResetScoreBButton();
            String[] difficulty = {"easy", "normal", "hard"};
            for (String diff : difficulty) {
                String scoreFilePath = "src/main/resources/score/" + diff + ".txt";
                String itemFilePath = "src/main/resources/score/" + diff + "(item).txt";

                // Check if the score file exists and contains correct data
                BufferedReader scoreReader = new BufferedReader(new FileReader(scoreFilePath));
                for (int i = 0; i < 10; i++) {
                    assertEquals(" 0", scoreReader.readLine());
                }
                scoreReader.close();

                // Check if the item file exists and contains correct data
                BufferedReader itemReader = new BufferedReader(new FileReader(itemFilePath));
                for (int i = 0; i < 10; i++) {
                    assertEquals(" 0", itemReader.readLine());
                }
                itemReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            assertTrue(false, "IOException occurred");
        }
    }
}