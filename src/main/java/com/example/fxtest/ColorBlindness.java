package com.example.fxtest;

import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ColorBlindness {
    private static final Color[] color0 = {Color.BLACK,Color.SKYBLUE,Color.YELLOW, Color.PURPLE,
            Color.GREEN, Color.RED, Color.BLUE, Color.ORANGE}; //0: default, from 1 to 7: IOTSZJL
    private static final Color[] color1 = {Color.BLACK,Color.SKYBLUE,Color.YELLOW, Color.PALEVIOLETRED,
            Color.color(0,158/255.0,87/255.0), Color.color(213/255.0,94/255.0,0), Color.BLUE, Color.color(231/255.0,160/255.0,0)};
}
