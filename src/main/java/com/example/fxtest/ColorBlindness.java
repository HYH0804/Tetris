package com.example.fxtest;

import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ColorBlindness {
    public static Boolean colorBlindness;
    private static final Color[] color0 = {Color.BLACK,Color.SKYBLUE,Color.YELLOW, Color.PURPLE,
            Color.GREEN, Color.RED, Color.BLUE, Color.ORANGE}; //0: default, from 1 to 7: IOTSZJL
    private static final Color[] color1 = {Color.BLACK,Color.SKYBLUE,Color.YELLOW, Color.PALEVIOLETRED,
            Color.color(0,158/255.0,87/255.0), Color.color(213/255.0,94/255.0,0), Color.BLUE, Color.color(231/255.0,160/255.0,0)};

    public static Color getColor(int index) throws IOException {
        if(colorBlindness == null){
            propColorBlindness();
        }
        if(colorBlindness){
            return color1[index];
        }
        else {
            return color0[index];
        }
    }

    public static void changeColorBlindness() throws IOException {
        if(colorBlindness == null){
            propColorBlindness();
        }
        colorBlindness = !colorBlindness;
    }

    public static void propColorBlindness() throws IOException {
        Properties properties = new Properties();
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
}
