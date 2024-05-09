package com.example.fxtest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SettingModel {
    // you should change public statics
    public static List<String> startKey = new ArrayList<>(); // index: "rotate", "moveLeft", "moveRight", "moveDown", "straight"
    public static int[] resolutionVal;  // index: "width", "height"
    public static int colorBlindnessVal = -1; // -1: not init, 0: X. 1: O

    private static final String[] buttonName = {"rotate", "moveLeft", "moveRight", "moveDown", "hardDrop"};




    //public static Boolean colorBlindnessVal;
    private static Properties properties;

    public static void init() {
        // if one of these is not loaded
        //if (keyVal == null || resolutionVal == null || colorBlindnessVal == -1) {
          if (properties == null) {
            try {
                FileInputStream in = new FileInputStream("src/main/resources/setting.properties");
                properties = new Properties();
                properties.load(in);
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // keyVal
            for(int i = 0; i < buttonName.length; i++){
                startKey.add(properties.getProperty(buttonName[i]));
            }

            // resolutionVal
            resolutionVal = new int[2];
            String resolution = properties.getProperty("resolution", "800x600");
            String[] dimensions = resolution.split("x");
            for(int i = 0; i < resolutionVal.length; i++) {
                resolutionVal[i] = Integer.parseInt(dimensions[i]);
            }

            // colorBlindnessVal
            colorBlindnessVal = Integer.parseInt(properties.getProperty("colorBlindness"));
        }
    }

    // save to .properties files
    public static void saveProp() {
        init(); // init 실행하면 작성 안해도 됨

        // move Val to properties
        for (int i = 0; i < 5; i++) { // keyVal
            properties.setProperty(buttonName[i], startKey.get(i));
        }
        properties.setProperty("resolution", resolutionVal[0] + "x" + resolutionVal[1]); // resolutionVal
        properties.setProperty("colorBlindness", Integer.toString(colorBlindnessVal));


        // save to .proeprties files
        try (FileOutputStream out = new FileOutputStream("src/main/resources/setting.properties")) {
            properties.store(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // change colorBlindnessVal
    public static void changeColorBlindness() {
        init(); // init 실행하면 작성 안해도 됨

        colorBlindnessVal = 1 - colorBlindnessVal;
    }

    // reset values and .properties
    public static void resetSetting() {
        init(); // init 실행하면 작성 안해도 됨

        String[] keyReset = {"UP","LEFT","RIGHT","DOWN","SPACE"};
        for(int i = 0; i < 5; i++) {
            startKey.set(i, keyReset[i]);
        }
        resolutionVal[0] = 800;
        resolutionVal[1] = 600;
        colorBlindnessVal = 0;

        // update .properties
        saveProp();
    }

    public static List<String> getStartKey() {
        return startKey;
    }
}
