package com.example.fxtest;
import static com.example.fxtest.Main.loadProperties;

import com.example.fxtest.brick.Block;

//얜 임시로, 랜덤 구현하면 필요 없
import com.example.fxtest.brick.BrickO;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
//시간이 좀 많이 지나고 브릭 스폰 위치가 이미 쌓여있는 board 블록과 겹치면? >> Board 늘려서 스폰 위치 따로 빼거나 스폰 자체를 바꿔야될듯
public class GameBoardController implements Initializable {
    Brick currentBrick;
    Brick nextBrick;

    BrickController brickController;

    GameBoard gameBoard = new GameBoard();

    Timeline timeline;

    RandomGenerator rg = new RandomGenerator();


    @FXML
    public GridPane boardView; //컨트롤View 매핑

    @FXML
    private Button StartButton;
    @FXML
    private Button ExitButton;


    public static double cellWidth = 20;
    public static double cellHeight = 20;



    @FXML
    public void goHomeButtonClick() throws IOException{
        Stage st = StageSaver.pStage;
        timeline.stop(); //주기함수 종료
        FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("Start.fxml"));
        Scene mainpage = new Scene(fxmlLoader.load(),st.getWidth() , st.getHeight());
        st.setScene(mainpage);
        st.show();
    }

    @FXML
    public void pauseButtonClick() throws IOException{
        if(GameBoard.pause) {
            GameBoard.pause=false;
            regiBrickEvent();
            timeline.play();

        }
        else{
            GameBoard.pause=true;
            timeline.stop();
        }
    }


    //타임라인 시간 설정 메서드
    void setTime(double x){

        timeline = new Timeline(new KeyFrame(Duration.seconds(x), event -> {
            minute10(); //x초만큼의 속도
        }));


    }

    //더 이상 못내려갈때 Brick 행렬에 고정 , 노말 블록이면 1 , 아이템 블록이면 그 아이템 숫자 고정
    void fixed(){
        if(currentBrick.getBlockList()==null){
            System.out.println("getBlockList가 null임");
        }
        else{
            System.out.println("null 아님");
        }
        for(Block block : currentBrick.getBlockList())
        GameBoard.board[block.getX()][block.getY()]=block.getItem().getNum();
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

    void destroy(){
/*        initBoard();
        GameBoard.score=0;
        GameBoard.deleteLine=0; */
        GameBoard.whileGame =false;
        timeline.stop();
        boardView.setOnKeyPressed(null);
        System.out.println("초기화완료");
    }
    
    void initBoard(){
        for (int[] row : GameBoard.board) {
            Arrays.fill(row, 0);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boardView.setFocusTraversable(true);

        currentBrick=rg.genarateNormal(0,false); //일단 이지로, 여기서 모드 받아와야됨.
        //currentBrick= new BrickZ(0,4,Color.GREEN );
        //currentBrick = new BrickO(0,4,Color.SKYBLUE);

        nextBrick=rg.genarateNormal(0,false);
        //nextBrick=new BrickZ(0,4,Color.GREEN );
        //nextBrick = new BrickO(0,4,Color.SKYBLUE);

        brickController = BrickController.getBrickController(); //키 값 전부 field에 세팅
        // GridPane에 키 이벤트 핸들러 등록
        regiBrickEvent();
        Drawing.setBoardView(boardView);
        
        //change()함수 실행
        try {
            change();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // startButton의 클릭 이벤트 핸들러 등록
        StartButton.setOnAction(event -> {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // 새로운 Scene을 로드합니다.
            try {
                Properties properties = loadProperties();
                String resolution = properties.getProperty("resolution", "800x600");
                String[] dimensions = resolution.split("x");
                double width = Double.parseDouble(dimensions[0]);
                double height = Double.parseDouble(dimensions[1]);

                // 세팅 페이지 로드
                FXMLLoader loader = new FXMLLoader(getClass().getResource("GameBoard.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root, width, height);

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
            timeline.stop();
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
        Drawing.colorErase(currentBrick);
        printBlock();
        if(!GameBoard.whileGame){
            System.out.println("겜 자체 시작");
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
                System.out.println("!currentBrick.canMoveDown()");
                /*if(currentBrick.isItem?) {
                    //(1) 케이스 아이템 있으면 해당 로직 먼저 수행
                }*/
                //System.out.println("완성 줄 삭제 전---------------");
                //printMatrix();

                List<Integer> removedRows = gameBoard.getRemovedRows();
                Drawing.updateBoardView(removedRows);
                gameBoard.removeFullRows();
                //System.out.println("완성 줄 삭제 후---------------");
                //printMatrix();
                //gravity로 1인지 확인해서 board 업데이트하고

                //Drawing.updateBoardView(removeLineList);
                //printMatrix();
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
                    //nextBrick을 currentBrick으로 옮김. + 색칠 + 이벤트 장착
                    sponBrick();
                    System.out.println("겜은 안끝났지만 내려갈 곳 없어서 블록 스폰");
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
        }
    }

    private void sponBrick() {
        //nextBrick을 currentBrick으로 옮김.
        currentBrick=nextBrick;

        //nextBrick 랜덤 뽑아와서 세팅(일단 동일한 brick으로 세팅)
        nextBrick=rg.genarateNormal(0, false);
        //nextBrick=new BrickZ(0,4,Color.GREEN );
        //nextBrick = new BrickO(0,4,Color.SKYBLUE);

        //currentBrick 색칠하고
        Drawing.colorFill(currentBrick);

        //이벤트 장착
    }

    public boolean isGameOver() {
/*        for(int i=0;i<GameBoard.WIDTH;i++){
            if(GameBoard.board[1][i]>=1){
                GameBoard.whileGame =true;
                return true;
            }
        }
        return false;*/
        for (int i = 1; i < GameBoard.HEIGHT; i++) {
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



    //무게추 같은 경우에는 로테이트 함수 구현은 해놔야됨. 바디는 냅두고
    private void regiBrickEvent() {
        boardView.setOnKeyPressed(event -> {
            Drawing.colorErase(currentBrick);
            String keyValue = event.getCode().toString();
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
            } else if(keyValue.equals(brickController.getSTRAIGHT()) || keyValue.toLowerCase().equals(brickController.getSTRAIGHT())){
                //여기는 수직떨구기
                System.out.println("---------------------------------수직 떨구기 누름");
                brickController.straightD(currentBrick);
                //수직 떨구고 timeline을 간격 없이 바로 새로 시작해야돼서
                timeline.stop();
                System.out.println("---------------------------------정지");
                //떨구고 바로 블록 뽑아옴
                minute10();
                timeline.play();
                Drawing.colorErase(currentBrick);
                System.out.println("---------------------------------재게");
                System.out.println("수직떨구기");
            }
            event.consume();
            if(GameBoard.whileGame==true) {
                Drawing.colorFill(currentBrick);
            }//색칠하고
        });
    }



    private static final String PROPERTIES_FILE = "src/main/resources/resolution.properties";

    //보드 해상도 change함수
    public void change() throws IOException {
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
}
