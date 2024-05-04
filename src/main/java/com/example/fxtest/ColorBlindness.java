package com.example.fxtest;

import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ColorBlindness {
    public static Boolean colorBlindness;
    private static final Color[] color0 = {Color.BLACK,Color.SKYBLUE,Color.YELLOW, Color.PURPLE,
            Color.GREEN, Color.RED, Color.BLUE, Color.ORANGE}; //0: default, from 1 to 7: IOTSZJL
    private static final Color[] color1 = {Color.BLACK,Color.SKYBLUE,Color.YELLOW, Color.PALEVIOLETRED,
            Color.color(0,158/255.0,87/255.0), Color.color(213/255.0,94/255.0,0), Color.BLUE, Color.color(231/255.0,160/255.0,0)};
    private static Properties properties = new Properties();


    public static void changeColorBlindness() {
        colorBlindness = !colorBlindness;
    }

    public static void propLoad() throws IOException {
        FileInputStream in = new FileInputStream("src/main/resources/setting.properties");
        properties.load(in);
        in.close();

        int intOfBlindness = Integer.parseInt(properties.getProperty("colorBlindness", "0"));
        if(intOfBlindness ==1){ // 0: false, 1: true
            colorBlindness = true;
        }
        else {
            colorBlindness = false;
        }
    }

    public static void propSave() {
        if(colorBlindness){
            properties.setProperty("colorBlindness", "1");
        }
        else {
            properties.setProperty("colorBlindness", "0");
        }

        try (FileOutputStream out = new FileOutputStream("src/main/resources/setting.properties")) {
            properties.store(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
