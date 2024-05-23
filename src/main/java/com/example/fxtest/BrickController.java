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


    public BrickController(String rotate, String moveL, String moveR, String moveD, String hardDrop){
        // SettingController.getKey(); //키 값들 StartKey 리스트로 가져오기
        SettingModel.init();
        this.ROTATE= rotate;
        this.MOVEL= moveL;
        this.MOVER= moveR;
        this.MOVED= moveD;
        this.HARDDROP= hardDrop;
    }

    //moveR 이벤트
    public void moveR(Brick brick) {
        if(brick.canMoveRight()){
            System.out.println("R 이동가능");
            brick.moveR();
            return;
        }
        System.out.println("R 이동불가능");
    }

    //moveL 이벤트
    public void moveL(Brick brick){
        if(brick.canMoveLeft()){
            System.out.println("L 이동가능");
            brick.moveL();
            return;
        }
        System.out.println("L 이동불가능");
    }

    //moveD 이벤트
    public void moveD(Brick brick){
        if(brick.canMoveDown()){
            System.out.println("D 이동가능");
            brick.moveD();
            return;
        }
        System.out.println("D 이동불가능");
    }

    //회전 이벤트
    public void rotate(Brick brick){
        if(brick.canRotate()){
            System.out.println("회전 이동가능");
            brick.rotate();
            return;
        }
        System.out.println("회전 이동불가능");
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

}
