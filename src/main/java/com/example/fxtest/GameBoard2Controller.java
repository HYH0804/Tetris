package com.example.fxtest;

import com.example.fxtest.brick.Block;
import com.example.fxtest.brick.Brick;
import com.example.fxtest.brick.Item;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import static com.example.fxtest.Drawing.displayNextBrick;
import static com.example.fxtest.Main.loadProperties;

//시간이 좀 많이 지나고 브릭 스폰 위치가 이미 쌓여있는 board 블록과 겹치면? >> Board 늘려서 스폰 위치 따로 빼거나 스폰 자체를 바꿔야될듯
public class GameBoard2Controller implements Initializable {

    Brick currentBrick;
    Brick nextBrick;
    Brick currentBrick2;
    Brick nextBrick2;

    BrickController brickController;
    BrickController brickController2;

    GameBoard1 gameBoard = new GameBoard1();
    GameBoard1 gameBoard2 = new GameBoard1();

    Timeline timeline;
    Timeline timeline2;

    RandomGenerator rg = new RandomGenerator();


    @FXML
    public GridPane boardView; //컨트롤View 매핑
    @FXML
    public GridPane boardView2; //컨트롤View 매핑

    @FXML
    private Label scoreLabel;
    @FXML
    private Label scoreLabel2;
    @FXML
    private GridPane nextBrickView;
    @FXML
    private GridPane nextBrickView2;


    public static double cellWidth = 20;
    public static double cellHeight = 20;


    //SettingModel에서 받아와야 되는 것들
    boolean colorBlindness=true; //색맹모드

    //난이도, 아이템 모드 확인
    public static int difficulty; //난이도
    public static boolean itemMode; //이거 setter로 받아야됨

    //타임라인 그 시간으로 시작
    void startTimeLine(double x){
        timeline.stop();
        System.out.println("startTimeLine실행, double x : " + x);
        setTime(x);
        timeline.setCycleCount(Timeline.INDEFINITE); // 무한 반복 설정
        timeline.play(); // Timeline 시작
    }



    //타임라인 시간 설정 메서드
    void setTime(double x){

        timeline = new Timeline(
                new KeyFrame(Duration.seconds(x), event -> {

                    minute10(); //x초만큼의 속도
                }));
    }

    void setTime2(double x){

        timeline2 = new Timeline(
                new KeyFrame(Duration.seconds(x), event -> {

                    minute10_2(); //x초만큼의 속도
                }));
    }



    //더 이상 못내려갈때 Brick 행렬에 고정 , 노말 블록이면 1 , 아이템 블록이면 그 아이템 숫자 고정
    public void fixed(GameBoard1 gameBoard,Brick currentBrick){
        System.out.println(" _______________currentBrick" + currentBrick);
        if(currentBrick==null){
            return;
        }
        if(currentBrick.getBlockList()==null){
            System.out.println("getBlockList가 null임");
        }
        else{
            System.out.println("null 아님");
        }
        for(Block block : currentBrick.getBlockList())
            gameBoard.board[block.getX()][block.getY()]=block.getItem().getNum();
    }



    //뷰 바꿔서 겜 시작이 아닌 그 뷰에서 그대로 게임 (재)시작때 초기화
    void init(){
        gameBoard=new GameBoard1();
        gameBoard2=new GameBoard1();

        //currentBrick=rg.genarateNormal(0,colorBlindness,gameBoard); //일단 이지로, 여기서 모드 받아와야됨.
        //currentBrick2=rg.genarateNormal(0,colorBlindness, gameBoard2);
        nextBrick=rg.genarateNormal(0,colorBlindness, gameBoard);
        nextBrick2=rg.genarateNormal(0,colorBlindness, gameBoard2);

        colorBlindness=getColorBliness();
        System.out.println("초기화완료");

    }

    void destroy(){
        timeline.stop();
        timeline2.stop();
        boardView.setOnKeyPressed(null);
        boardView2.setOnKeyPressed(null);
        System.out.println("초기화완료");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boardView.setFocusTraversable(true);
        boardView2.setFocusTraversable(true);
        //difficulty, 색맹여부 받아오는 ,



        init();


        currentBrick=rg.genarateNormal(0,colorBlindness,gameBoard); //일단 이지로, 여기서 모드 받아와야됨.
        //currentBrick= new BrickI(0,4,Color.GREEN );
        //currentBrick = new BrickO(0,4,Color.SKYBLUE);
        currentBrick2=rg.genarateNormal(0,colorBlindness, gameBoard2);

        nextBrick=rg.genarateNormal(0,colorBlindness, gameBoard);
        //nextBrick=new BrickI(0,4,Color.GREEN );
        //nextBrick = new BrickO(0,4,Color.SKYBLUE);
        nextBrick2=rg.genarateNormal(0,colorBlindness, gameBoard2);

        brickController = BrickController.getBrickController(); //키 값 전부 field에 세팅
        //brickController2 = BrickController.getBrickController();
        // GridPane에 키 이벤트 핸들러 등록
        regiBrickEvent(currentBrick2,boardView2,gameBoard2);
        //보드2도 등록해야됨


        gameBoard.scoreProperty().addListener((obs, oldScore, newScore) -> {
            if (newScore.intValue() > oldScore.intValue()) {
                updateScoreLabel(scoreLabel,gameBoard);
            }
        });

        gameBoard2.scoreProperty().addListener((obs, oldScore, newScore) -> {
            if (newScore.intValue() > oldScore.intValue()) {
                updateScoreLabel(scoreLabel2,gameBoard2);
            }
        });

        displayNextBrick(nextBrick,nextBrickView);
        displayNextBrick(nextBrick2,nextBrickView2);

        //change()함수 실행
        try {
            change(boardView);
            change(boardView2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setTime(1.0);
        setTime2(1.0);
        timeline.setCycleCount(Timeline.INDEFINITE); // 무한 반복 설정
        timeline.play(); // Timeline 시작
        timeline2.setCycleCount(Timeline.INDEFINITE); // 무한 반복 설정
        timeline2.play(); // Timeline 시작
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
        Drawing.colorErase(currentBrick,boardView);
/*      System.out.println(difficulty+">> diff");
        System.out.println(itemMode +">> itemMode");*/
        //printBlock();
        if(!gameBoard.whileGame){
            gameBoard.downScore=1;
            System.out.println("겜 자체 시작");
            //nextBrick을 currentBrick으로 옮김. + 색칠 + 이벤트 장착
            sponBrick(gameBoard,boardView, nextBrickView,1);

            //게임 중으로 바꿈
            gameBoard.whileGame =true;


            //테스트
            //printMatrix();

        }
        else{
            //착지시(아이템)
 /*           if(turnEnd==true){

                Item.turnEndDoItem(currentBrick, gameBoard, boardView); //아이템 , 하드드롭했을때
                turnEnd=false;
            }*/
            if(!currentBrick.canMoveDown()/*!canMoveDown()*/){ //더 못내려가면
                //그 위치에 색칠
                //colorFill();

                gameBoard.turnEnd=true;
                nextBrickView.setVisible(true);

                Drawing.colorFill(currentBrick,boardView);
                fixed(gameBoard,currentBrick);
                //아이템 기능을 빼고 아무슨아이템이냐 받고 호출
                //Block Item
                System.out.println("!currentBrick.canMoveDown()");

                //착지시(아이템) , 살포시 안착했을때
                Item.turnEndDoItem(currentBrick, gameBoard, boardView); //아이템

                /*if(currentBrick.isItem?) {
                    //(1) 케이스 아이템 있으면 해당 로직 먼저 수행
                }*/

                System.out.println("*************보드1************");
                printMatrix(gameBoard);


                //먼저 삭제되는 로우 가져와서 거기에 아이템 있는지 확인(아이템)
                List<Integer> removedRows = gameBoard.getRemovedRows(); //삭제 전에 우선 삭제되는 라인 먼저 확인

                //보드 전부 0
                checkAndDoItem6(removedRows,gameBoard,boardView);



                //NPE조심
                Drawing.updateBoardView(removedRows,boardView, gameBoard.board); //gui 여기서 삭제
                gameBoard.removeFullRows(); //배열에서 삭제 후 점수 업뎃

                //System.out.println("완성 줄 삭제 후---------------");
                //printMatrix();
                //gravity로 1인지 확인해서 board 업데이트하고

                //Drawing.updateBoardView(removeLineList);
                //printMatrix(gameBoard);
                //줄 지우기


                //겜 끝났는지 확인
                if(isGameOver(gameBoard)){
                    //스코어보드 처리

                    System.out.println("--------------------GameOver");
                    //전부 초기화
                    destroy();

                    //테스트
                    //printMatrix();
                }
                else{
                    Item.sponDoItem(currentBrick, gameBoard, nextBrickView);

                    gameBoard.turnEnd=false;
                    //nextBrick을 currentBrick으로 옮김. + 색칠 + 이벤트 장착
                    sponBrick(gameBoard,boardView,nextBrickView,1);
                    chageTime(gameBoard);
                    //스폰되자마자 블록 아이템 수행
                    //Item.sponDoItem(currentBrick, gameBoard, boardView);

                    System.out.println("겜은 안끝났지만 내려갈 곳 없어서 블록 스폰"+ gameBoard.blockSpon);
                    //테스트
                    //printMatrix();
                }

            }
            else {
                //지우고 moveD() 호출하고 색칠하기
                Drawing.colorErase(currentBrick,boardView);
                //System.out.println("---------------");
                currentBrick.moveD();
                //printBlock(currentBrick);
                Drawing.colorFill(currentBrick,boardView);

                //테스트
                //printMatrix();
            }
            if(gameBoard.turnEnd==true){

                Item.turnEndDoItem(currentBrick, gameBoard, boardView); //아이템 , 하드드롭했을때
               gameBoard.turnEnd=false;
            }
        }
    }

        private void minute10_2(){
        Drawing.colorErase(currentBrick2,boardView2);
/*        System.out.println(difficulty+">> diff");
        System.out.println(itemMode +">> itemMode");*/
        //printBlock();
        if(!gameBoard2.whileGame){
            gameBoard2.downScore=1;
            System.out.println("겜 자체 시작");
            //nextBrick을 currentBrick으로 옮김. + 색칠 + 이벤트 장착
            sponBrick(gameBoard2,boardView2,nextBrickView2,2);

            //게임 중으로 바꿈
            gameBoard2.whileGame =true;


            //테스트
            //printMatrix();

        }
        else{
            //착지시(아이템)
 /*           if(turnEnd==true){

                Item.turnEndDoItem(currentBrick, gameBoard, boardView); //아이템 , 하드드롭했을때
                turnEnd=false;
            }*/
            if(!currentBrick2.canMoveDown()/*!canMoveDown()*/){ //더 못내려가면
                //그 위치에 색칠
                //colorFill();

                gameBoard2.turnEnd=true;
                nextBrickView2.setVisible(true);


                Drawing.colorFill(currentBrick2,boardView2);
                fixed(gameBoard2,currentBrick2);
                //아이템 기능을 빼고 아무슨아이템이냐 받고 호출
                //Block Item
                System.out.println("!currentBrick.canMoveDown()");

                //착지시(아이템) , 살포시 안착했을때
                Item.turnEndDoItem(currentBrick2, gameBoard2, boardView2); //아이템

                /*if(currentBrick.isItem?) {
                    //(1) 케이스 아이템 있으면 해당 로직 먼저 수행
                }*/
                //System.out.println("완성 줄 삭제 전---------------");
                System.out.println("*********************보드2**********************");
                printMatrix(gameBoard2);


                //먼저 삭제되는 로우 가져와서 거기에 아이템 있는지 확인(아이템)
                List<Integer> removedRows = gameBoard2.getRemovedRows(); //삭제 전에 우선 삭제되는 라인 먼저 확인

                //보드 전부 0
                checkAndDoItem6(removedRows,gameBoard2,boardView2);

                //NPE조심
                Drawing.updateBoardView(removedRows,boardView2, gameBoard2.board); //gui 여기서 삭제
                gameBoard2.removeFullRows(); //배열에서 삭제 후 점수 업뎃

                //System.out.println("완성 줄 삭제 후---------------");
                //printMatrix();
                //gravity로 1인지 확인해서 board 업데이트하고

                //Drawing.updateBoardView(removeLineList);
                //printMatrix(gameBoard2);
                //줄 지우기


                //겜 끝났는지 확인
                if(isGameOver(gameBoard2)){
                    //스코어보드 처리

                    System.out.println("GameOver");
                    //전부 초기화
                    destroy();

                    //테스트
                    //printMatrix();
                }
                else{
                    Item.sponDoItem(currentBrick2, gameBoard2, nextBrickView2);

                    gameBoard2.turnEnd=false;
                    //nextBrick을 currentBrick으로 옮김. + 색칠 + 이벤트 장착
                    sponBrick(gameBoard2,boardView2, nextBrickView2,2);
                    chageTime(gameBoard2);


                    //스폰되자마자 블록 아이템 수행
                    //Item.sponDoItem(currentBrick, gameBoard, boardView);

                    //System.out.println("겜은 안끝났지만 내려갈 곳 없어서 블록 스폰"+ gameBoard2.blockSpon);
                    //테스트
                    //printMatrix();
                }

            }
            else {
                //지우고 moveD() 호출하고 색칠하기
                Drawing.colorErase(currentBrick2,boardView2);
                //System.out.println("겜은 안끝났고 내려갈 곳도 있고");
                currentBrick2.moveD();
                Drawing.colorFill(currentBrick2,boardView2);

                //테스트
                //printMatrix();
            }
            if(gameBoard2.turnEnd==true){

                Item.turnEndDoItem(currentBrick2, gameBoard2, boardView2); //아이템 , 하드드롭했을때
                gameBoard2.turnEnd=false;
            }
        }
    }

    private void chageTime(GameBoard1 gameBoard) {
        if((gameBoard.deleteLine%10==0 && gameBoard.deleteLine!=0) || gameBoard.blockSpon%20 ==0) {
            gameBoard.downScore++;
            System.out.println("changeTime 실행");
            startTimeLine(changeSpeed(difficulty, gameBoard));
        }
    }

    public double changeSpeed(int difficulty, GameBoard1 gameBoard){
        if(difficulty==0){ //이지
            gameBoard.speed=gameBoard.speed*0.92;
            return gameBoard.speed;
        }
        else if(difficulty==1){ //노말
            gameBoard.speed= gameBoard.speed*0.9;
            return gameBoard.speed;
        }
        else{ //하드
            gameBoard.speed= gameBoard.speed*0.88;
            return gameBoard.speed;
        }
    }


    //아이템6 실행
    private void checkAndDoItem6(List<Integer> removedRows,GameBoard1 gameBoard,GridPane boardView) {
        boolean flag=false;
        for(int fullRow : removedRows){
            for(int i = 0; i< GameBoard1.WIDTH; i++){
                if(gameBoard.board[fullRow][i]==6){
                    flag=true;
                }
            }
            if(flag==true){
                break;
            }
        }
        if(flag==true){
            for (int[] row : gameBoard.board) {
                Arrays.fill(row, 0);
            }
            Drawing.updateBoardView(boardView, gameBoard.board);
        }
    }

    private void sponBrick(GameBoard1 gameBoard , GridPane gridPane, GridPane nextBrickView,int n) { //n이 1이면 currentBrick 1에, n이 2이면 currentBrick 2에
        //nextBrick을 currentBrick으로 옮김.
        if(n==1) {
            currentBrick = nextBrick;
            //regiBrickEvent(currentBrick,boardView,gameBoard);
        }
        else {
            currentBrick2 = nextBrick2;
            regiBrickEvent(currentBrick2,boardView2,gameBoard2);
        }
        //nextBrick 랜덤 뽑아와서 세팅
        if(n==1) {
            if (gameBoard.deleteLine % 10 == 0 && gameBoard.deleteLine != 0 && itemMode == true) {
                nextBrick = rg.generateItem(0, colorBlindness, gameBoard);
                gameBoard.deleteLine = 0;
                gameBoard.blockSpon++;
            } else {
                nextBrick = rg.genarateNormal(0, colorBlindness, gameBoard);
                //nextBrick=rg.generateItem(difficulty,colorBlindness);
                gameBoard.blockSpon++;
            }
        }

        if(n==2) {
            if (gameBoard.deleteLine % 10 == 0 && gameBoard.deleteLine != 0 && itemMode == true) {
                nextBrick2 = rg.generateItem(0, colorBlindness, gameBoard);
                gameBoard.deleteLine = 0;
                gameBoard.blockSpon++;
            } else {
                nextBrick2 = rg.genarateNormal(0, colorBlindness, gameBoard);
                //nextBrick=rg.generateItem(difficulty,colorBlindness);
                gameBoard.blockSpon++;
            }
        }

        //nextBrick=new BrickZ(0,4,Color.GREEN );
        //nextBrick = new BrickO(0,4,Color.SKYBLUE);

        if(n==1) {
            //currentBrick 색칠하고
            Drawing.colorFill(currentBrick, gridPane);
            displayNextBrick(nextBrick, nextBrickView);
            //이벤트 장착
        }
        else{
            Drawing.colorFill(currentBrick2, gridPane);
            displayNextBrick(nextBrick2, nextBrickView);
        }
    }

/*    private void sponBrick2() {
        //nextBrick을 currentBrick으로 옮김.
        currentBrick2=nextBrick2;

        //nextBrick 랜덤 뽑아와서 세팅
        if(GameBoard2.deleteLine%10==0 && GameBoard2.deleteLine!=0 && itemMode==true ) {
            nextBrick2 = rg.generateItem(0, colorBlindness, gameBoard2);
            GameBoard2.deleteLine=0;
        }
        else{
            nextBrick2=rg.genarateNormal(0, colorBlindness,gameBoard2);
            //nextBrick=rg.generateItem(difficulty,colorBlindness);
        }
        blockSpon++;
        //nextBrick=new BrickZ(0,4,Color.GREEN );
        //nextBrick = new BrickO(0,4,Color.SKYBLUE);

        //currentBrick 색칠하고
        Drawing.colorFill2(currentBrick2);
        displayNextBrick(nextBrick2,nextBrickView2);
        //이벤트 장착
    }*/


    public boolean isGameOver(GameBoard1 gameBoard) {
        for(int i=0;i<GameBoard1.WIDTH;i++){
            if(gameBoard.board[1][i]>=1){
                //GameBoard.whileGame =true;
                gameBoard.whileGame =false;
                return true;
            }
        }
        return false;
        /*for (int i = 1; i < GameBoard.HEIGHT; i++) {
            boolean found = false;  // 각 행마다 검사를 시작할 때 'found'를 'false'로 설정
            for (int j = 0; j < GameBoard.WIDTH; j++) {
                if (GameBoard.board[i][j] > 0) {
                    found = true;  // 0보다 큰 값을 찾으면 'found'를 'true'로 설정
                    break;  // 더 이상 검사할 필요 없음
                }
            }
            if (!found) {  // 만약 이 행에서 0보다 큰 값이 없다면
                return false;  // 즉시 false 반환
            }
        }
        return true;  // 모든 행에 0보다 큰 값이 하나 이상 있다면 true 반환
        */

    }

    //2차원배열 출력 테스트함수
    public void printMatrix(GameBoard1 gameBoard){
        for(int i = 0; i< GameBoard1.HEIGHT; i++){
            for(int j = 0; j< GameBoard1.WIDTH; j++){
                System.out.printf("%d ", gameBoard.board[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------END-------");
    }


    //현재 브릭 x , y 모든 좌표 출력
    public void printBlock(Brick currentBrick){
        for(Block block : currentBrick.getBlockList()){
            System.out.println("x값: " + block.getX() + " y 값: "+block.getY());
        }
    }



    //무게추 같은 경우에는 로테이트 함수 구현은 해놔야됨. 바디는 냅두고
    private void regiBrickEvent(Brick currentBrick,GridPane boardView,GameBoard1 gameBoard) {
        boardView.setOnKeyPressed(event -> {
            Drawing.colorErase(currentBrick,boardView);
            String keyValue = event.getCode().toString();
            if (event.getCode() == KeyCode.ESCAPE) {
                gameBoard.pause = !gameBoard.pause;
                if(gameBoard.pause) {
                    boardView.setOpacity(0);
                    nextBrickView.setOpacity(0);
                    timeline.stop();
                } else {
                    boardView.setOpacity(1);
                    nextBrickView.setOpacity(1);
                    timeline.play();
                }
            } else if (event.getCode() == KeyCode.BACK_SPACE) {
                // 백스페이스 키가 눌렸을 때의 동작 (게임 종료)
                Stage stage = (Stage) boardView.getScene().getWindow();
                timeline.stop(); // 타임라인 애니메이션을 정지합니다.
                stage.close(); // 현재 스테이지를 닫습니다.
            }
            if (!gameBoard.pause) {
                if (keyValue.equals(brickController.getMOVER()) || keyValue.toLowerCase().equals(brickController.getMOVER())) {
                    // 오른쪽 이동 키가 눌렸을 때의 동작
                    System.out.println("Right key pressed");
                    brickController.moveR(currentBrick);
                    printBlock(currentBrick);
                } else if (keyValue.equals(brickController.getMOVEL()) || keyValue.toLowerCase().equals(brickController.getMOVEL())) {
                    // 왼쪽 이동 키가 눌렸을 때의 동작
                    System.out.println("Left key pressed");
                    brickController.moveL(currentBrick);
                    printBlock(currentBrick);
                } else if (keyValue.equals(brickController.getMOVED()) || keyValue.toLowerCase().equals(brickController.getMOVED())) {
                    // 아래 이동 키가 눌렸을 때의 동작
                    brickController.moveD(currentBrick);
                    printBlock(currentBrick);
                } else if (keyValue.equals(brickController.getROTATE()) || keyValue.toLowerCase().equals(brickController.getROTATE())) {
                    // 회전 키가 눌렸을 때의 동작
                    System.out.println("Rotate key pressed");
                    brickController.rotate(currentBrick);
                    printBlock(currentBrick);
                } else if(keyValue.equals(brickController.getSTRAIGHT()) || keyValue.toLowerCase().equals(brickController.getSTRAIGHT())) {
                    //여기는 수직떨구기
                    System.out.println("---------------------------------수직 떨구기 누름");
                    brickController.straightD(currentBrick);
                    //
                    if (isHardDropGameOver()) {
                        Drawing.colorFill(currentBrick,boardView);
                        fixed(gameBoard,currentBrick);
                        System.out.println("수직떨구기");
                        destroy();
                    } else {
                        //수직 떨구고 timeline을 간격 없이 바로 새로 시작해야돼서
                        timeline.stop();
                        System.out.println("---------------------------------정지");
                        gameBoard.turnEnd = true;
                        //떨구고 바로 블록 뽑아옴
                        gameBoard.whileGame=true;
                        minute10();
                        timeline.play();
                        Drawing.colorErase(currentBrick,boardView);
                        System.out.println("---------------------------------재게");
                        System.out.println("수직떨구기");
                    }
                }
                event.consume();
                if (gameBoard.whileGame == true) {
                    Drawing.colorFill(currentBrick,boardView);
                }//색칠하고
            }
        });
    }

    //보드 해상도 change함수
    public void change(GridPane boardView) throws IOException {
        // 해상도에 따라 칸의 크기를 동적으로 조정
        Properties properties = loadProperties();
        String resolution = properties.getProperty("resolution", "800x600");
        String[] dimensions = resolution.split("x");
        double width = Double.parseDouble(dimensions[0]);
        double height = Double.parseDouble(dimensions[1]);

        int numRows = 22; // 행의 수
        int numCols = 10; // 열의 수

        //해상도 바꾸고 싶으면 여기를 바꾼다.
        cellWidth = height / 30;
        cellHeight = height / 30;

        boardView.getColumnConstraints().clear();
        boardView.getRowConstraints().clear();

        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setMinWidth(cellWidth);
            colConstraints.setPrefWidth(cellWidth);
            colConstraints.setMaxWidth(cellWidth);
            boardView.getColumnConstraints().add(colConstraints);
        }

        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setMinHeight(cellHeight);
            rowConstraints.setPrefHeight(cellHeight);
            rowConstraints.setMaxHeight(cellHeight);
            boardView.getRowConstraints().add(rowConstraints);
        }
    }

    public void updateScoreLabel(Label scoreLabel,GameBoard1 gameBoard) {
        this.scoreLabel.setText("Score: " +Integer.toString(gameBoard.getScore()));
    }

    //hardDrop 시에 게임오버 판단
    public boolean isHardDropGameOver() {
        boolean flag = false;
        List<Block> blockList = currentBrick.getBlockList();
        for (Block block : blockList) {
            if (block.getX() < 2) {
                flag = true;
            }
        }
        return flag;
    }

    public boolean isHardDropGameOver2() {
        boolean flag = false;
        List<Block> blockList = currentBrick2.getBlockList();
        for (Block block : blockList) {
            if (block.getX() < 2) {
                flag = true;
            }
        }
        return flag;
    }


    public static void setOptions(int difficulty, boolean itemMode) {
        GameBoardController.difficulty = difficulty;
        GameBoardController.itemMode = itemMode;
        // 여기서부터 게임을 시작할 수 있음
        System.out.println("난이도" + difficulty);
        System.out.println("아이템모드" + itemMode);
    }





    //프로퍼티에서 색맹모드 여부 가져오기
    public boolean getColorBliness(){
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
        String temp = settingProperties.getProperty("colorBlindness");
        int num = Integer.parseInt(temp);
        if(num==0){
            return false;
        }
        else{
            return true;
        }
    }
}
