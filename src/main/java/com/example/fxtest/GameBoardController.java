package com.example.fxtest;
import static com.example.fxtest.Main.loadProperties;

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
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;
import java.util.ResourceBundle;
//시간이 좀 많이 지나고 브릭 스폰 위치가 이미 쌓여있는 board 블록과 겹치면? >> Board 늘려서 스폰 위치 따로 빼거나 스폰 자체를 바꿔야될듯
public class GameBoardController implements Initializable {
    Brick currentBrick;
    Brick nextBrick;

    BrickController brickController = new BrickController();

    GameBoard gameBoard = new GameBoard();

    Timeline timeline;

    @FXML
    private GridPane boardView; //컨트롤View 매핑
    @FXML
    private TextField textField;
    @FXML
    private Button StartButton;
    @FXML
    private Button ExitButton;

    public static double cellWidth = 20;
    public double cellHeight = 20;


    //주기함수 종료하고 다시 처음 페이지로
    @FXML
    public void onStartButtonClick() throws IOException{
        Stage st = StageSaver.pStage;
        FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("Start.fxml"));
        Scene mainpage = new Scene(fxmlLoader.load(), 320, 240);
        timeline.stop(); //주기함수 종료
        initBoard(); //board 0 초기화
        GameBoard.whileGame =false;
        st.setScene(mainpage);
        st.show();
    }

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

    //게임 (재)시작때 초기화
    void init(){
        initBoard();
        GameBoard.score=0;
        GameBoard.deleteLine=0;
        GameBoard.whileGame =false;
        timeline.stop();
        System.out.println("초기화완료");
    }
    
    void initBoard(){
        for (int[] row : GameBoard.board) {
            Arrays.fill(row, 0);
        }
    }


    //@FXML로 게임 시작 버튼 만들어서 이거 누르면 다시 매 1초마다 호출되는 함수 호출하여 게임 재시작
    //여기서 initialize 함수 호출

    public void colorErase() {
        /*for (Block block : currentBrick.getBlockList()) { // currentBrick에서 Block 배열을 가져오는 가정
            int x = block.getX();
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

        for(Block block : currentBrick.getBlockList()) {
            Rectangle rectangleAt = getRectangleAt(boardView,block.getY(),block.getX());
            boardView.getChildren().remove(rectangleAt);
        }
    }

    //현재 GameBoard위 움직이는 Brick의 각 Rectangle 가져오기
    private Rectangle getRectangleAt(GridPane gridPane, int columnIndex, int rowIndex) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            Integer nodeColumnIndex = GridPane.getColumnIndex(node);
            Integer nodeRowIndex = GridPane.getRowIndex(node);

            if (nodeColumnIndex != null && nodeRowIndex != null && nodeColumnIndex == columnIndex && nodeRowIndex == rowIndex) {
                if (node instanceof Rectangle) {
                    return (Rectangle) node;
                }
            }
        }
        return null;
    }

    public void colorFill(){
        for (Block block : currentBrick.getBlockList()) { // currentBrick에서 Block 배열을 가져오는 가정
            int x = block.getX();
            int y = block.getY();

            // 20x20 픽셀 크기의 Rectangle 생성
            Rectangle rectangle = new Rectangle(cellWidth, cellHeight);
            rectangle.setFill(Color.BLUE); // 색상 설정, 필요에 따라 변경 가능

            // GridPane에 Rectangle 추가
            boardView.add(rectangle, y, x);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boardView.setFocusTraversable(true);
        currentBrick=new BrickZ(0,4);
        //nextBrick 랜덤에서 뽑아오기(임시로)
        nextBrick=new BrickZ(0,4);
        
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
            stage.close();
        });

        setTime(1.0f);
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
    private void minute10(){
        if(!GameBoard.whileGame){

            //nextBrick을 currentBrick으로 옮김. + 색칠 + 이벤트 장착
            sponBrick();

            //게임 중으로 바꿈
            GameBoard.whileGame =true;


            //테스트
            printMatrix();

        }
        else{
            if(!currentBrick.canMoveDown()/*!canMoveDown()*/){
                //그 위치에 색칠
                colorFill();
                fixed();

                //1인지 확인하고
                //줄 지우기


                //겜 끝났는지 확인
                if(isGameOver()){
                    //스코어보드 처리

                    System.out.println("게임종료");
                    //전부 초기화
                    init();

                    //테스트
                    printMatrix();
                }
                else{
                    //nextBrick을 currentBrick으로 옮김. + 색칠 + 이벤트 장착
                    sponBrick();

                    //테스트
                    printMatrix();
                }

            }
            else {
                //지우고 moveD() 호출하고 색칠하기
                colorErase();
                currentBrick.moveD();
                colorFill();

                //테스트
                printMatrix();
            }
        }
    }

    private void sponBrick() {
        //nextBrick을 currentBrick으로 옮김.
        currentBrick=nextBrick;

        //nextBrick 랜덤 뽑아와서 세팅(일단 동일한 brick으로 세팅)
        nextBrick=new BrickZ(0, 4);

        //currentBrick 색칠하고
        colorFill();

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
