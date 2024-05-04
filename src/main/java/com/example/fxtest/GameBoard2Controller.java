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
import javafx.scene.layout.GridPane;
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
//시간이 좀 많이 지나고 브릭 스폰 위치가 이미 쌓여있는 board 블록과 겹치면? >> Board 늘려서 스폰 위치 따로 빼거나 스폰 자체를 바꿔야될듯
public class GameBoard2Controller implements Initializable {

    public static double cellWidth = 20;
    public static double cellHeight = 20;
    Brick currentBrick;
    Brick nextBrick;
    Brick currentBrick2;
    Brick nextBrick2;

    BrickController brickController;
    BrickController brickController2;

    GameBoard gameBoard = new GameBoard();
    GameBoard gameBoard2 = new GameBoard();

    Timeline timeline;
    Timeline timeline2;

    RandomGenerator rg = new RandomGenerator();

    boolean turnEnd =false;


    double speed=1;

    int blockSpon=0; //스폰블록


    boolean colorBlindness=true; //색맹모드




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

    public static int downScore=0; //속도마다 다르게 변경

    //난이도, 아이템 모드 확인
    public static int difficulty; //난이도
    public static boolean itemMode; //이거 setter로 받아야됨



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
    public void fixed(){
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
            GameBoard.board[block.getX()][block.getY()]=block.getItem().getNum();
    }

    public void fixed2(){
        if(currentBrick2==null){
            return;
        }
        if(currentBrick2.getBlockList()==null){
            System.out.println("getBlockList가 null임");
        }
        else{
            System.out.println("null 아님");
        }
        for(Block block : currentBrick2.getBlockList())
            GameBoard2.board2[block.getX()][block.getY()]=block.getItem().getNum();
    }

    //뷰 바꿔서 겜 시작이 아닌 그 뷰에서 그대로 게임 (재)시작때 초기화
    void init(){
        initBoard();
        GameBoard.setScore(0);
        GameBoard.deleteLine=0;
        GameBoard.whileGame =false;
        GameBoard2.setScore(0);
        GameBoard2.deleteLine=0;
        GameBoard2.whileGame =false;
        //timeline.stop();
        regiBrickEvent();
        colorBlindness=getColorBliness();
        System.out.println("초기화완료");
    }

    void destroy(){
/*        initBoard();
        GameBoard.score=0;
        GameBoard.deleteLine=0; */
        GameBoard.whileGame =false;
        GameBoard2.whileGame =false;
        timeline.stop();
        timeline2.stop();
        boardView.setOnKeyPressed(null);
        boardView2.setOnKeyPressed(null);
        System.out.println("초기화완료");
        turnEnd=false;
    }


    void initBoard(){
        for (int[] row : GameBoard.board) {
            Arrays.fill(row, 0);
        }
        for (int[] row : GameBoard2.board2) {
            Arrays.fill(row, 0);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boardView.setFocusTraversable(true);
        boardView2.setFocusTraversable(true);
        //difficulty, 색맹여부 받아오는 ,


        init();


        currentBrick=rg.genarateNormal(0,colorBlindness); //일단 이지로, 여기서 모드 받아와야됨.
        //currentBrick= new BrickI(0,4,Color.GREEN );
        //currentBrick = new BrickO(0,4,Color.SKYBLUE);
        currentBrick2=rg.genarateNormal(0,colorBlindness);

        nextBrick=rg.genarateNormal(0,colorBlindness);
        //nextBrick=new BrickI(0,4,Color.GREEN );
        //nextBrick = new BrickO(0,4,Color.SKYBLUE);
        nextBrick2=rg.genarateNormal(0,colorBlindness);

        brickController = BrickController.getBrickController(); //키 값 전부 field에 세팅
        brickController2 = BrickController.getBrickController();
        // GridPane에 키 이벤트 핸들러 등록
        regiBrickEvent();
        Drawing.setBoardView(boardView);
        Drawing.setBoardView2(boardView2);



        GameBoard.scoreProperty().addListener((obs, oldScore, newScore) -> {
            if (newScore.intValue() > oldScore.intValue()) {
                updateScoreLabel(scoreLabel);
            }
        });

        GameBoard2.scoreProperty().addListener((obs, oldScore, newScore) -> {
            if (newScore.intValue() > oldScore.intValue()) {
                updateScoreLabel2(scoreLabel2);
            }
        });

        displayNextBrick(nextBrick,nextBrickView);
        displayNextBrick(nextBrick2,nextBrickView2);

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
        Drawing.colorErase(currentBrick);
        System.out.println(difficulty+">> diff");
        System.out.println(itemMode +">> itemMode");
        //printBlock();
        if(!GameBoard.whileGame){
            downScore=1;
            System.out.println("겜 자체 시작");
            //nextBrick을 currentBrick으로 옮김. + 색칠 + 이벤트 장착
            sponBrick();

            //게임 중으로 바꿈
            GameBoard.whileGame =true;


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

                turnEnd=true;
                nextBrickView.setVisible(true);

                Drawing.colorFill(currentBrick);
                fixed();
                //아이템 기능을 빼고 아무슨아이템이냐 받고 호출
                //Block Item
                System.out.println("!currentBrick.canMoveDown()");

                //착지시(아이템) , 살포시 안착했을때
                Item.turnEndDoItem(currentBrick, gameBoard, boardView); //아이템

                /*if(currentBrick.isItem?) {
                    //(1) 케이스 아이템 있으면 해당 로직 먼저 수행
                }*/
                //System.out.println("완성 줄 삭제 전---------------");
                printMatrix();


                //먼저 삭제되는 로우 가져와서 거기에 아이템 있는지 확인(아이템)
                List<Integer> removedRows = gameBoard.getRemovedRows(); //삭제 전에 우선 삭제되는 라인 먼저 확인

                //보드 전부 0
                checkAndDoItem6(removedRows);

                //NPE조심
                Drawing.updateBoardView(removedRows); //gui 여기서 삭제
                gameBoard.removeFullRows(); //배열에서 삭제 후 점수 업뎃

                //System.out.println("완성 줄 삭제 후---------------");
                //printMatrix();
                //gravity로 1인지 확인해서 board 업데이트하고

                //Drawing.updateBoardView(removeLineList);
                printMatrix();
                //줄 지우기


                //겜 끝났는지 확인
                if(isGameOver()){
                    //스코어보드 처리

                    System.out.println("GameOver");
                    //전부 초기화
                    destroy();

                    //테스트
                    //printMatrix();
                }
                else{
                    Item.sponDoItem(currentBrick, gameBoard, nextBrickView);

                    turnEnd=false;
                    //nextBrick을 currentBrick으로 옮김. + 색칠 + 이벤트 장착
                    sponBrick();

                    //스폰되자마자 블록 아이템 수행
                    //Item.sponDoItem(currentBrick, gameBoard, boardView);

                    System.out.println("겜은 안끝났지만 내려갈 곳 없어서 블록 스폰"+ blockSpon);
                    //테스트
                    //printMatrix();
                }

            }
            else {
                //지우고 moveD() 호출하고 색칠하기
                Drawing.colorErase(currentBrick);
                System.out.println("겜은 안끝났고 내려갈 곳도 있고");
                currentBrick.moveD();
                Drawing.colorFill(currentBrick);

                //테스트
                //printMatrix();
            }
            if(turnEnd==true){

                Item.turnEndDoItem(currentBrick, gameBoard, boardView); //아이템 , 하드드롭했을때
                turnEnd=false;
            }
        }
    }

        private void minute10_2(){
        Drawing.colorErase2(currentBrick2);
        System.out.println(difficulty+">> diff");
        System.out.println(itemMode +">> itemMode");
        //printBlock();
        if(!GameBoard2.whileGame){
            downScore=1;
            System.out.println("겜 자체 시작");
            //nextBrick을 currentBrick으로 옮김. + 색칠 + 이벤트 장착
            sponBrick2();

            //게임 중으로 바꿈
            GameBoard2.whileGame =true;


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

                turnEnd=true;
                nextBrickView2.setVisible(true);


                Drawing.colorFill2(currentBrick2);
                fixed2();
                //아이템 기능을 빼고 아무슨아이템이냐 받고 호출
                //Block Item
                System.out.println("!currentBrick.canMoveDown()");

                //착지시(아이템) , 살포시 안착했을때
                Item.turnEndDoItem(currentBrick2, gameBoard2, boardView2); //아이템

                /*if(currentBrick.isItem?) {
                    //(1) 케이스 아이템 있으면 해당 로직 먼저 수행
                }*/
                //System.out.println("완성 줄 삭제 전---------------");
                printMatrix2();


                //먼저 삭제되는 로우 가져와서 거기에 아이템 있는지 확인(아이템)
                List<Integer> removedRows = gameBoard2.getRemovedRows(); //삭제 전에 우선 삭제되는 라인 먼저 확인

                //보드 전부 0
                checkAndDoItem6(removedRows);

                //NPE조심
                Drawing.updateBoardView2(removedRows); //gui 여기서 삭제
                gameBoard2.removeFullRows(); //배열에서 삭제 후 점수 업뎃

                //System.out.println("완성 줄 삭제 후---------------");
                //printMatrix();
                //gravity로 1인지 확인해서 board 업데이트하고

                //Drawing.updateBoardView(removeLineList);
                printMatrix2();
                //줄 지우기


                //겜 끝났는지 확인
                if(isGameOver2()){
                    //스코어보드 처리

                    System.out.println("GameOver");
                    //전부 초기화
                    destroy();

                    //테스트
                    //printMatrix();
                }
                else{
                    Item.sponDoItem(currentBrick2, gameBoard2, nextBrickView2);

                    turnEnd=false;
                    //nextBrick을 currentBrick으로 옮김. + 색칠 + 이벤트 장착
                    sponBrick2();

                    //스폰되자마자 블록 아이템 수행
                    //Item.sponDoItem(currentBrick, gameBoard, boardView);

                    System.out.println("겜은 안끝났지만 내려갈 곳 없어서 블록 스폰"+ blockSpon);
                    //테스트
                    //printMatrix();
                }

            }
            else {
                //지우고 moveD() 호출하고 색칠하기
                Drawing.colorErase2(currentBrick2);
                System.out.println("겜은 안끝났고 내려갈 곳도 있고");
                currentBrick2.moveD();
                Drawing.colorFill2(currentBrick2);

                //테스트
                //printMatrix();
            }
            if(turnEnd==true){

                Item.turnEndDoItem(currentBrick2, gameBoard2, boardView2); //아이템 , 하드드롭했을때
                turnEnd=false;
            }
        }
    }

    //아이템6 실행
    private void checkAndDoItem6(List<Integer> removedRows) {
        boolean flag=false;
        for(int fullRow : removedRows){
            for(int i=0; i<GameBoard.WIDTH; i++){
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
            Drawing.updateBoardView2();
        }
    }

    private void sponBrick() {
        //nextBrick을 currentBrick으로 옮김.
        currentBrick=nextBrick;

        //nextBrick 랜덤 뽑아와서 세팅
        if(GameBoard.deleteLine%10==0 && GameBoard.deleteLine!=0 && itemMode==true ) {
            nextBrick = rg.generateItem(0, colorBlindness);
            GameBoard.deleteLine=0;
        }
        else{
            nextBrick=rg.genarateNormal(0, colorBlindness);
            //nextBrick=rg.generateItem(difficulty,colorBlindness);
        }
        blockSpon++;
        //nextBrick=new BrickZ(0,4,Color.GREEN );
        //nextBrick = new BrickO(0,4,Color.SKYBLUE);

        //currentBrick 색칠하고
        Drawing.colorFill(currentBrick);
        displayNextBrick(nextBrick,nextBrickView);
        //이벤트 장착
    }

    private void sponBrick2() {
        //nextBrick을 currentBrick으로 옮김.
        currentBrick2=nextBrick2;

        //nextBrick 랜덤 뽑아와서 세팅
        if(GameBoard2.deleteLine%10==0 && GameBoard2.deleteLine!=0 && itemMode==true ) {
            nextBrick2 = rg.generateItem(0, colorBlindness);
            GameBoard2.deleteLine=0;
        }
        else{
            nextBrick2=rg.genarateNormal(0, colorBlindness);
            //nextBrick=rg.generateItem(difficulty,colorBlindness);
        }
        blockSpon++;
        //nextBrick=new BrickZ(0,4,Color.GREEN );
        //nextBrick = new BrickO(0,4,Color.SKYBLUE);

        //currentBrick 색칠하고
        Drawing.colorFill2(currentBrick2);
        displayNextBrick(nextBrick2,nextBrickView2);
        //이벤트 장착
    }

    public boolean isGameOver() {
        for(int i=0;i<GameBoard.WIDTH;i++){
            if(GameBoard.board[1][i]>=1){
                //GameBoard.whileGame =true;
                GameBoard.whileGame =false;
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

    public boolean isGameOver2() {
        for(int i=0;i<GameBoard2.WIDTH;i++){
            if(GameBoard2.board2[1][i]>=1){
                //GameBoard.whileGame =true;
                GameBoard2.whileGame =false;
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
    public void printMatrix(){
        for(int i=0; i<GameBoard.HEIGHT; i++){
            for(int j=0; j<GameBoard.WIDTH; j++){
                System.out.printf("%d ",GameBoard.board[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------END-------");
    }

    public void printMatrix2(){
        for(int i=0; i<GameBoard2.HEIGHT; i++){
            for(int j=0; j<GameBoard2.WIDTH; j++){
                System.out.printf("%d ",GameBoard2.board2[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------END-------");
    }

    //현재 브릭 x , y 모든 좌표 출력
    public void printBlock(){
        for(Block block : currentBrick.getBlockList()){
            System.out.println("x값: " + block.getX() + " y 값: "+block.getY());
        }
    }

    public void printBlock2(){
        for(Block block : currentBrick2.getBlockList()){
            System.out.println("x값: " + block.getX() + " y 값: "+block.getY());
        }
    }



    //무게추 같은 경우에는 로테이트 함수 구현은 해놔야됨. 바디는 냅두고
    private void regiBrickEvent() {
        boardView.setOnKeyPressed(event -> {
            Drawing.colorErase(currentBrick);
            String keyValue = event.getCode().toString();
            if (event.getCode() == KeyCode.ESCAPE) {
                GameBoard.pause = !GameBoard.pause;
                if(GameBoard.pause) {
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
            if (!GameBoard.pause) {
                if (keyValue.equals(brickController.getMOVER()) || keyValue.toLowerCase().equals(brickController.getMOVER())) {
                    // 오른쪽 이동 키가 눌렸을 때의 동작
                    System.out.println("Right key pressed");
                    brickController.moveR(currentBrick);
                    printBlock();
                } else if (keyValue.equals(brickController.getMOVEL()) || keyValue.toLowerCase().equals(brickController.getMOVEL())) {
                    // 왼쪽 이동 키가 눌렸을 때의 동작
                    System.out.println("Left key pressed");
                    brickController.moveL(currentBrick);
                    printBlock();
                } else if (keyValue.equals(brickController.getMOVED()) || keyValue.toLowerCase().equals(brickController.getMOVED())) {
                    // 아래 이동 키가 눌렸을 때의 동작
                    brickController.moveD(currentBrick);
                    printBlock();
                } else if (keyValue.equals(brickController.getROTATE()) || keyValue.toLowerCase().equals(brickController.getROTATE())) {
                    // 회전 키가 눌렸을 때의 동작
                    System.out.println("Rotate key pressed");
                    brickController.rotate(currentBrick);
                    printBlock();
                } else if(keyValue.equals(brickController.getSTRAIGHT()) || keyValue.toLowerCase().equals(brickController.getSTRAIGHT())) {
                    //여기는 수직떨구기
                    System.out.println("---------------------------------수직 떨구기 누름");
                    brickController.straightD(currentBrick);
                    //
                    if (isHardDropGameOver()) {
                        Drawing.colorFill(currentBrick);
                        fixed();
                        System.out.println("수직떨구기");
                        destroy();
                    } else {
                        //수직 떨구고 timeline을 간격 없이 바로 새로 시작해야돼서
                        timeline.stop();
                        System.out.println("---------------------------------정지");
                        turnEnd = true;
                        //떨구고 바로 블록 뽑아옴
                        minute10();
                        timeline.play();
                        Drawing.colorErase(currentBrick);
                        System.out.println("---------------------------------재게");
                        System.out.println("수직떨구기");
                    }
                }
                event.consume();
                if (GameBoard.whileGame == true) {
                    Drawing.colorFill(currentBrick);
                }//색칠하고
            }
        });
    }


    public void updateScoreLabel(Label scoreLabel) {
        this.scoreLabel.setText("Score: " +Integer.toString(GameBoard.getScore()));
    }

    public void updateScoreLabel2(Label scoreLabel) {
        this.scoreLabel.setText("Score: " +Integer.toString(GameBoard2.getScore()));
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

