package com.example.fxtest;

import com.example.fxtest.brick.Brick;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//키 입력 시 발생되는 이벤트 처리
public class BrickController{

    private String MOVED;
    private String MOVEL;
    private String MOVER;
    private String ROTATE;

    //수직떨어지기 추가해야됨
    private String HARDDROP;



    //싱글톤
    private static BrickController brickController = new BrickController();

    public static BrickController getBrickController() {
        return brickController;
    }

    private BrickController(){
        // SettingController.getKey(); //키 값들 StartKey 리스트로 가져오기
        MOVED= SettingModel.startKey.get(0);
        MOVEL= SettingModel.startKey.get(1);
        MOVER= SettingModel.startKey.get(2);
        ROTATE= SettingModel.startKey.get(3);
        HARDDROP= SettingModel.startKey.get(4);
    }
    //싱글톤

    public void updateBrickController(){
        //setting.properties에서 값 가져와서 MOVE에 넣기
        // Properties 객체 생성
        Properties settingProperties = new Properties();
        try {
            // setting.properties 파일 로드
            FileInputStream in = new FileInputStream("src/main/resources/setting.properties");
            settingProperties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ROTATE=settingProperties.getProperty("rotate");
        MOVER=settingProperties.getProperty("moveRight");
        MOVEL=settingProperties.getProperty("moveLeft");
        HARDDROP=settingProperties.getProperty("hardDrop");
        MOVED=settingProperties.getProperty("moveDown");
    }



    //moveR 이벤트
    public void moveR(Brick brick) {
        if(brick.canMoveRight()){
            System.out.println("이동가능");
            brick.moveR();
            return;
        }
        System.out.println("이동불가능");
    }

    //moveL 이벤트
    public void moveL(Brick brick){
        if(brick.canMoveLeft()){
            System.out.println("이동가능");
            brick.moveL();
            return;
        }
        System.out.println("이동불가능");
    }

    //moveD 이벤트
    public void moveD(Brick brick){
        if(brick.canMoveDown()){
            System.out.println("이동가능");
            brick.moveD();
            return;
        }
        System.out.println("이동불가능");
    }

    //회전 이벤트
    public void rotate(Brick brick){
        if(brick.canRotate()){
            System.out.println("이동가능");
            brick.rotate();
            return;
        }
        System.out.println("이동불가능");
    }

    public void straightD(Brick brick){
        brick.straightD();
    }

    public String getMOVED() {
        return MOVED;
    }

    public String getMOVEL() {
        return MOVEL;
    }

    public String getMOVER() {
        return MOVER;
    }

    public String getROTATE() {
        return ROTATE;
    }
    public String getSTRAIGHT(){
        return HARDDROP;
    }

    public void setMOVED(String MOVED) {
        this.MOVED = MOVED;
    }

    public void setMOVEL(String MOVEL) {
        this.MOVEL = MOVEL;
    }

    public void setMOVER(String MOVER) {
        this.MOVER = MOVER;
    }

    public void setROTATE(String ROTATE) {
        this.ROTATE = ROTATE;
    }

    public void setHARDDROP(String HARDDROP) {
        this.HARDDROP = HARDDROP;
    }
}
