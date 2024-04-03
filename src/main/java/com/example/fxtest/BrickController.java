package com.example.fxtest;

import com.example.fxtest.brick.Brick;

//키 입력 시 발생되는 이벤트 처리
public class BrickController{

    private String MOVED;
    private String MOVEL;
    private String MOVER;
    private String ROTATE;

    //수직떨어지기 추가해야됨


    //싱글톤
    private static BrickController brickController = new BrickController();

    public static BrickController getBrickController() {
        return brickController;
    }

    private BrickController(){
        SettingController.getKey(); //키 값들 StartKey 리스트로 가져오기
        MOVED=SettingController.startKey.get(0);
        MOVEL=SettingController.startKey.get(1);
        MOVER=SettingController.startKey.get(2);
        ROTATE=SettingController.startKey.get(3);
        //수직떨어지기 추가해야됨
    }
    //싱글톤


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

}
