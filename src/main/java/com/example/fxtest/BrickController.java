package com.example.fxtest;

import com.example.fxtest.brick.Brick;

//키 입력 시 발생되는 이벤트 처리
public class BrickController{

    private String MOVED;
    private String MOVEL;
    private String MOVER;
    private String ROTATE;

    //수직떨어지기 추가해야됨
    


    private static BrickController brickController = new BrickController();

    public BrickController(){
        SettingController.getKey(); //키 값들 StartKey 리스트로 가져오기
        MOVED=SettingController.startKey.get(0);
        MOVEL=SettingController.startKey.get(1);
        MOVER=SettingController.startKey.get(2);
        ROTATE=SettingController.startKey.get(3);
        //수직떨어지기 추가해야됨
    }


    //그림그리기 로직:
    //currentBrick의 각 Block 값 좌표에 해당하는 Grid 위치 색 지우고
    //움직이거나 회전하거나 1초동안 자동으로 내려간 후
    //이동 후 currentBrick 위치값 색 채우기


    //moveR 이벤트
    public void moveR(Brick brick) {
        if(brick.canMoveRight()){
            brick.moveR();
        }
    }

    //moveL 이벤트
    public void moveL(Brick brick){
        if(brick.canMoveLeft()){
            brick.moveL();
        }
    }

    //moveD 이벤트
    public void moveD(Brick brick){
        if(brick.canMoveDown()){
            brick.moveD();
        }
    }

    //회전 이벤트
    public void rotate(Brick brick){
        if(brick.canRotate()){
            brick.rotate();
        }
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
