package com.example.fxtest;


import com.example.fxtest.brick.Block;

//얜 임시로, 랜덤 구현하면 필요 없
import com.example.fxtest.brick.BrickZ;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import com.example.fxtest.brick.Brick;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
//시간이 좀 많이 지나고 브릭 스폰 위치가 이미 쌓여있는 board 블록과 겹치면? >> Board 늘려서 스폰 위치 따로 빼거나 스폰 자체를 바꿔야될듯
public class GameBoardController implements Initializable {
    Brick currentBrick;
    Brick nextBrick;

    BrickController brickController;

    GameBoard gameBoard = new GameBoard();

    Timeline timeline;

    @FXML
    public GridPane boardView; //컨트롤View 매핑

    @FXML
    private Button StartButton;
    @FXML
    private Button ExitButton;

    //주기함수 종료하고 다시 처음 페이지로
    @FXML
    public void onStartButtonClick() throws IOException{
        Stage st = StageSaver.pStage;
        FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("Start.fxml"));
        Scene mainpage = new Scene(fxmlLoader.load(), 320, 240);
        st.setScene(mainpage);
        st.show();
    }

    //타임라인 시간 설정 메서드
    void setTime(double x){

        timeline = new Timeline(new KeyFrame(Duration.seconds(x), event -> {
            minute10(); //x초만큼의 속도
        }));


    }

    //더 이상 못내려갈때 Brick 행렬에 고정
    void fixed(){
        for(Block block : currentBrick.getBlockList())
        GameBoard.board[block.getX()][block.getY()]=1;
    }

    //게임 (재)시작때 초기화
    void init(){
        initBoard();
        GameBoard.score=0;
        GameBoard.deleteLine=0;
        GameBoard.whileGame =false;
        timeline.stop();
        regiBrickEvent();
        System.out.println("초기화완료");
    }
    
    void initBoard(){
        for (int[] row : GameBoard.board) {
            Arrays.fill(row, 0);
        }
    }


    //@FXML로 게임 시작 버튼 만들어서 이거 누르면 다시 매 1초마다 호출되는 함수 호출하여 게임 재시작
    //여기서 initialize 함수 호출
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boardView.setFocusTraversable(true);
        currentBrick=new BrickZ(0,4);
        //nextBrick 랜덤에서 뽑아오기(임시로)
        nextBrick=new BrickZ(0,4);
        brickController = BrickController.getBrickController(); //키 값 전부 field에 세팅
        // GridPane에 키 이벤트 핸들러 등록
        regiBrickEvent();
        Drawing.setBoardView(boardView);


        // startButton의 클릭 이벤트 핸들러 등록
        StartButton.setOnAction(event -> {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // 새로운 Scene을 로드합니다.
            try {
                Parent root = FXMLLoader.load(getClass().getResource("GameBoard.fxml"));
                Scene scene = new Scene(root);
                timeline.stop(); //주기함수 종료
                // Stage에 새로운 Scene을 설정합니다.
                stage.setScene(scene);
                init(); //새로 시작 전 board 0으로 초기화
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        ExitButton.setOnAction(event -> {
            // 현재 스테이지를 가져옴
            Stage stage = (Stage) ExitButton.getScene().getWindow();
            // 어플리케이션 종료
            stage.close();
        });

        setTime(1.0);
        timeline.setCycleCount(Timeline.INDEFINITE); // 무한 반복 설정
        timeline.play(); // Timeline 시작
    }


    //Score 컨트롤 혹은 deleteLine 컨트롤이 일정 int값 이상이면 속도 빨라짐(1초 > 0.8초 > 0.5초). 속성감시 이벤트 리스너
    //이 이벤트 리스너는 값에 맞게 현재 호출되는 정기 실행 함수를 멈추고 매 0.8 혹은 0.5마다 호출되는 함수를 여기서 호출한다.




    //어느 한계 선 이상(1번째 행에 1이 하나라도 들어오면) 이 되면 끝인 리스너, GameOverFlag=true로



    //매 1초마다 호출되는 함수
    //더 이상 내려갈 수 없는지 확인 + 없다면 거기 위치에 Brick 박고 nextBrick을 currentBrick으로 넘기고 nextBrick 랜덤으로 뽑아오고
    //키 입력 시 위치 변경
    //줄 꽉차면 없애고 그 윗줄 내리고
    //어느 한계 선 이상이 되면 끝인지 매초 확인하고 맞으면 종료
    //아이템은 총 2가지 케이스 >> (1) 떨어지면 바로 작동 (2) 줄 삭제가 되어야 작동
    private void minute10(){
        if(!GameBoard.whileGame){

            //nextBrick을 currentBrick으로 옮김. + 색칠 + 이벤트 장착
            sponBrick();

            //게임 중으로 바꿈
            GameBoard.whileGame =true;


            //테스트
            //printMatrix();

        }
        else{
            if(!currentBrick.canMoveDown()/*!canMoveDown()*/){ //더 못내려가면
                //그 위치에 색칠
                //colorFill();
                Drawing.colorFill(currentBrick);
                fixed();

                /*if(currentBrick.isItem?) {
                    //(1) 케이스 아이템 있으면 해당 로직 먼저 수행
                }*/

                //gravity로 1인지 확인해서 board 업데이트하고
                Drawing.updateBoardView();
                //줄 지우기


                //겜 끝났는지 확인
                if(isGameOver()){
                    //스코어보드 처리

                    System.out.println("게임종료");
                    //전부 초기화
                    init();

                    //테스트
                    //printMatrix();
                }
                else{
                    //nextBrick을 currentBrick으로 옮김. + 색칠 + 이벤트 장착
                    sponBrick();

                    //테스트
                    //printMatrix();
                }

            }
            else {
                //지우고 moveD() 호출하고 색칠하기
                Drawing.colorErase(currentBrick);
                currentBrick.moveD();
                Drawing.colorFill(currentBrick);

                //테스트
                //printMatrix();
            }
        }
    }

    private void sponBrick() {
        //nextBrick을 currentBrick으로 옮김.
        currentBrick=nextBrick;

        //nextBrick 랜덤 뽑아와서 세팅(일단 동일한 brick으로 세팅)
        nextBrick=new BrickZ(0, 4);

        //currentBrick 색칠하고
        Drawing.colorFill(currentBrick);

        //이벤트 장착
    }

    public boolean isGameOver() {
        for(int i=0;i<GameBoard.WIDTH;i++){
            if(GameBoard.board[1][i]==1){
                GameBoard.whileGame =true;
                return true;
            }
        }
        return false;
    }

    //2차원배열 출력 테스트함수
    public void printMatrix(){
        for(int i=0; i<GameBoard.HEIGHT; i++){
            for(int j=0; j<GameBoard.WIDTH; j++){
                System.out.printf("%d ",GameBoard.board[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------END-------");
    }

    public void printBlock(){
        for(Block block : currentBrick.getBlockList()){
            System.out.println("x값: " + block.getX() + " y 값: "+block.getY());
        }
    }

    private void regiBrickEvent() {
        boardView.setOnKeyPressed(event -> {
            Drawing.colorErase(currentBrick);
            String keyValue = event.getCode().toString();
            if (keyValue.equals(brickController.getMOVER()) || keyValue.toUpperCase().equals(brickController.getMOVER())) {
                // 오른쪽 이동 키가 눌렸을 때의 동작
                System.out.println("Right key pressed");
                brickController.moveR(currentBrick);
                printBlock();
            } else if (keyValue.equals(brickController.getMOVEL()) || keyValue.toUpperCase().equals(brickController.getMOVEL())) {
                // 왼쪽 이동 키가 눌렸을 때의 동작
                System.out.println("Left key pressed");
                brickController.moveL(currentBrick);
                printBlock();
            } else if (keyValue.equals(brickController.getMOVED()) || keyValue.toUpperCase().equals(brickController.getMOVED())) {
                // 아래 이동 키가 눌렸을 때의 동작
                brickController.moveD(currentBrick);
                printBlock();
            } else if (keyValue.equals(brickController.getROTATE()) || keyValue.toUpperCase().equals(brickController.getROTATE())) {
                // 회전 키가 눌렸을 때의 동작
                System.out.println("Rotate key pressed");
                brickController.rotate(currentBrick);
                printBlock();
            } /*else if(){
                //여기는 수직떨구기
                System.out.println("수직떨구기");
            }*/
            event.consume();
            Drawing.colorFill(currentBrick); //색칠하고
        });
    }



}
