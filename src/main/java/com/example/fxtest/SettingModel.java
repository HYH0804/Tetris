package com.example.fxtest;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SettingModel {

    // you should change public statics
    public static List<String> keyVal = new ArrayList<>(); // index: "rotate", "moveLeft", "moveRight", "moveDown", "straight" * 2

    public static int[] resolutionVal;  // index: "width", "height"
    public static int colorBlindnessVal = -1; // -1: not init, 0: X. 1: O

    private static final String[] buttonName = {"rotate", "moveLeft", "moveRight", "moveDown", "hardDrop"};

    public static final String SETTINGS_FILE = System.getProperty("user.home") + File.separator + "score" + File.separator + "setting.properties";



    //public static Boolean colorBlindnessVal;
    private static Properties properties;

    public static void init() {
        // if one of these is not loaded
        //if (keyVal == null || resolutionVal == null || colorBlindnessVal == -1) {
          if (properties == null) {

              /*try {
                FileInputStream in = new FileInputStream("src/main/resources/setting.properties");
                properties = new Properties();
                properties.load(in);
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }*/
              File settingsFile = new File(SETTINGS_FILE);
              File settingsDir = settingsFile.getParentFile();
              if (!settingsDir.exists()) {
                  settingsDir.mkdirs();
              }
              if (!settingsFile.exists()) {
                  try (InputStream in = Main.class.getClassLoader().getResourceAsStream("setting.properties")) {
                      if (in == null) {
                          throw new RuntimeException("Default settings file not found in JAR");
                      }
                      Files.copy(in, settingsFile.toPath());
                      System.out.println("Default settings file copied to " + SETTINGS_FILE);
                  } catch (IOException e) {
                      throw new RuntimeException("Failed to create default settings file", e);
                  }
              }
              try {
                  FileInputStream in = new FileInputStream(SETTINGS_FILE);
                  properties = new Properties();
                  properties.load(in);
                  in.close();
              } catch (IOException e) {
                  throw new RuntimeException(e);
              }
            // keyVal
            for(int i = 0; i < buttonName.length; i++){
                keyVal.add(properties.getProperty(buttonName[i] + "1"));
            }
            // 2P
              for(int i = 0; i < buttonName.length; i++){
                  keyVal.add(properties.getProperty(buttonName[i] + "2"));
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
            properties.setProperty(buttonName[i]+"1", keyVal.get(i));
        }
        // 2P
        for (int i = 0; i < 5; i++) { // keyVal
            properties.setProperty(buttonName[i]+"2", keyVal.get(i+5));
        }
        properties.setProperty("resolution", resolutionVal[0] + "x" + resolutionVal[1]); // resolutionVal
        properties.setProperty("colorBlindness", Integer.toString(colorBlindnessVal));


        // save to .proeprties files
        try (FileOutputStream out = new FileOutputStream(SETTINGS_FILE)) {
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

        // create new properties
        properties = new Properties();

        String[] keyReset = {"UP","LEFT","RIGHT","DOWN","SPACE", "W", "A", "D", "S", "X"};
        for(int i = 0; i < 10; i++) {
            keyVal.set(i, keyReset[i]);
        }
        resolutionVal[0] = 800;
        resolutionVal[1] = 600;
        colorBlindnessVal = 0;

        // update .properties
        saveProp();
    }



    // index: "rotate", "moveLeft", "moveRight", "moveDown", "straight" // index: "width", "height" // -1: not init, 0: X. 1: O
    public static String getRotate1() {
        init();
        return keyVal.get(0);
    }

    public static String getMoveL1() {
        init();
        return keyVal.get(1);
    }

    public static String getMoveR1() {
        init();
        return keyVal.get(2);
    }

    public static String getMoveD1() {
        init();
        return keyVal.get(3);
    }

    public static String getHardDrop1() {
        init();
        return keyVal.get(4);
    }

    public static String getRotate2() {
        init();
        return keyVal.get(5);
    }

    public static String getMoveL2() {
        init();
        return keyVal.get(6);
    }

    public static String getMoveR2() {
        init();
        return keyVal.get(7);
    }

    public static String getMoveD2() {
        init();
        return keyVal.get(8);
    }

    public static String getHardDrop2() {
        init();
        return keyVal.get(9);
    }

    public static int getWidth() {
        init();
        return resolutionVal[0];
    }

    public static int getHeight() {
        init();
        return resolutionVal[1];
    }
    public static int getColorBlindnessVal() {
        init();
        return colorBlindnessVal;
    }

}
