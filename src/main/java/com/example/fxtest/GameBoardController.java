package com.example.fxtest;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
//시간이 좀 많이 지나고 브릭 스폰 위치가 이미 쌓여있는 board 블록과 겹치면? >> Board 늘려서 스폰 위치 따로 빼거나 스폰 자체를 바꿔야될듯
public class GameBoardController implements Initializable {
    Brick currentBrick;
    Brick nextBrick;

    BrickController brickController = new BrickController();

    boolean turnEnd = true;
    GameBoard gameBoard = new GameBoard();
    Timeline timeline;

    @FXML
    private GridPane boardView; //컨트롤View 매핑
    @FXML
    private TextField textField;
    @FXML
    private Button StartButton;


    //타임라인 시간 설정 메서드
    void setTime(float x){
        timeline = new Timeline(new KeyFrame(Duration.seconds(x), event -> {
            if(x==1.0f) {
                minute10();
            }// 1초마다 호출될 함수
            else if(x==0.8f){
                //minute8();
            }
            else{
                //minute5();
            }
        }));
    }

    //더 이상 못내려갈때 Brick 행렬에 고정
    void fixed(){
        for(Block block : currentBrick.getBlockList())
        GameBoard.board[block.getX()][block.getY()]=1;
    }


    //@FXML로 게임 시작 버튼 만들어서 이거 누르면 다시 매 1초마다 호출되는 함수 호출하여 게임 재시작
    //여기서 initialize 함수 호출

    public void colorErase(){
        for (Block block : currentBrick.getBlockList()) { // currentBrick에서 Block 배열을 가져오는 가정
            /*int x = block.getX();
            int y = block.getY();

            // GridPane에서 특정 위치의 Rectangle을 찾아 제거
            Node toRemove = null; // 제거할 Node 초기화
            for (Node child : boardView.getChildren()) {
                // GridPane에서의 Node 위치가 Block의 위치와 일치하는지 확인
                if (GridPane.getColumnIndex(child) == x && GridPane.getRowIndex(child) == y && child instanceof Rectangle) {
                    toRemove = child; // 일치하는 경우, 제거할 Node로 설정
                    break; // 찾았으므로 루프 종료
                }
            }

            if (toRemove != null) {
                boardView.getChildren().remove(toRemove); // 해당 Node 제거
            }*/

        }
    }

    public void colorFill(){
        for (Block block : currentBrick.getBlockList()) { // currentBrick에서 Block 배열을 가져오는 가정
            int x = block.getX();
            int y = block.getY();

            // 20x20 픽셀 크기의 Rectangle 생성
            Rectangle rectangle = new Rectangle(20, 20);
            rectangle.setFill(Color.BLUE); // 색상 설정, 필요에 따라 변경 가능

            // GridPane에 Rectangle 추가
            boardView.add(rectangle, y, x);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boardView.setFocusTraversable(true);
        currentBrick=new BrickZ(1,4);
        //nextBrick 랜덤에서 뽑아오기(임시로)
        nextBrick=new BrickZ(1,4);

        setTime(1.0f);
        timeline.setCycleCount(Timeline.INDEFINITE); // 무한 반복 설정
        timeline.play(); // Timeline 시작
    }


    //Score 컨트롤 혹은 deleteLine 컨트롤이 일정 int값 이상이면 속도 빨라짐(1초 > 0.8초 > 0.5초). 속성감시 이벤트 리스너
    //이 이벤트 리스너는 값에 맞게 현재 호출되는 정기 실행 함수를 멈추고 매 0.8 혹은 0.5마다 호출되는 함수를 여기서 호출한다.




    //어느 한계 선 이상이 되면 끝인 리스너, GameOverFlag=true로



    //매 1초마다 호출되는 함수
    //더 이상 내려갈 수 없는지 확인 + 없다면 거기 위치에 Brick 박고 nextBrick을 currentBrick으로 넘기고 nextBrick 랜덤으로 뽑아오고
    //키 입력 시 위치 변경
    //줄 꽉차면 없애고 그 윗줄 내리고
    //어느 한계 선 이상이 되면 끝인지 매초 확인하고 맞으면 종료
    private void minute10(){
        if(turnEnd){

            //nextBrick을 currentBrick으로 옮김.
            currentBrick=nextBrick;

            //nextBrick 랜덤 뽑아와서 세팅(일단 동일한 brick으로 세팅)
            nextBrick=new BrickZ(1, 4);

            //currentBrick 색칠하고
            colorFill();

            //이벤트 장착


            //다시 turn시작
            turnEnd=false;

        }
        else{
            if(!currentBrick.canMoveDown()/*!canMoveDown()*/){
                //턴 종료
                turnEnd=true;
                //그 위치에 색칠
                colorFill();
                fixed();
            }
            else {
                //지우고 moveD() 호출하고 색칠하기
                colorErase();
                currentBrick.moveD();
                colorFill();
            }
        }
    }



    //매 0.8초마다 호출되는 함수
    //더 이상 내려갈 수 없는지 확인 + 없다면 거기 위치에 Brick 박고 nextBrick을 currentBrick으로 넘기고 nextBrick 랜덤으로 뽑아오고
/*    private void minute8(){
        if(*//*턴 종료*//*){
            *//*
            nextBrick을 currentBrick으로 옮기면서 중심 위치 초기화
            nextBrick 랜덤 뽑아와서 세팅
            currentBrick 색칠하고 이벤트 장착
            flag=false 로 초기화

            *//*
        }
        else{
            if(*//*!canMoveDown()*//*){
                //flag를 true로 하고
                //그 위치에 색칠
            }
            else {
                //moveD() 호출하고 색칠하기
            }
        }
    }*/

    //0.5초마다 호출되는 함수
    //더 이상 내려갈 수 없는지 확인 + 없다면 거기 위치에 Brick 박고 nextBrick을 currentBrick으로 넘기고 nextBrick 랜덤으로 뽑아오고
/*    private void minute5(){
        if(*//*턴 종료*//*){
            *//*
            nextBrick을 currentBrick으로 옮기면서 중심 위치 초기화
            nextBrick 랜덤 뽑아와서 세팅
            currentBrick 색칠하고 이벤트 장착
            flag=false 로 초기화

            *//*
        }
        else{
            if(*//*!canMoveDown()*//*){
                //flag를 true로 하고
                //그 위치에 색칠
            }
            else {
                //moveD() 호출하고 색칠하기
            }
        }
    }*/

}
