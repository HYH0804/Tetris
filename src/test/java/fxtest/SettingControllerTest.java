package fxtest;

import com.example.fxtest.SettingController;
import org.junit.jupiter.api.Test;

public class SettingControllerTest {

    SettingController settingController = new SettingController();

    @Test
    void testOnResetSettingsButton() {
        settingController.onResetScoreBButton();
    }
}
