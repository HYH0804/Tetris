package com.example.fxtest;

import com.example.fxtest.brick.Block;
import com.example.fxtest.brick.Brick;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameBoard1 {
    //이거 조심
    public final static int WIDTH =10; //일단 크기로... Index는 9까지
    public final static int HEIGHT=20; //일단 크기로... 0~1 행은 블록 스폰, 2~21 행은 보드

    //Board 2차원 배열 , 있으면 1 없으면 0
    public int[][] board = new int[HEIGHT][WIDTH]; //0으로 초기화 해야됨

    private IntegerProperty score = new SimpleIntegerProperty(0); //점수

    public  int[][] attackBoard = new int[10][10];
    public int attackCount=0;
    public int downScore=0; //속도마다 다르게 변경

    public double speed=1;

    boolean gameOver=false;

    public int deleteLine=0; //없앤 줄 >> 나중 속도 조절할때
    boolean whileGame =false; //Flag가 True로 되면 게임 끝나는 속성 이벤트 리스너

    boolean turnEnd =false;

    boolean pause = false;

    int blockSpon=0; ////스폰블록

    //생성자 (추후 필드 추가시 다시 봐야됨)
    public GameBoard1() {
        //board 배열 0으로 전부 초기화
        for (int[] row : board) {
            Arrays.fill(row, 0);
        }
    }

    public IntegerProperty scoreProperty() {
        return score;
    }

    public int getScore() {
        return score.get();
    }

    public void setScore(int newScore) {
        score.set(newScore);
    }


    // 보드를 순회하여 완전히 채워진 줄을 삭제하고 점수를 업데이트
    public void removeFullRows() {
        int rowsRemoved = 0;
        for (int row = 0; row < HEIGHT; row++) {
            if (isRowFull(row)) {
                removeRow(row);
                row--;
                rowsRemoved++;
            }
        }
        if (rowsRemoved > 0) {
            updateScoreLine(rowsRemoved); // 삭제된 줄의 수에 따라 점수 업데이트
        }
    }



    public void removeRow(int fullRow) {
        //Drawing.animeRow(fullRow);
        for (int row = fullRow; row > 0; row--) {
            for (int col = 0; col < WIDTH; col++) {
                board[row][col] = board[row - 1][col]; // 위의 줄을 아래로 복사
            }
        }
        Arrays.fill(board[0], 0); // 가장 윗 줄을 0으로 초기화
    }


    // 특정 줄이 완전히 채워졌는지 확인
    public boolean isRowFull(int row) {
        for (int col = 0; col < WIDTH; col++) {
            if (board[row][col] == 0) {
                return false; // 하나라도 비어있으면 false 반환
            }
        }
        return true; // 모든 칸이 채워져 있으면 true 반환
    }

    // 삭제된 줄의 수에 따라 점수를 업데이트
    public void updateScoreLine(int rowsRemoved) {
        switch (rowsRemoved) {
            case 1:
                updateScore(100);
                break;
            case 2:
                updateScore(300);
                break;
            case 3:
                updateScore(500);
                break;
            case 4:
                updateScore(800);
                break;
            default:
                break;
        }
        deleteLine += rowsRemoved;
    }


    //완성된 줄 확인만
    public List<Integer> getRemovedRows() {
        List<Integer> removedRows = new ArrayList<>();
        for (int row = 0; row < HEIGHT; row++) {
            if (isRowFull(row)) {
                removedRows.add(row);
            }
        }
        return removedRows;
    }


    public void removeFullColumn(int column) {
        //Drawing.animeCol(column);
        for(int i = 0; i< GameBoard1.HEIGHT; i++){
            board[i][column]=0;
        }
    }



    public void updateScore(int value) {
        score.set(getScore() + value);
    }


    public int[][] getBoard() {
        return board;
    }

    //보드에서 채워진 줄의 행 번호를 가져옮과 동시에 게임보드의 행을 가져온다.
    //current블럭의 xy좌표 고려해서 뺴버리면 줄이 반환된다.
    public List<List<Integer>> attackLine(List<Integer> removeLine, Brick currentBrick, GameBoard1 gameBoard){
        int i=0;
        List<List<Integer>> listOfLists = new ArrayList<>();
        if(removeLine.size()>1 && gameBoard.attackCount<10){

            for (int j = 0; j < removeLine.size(); j++) {
                int value = removeLine.get(j);
                List<Integer> firstRowList = Arrays.stream(board[value]).boxed().collect(Collectors.toList());
                for (Block block : currentBrick.getBlockList()) {
                    if (block.getX() == value) {
                        firstRowList.set(block.getY(), 0);
                    }
                }
                // 첫 번째 행을 List로 변환하여 추가
                listOfLists.add(firstRowList);
                gameBoard.attackCount+=1;
                if(gameBoard.attackCount==10){
                    break;
                }
                // 결과 확인
                System.out.println(listOfLists.get(j));  // 첫 번째 행 출력
            }
            attackBoardUpdate(listOfLists, gameBoard);
        }
        else {
            System.out.println("No Full Row----");
        }
        return listOfLists;
    }
    //어택보드의 기존 블럭들을 위로 꽉찬만큼 올리고 리턴받은 행을 어택보드에 업데이트
    public void attackBoardUpdate(List<List<Integer>> attackLine, GameBoard1 gameBoard){
        List<Integer> temp= null;
        for (int i=0; i<attackLine.size(); i++){
            temp = attackLine.get(i);
            attackBoardUp(gameBoard);
            for (int j=0; j<10; j++){
                gameBoard.attackBoard[9][j] = (int)temp.get(j);
            }
        }
        for(int n=0;n<10;n++){
            for(int m=0;m<10;m++){
                System.out.print(gameBoard.attackBoard[n][m]);
            }
            System.out.println();
        }
        //어택보드가 10칸이 넘었을 때 예외처리
    }
    //어택보드 한칸 올리기
    public void attackBoardUp(GameBoard1 gameBoard){
        for(int i=1; i<10; i++){
            for (int j=0; j<10; j++){
                gameBoard.attackBoard[i-1][j]=gameBoard.attackBoard[i][j];
            }
        }
    }
    //어택보드에 있는 것이 브릭이 닿았을 때 보드로 넘어온다.
    //2p모드 구현 전이라 임시로 내 보드로 넘어오게 끔 만들었음
    public void attackStart() {
        List<List<Integer>> temp = myAttackBoardToList();
        for(List<Integer> hi : temp) {
            System.out.println(hi);
            boardUp();
            for (int j = 0; j < 10; j++) {
                board[21][j] = hi.get(j);
            }
        }
    }
    //어택보드 초기화
    public void attackBoardClean(){
        for(int i=0; i<10; i++){
            for (int j=0; j<10; j++){
                attackBoard[i][j]=0;
            }
        }
        attackCount=0;
    }
    //어택 당했을때 내 보드 한칸 올리기
    public void boardUp(){
        for(int i=1; i<22; i++){
            for (int j=0; j<10; j++){
                board[i-1][j]=board[i][j];
            }
        }
    }
    //어택보드 값을 리스트로 바꿔서 리턴
    public List<List<Integer>> myAttackBoardToList() {
        List<List<Integer>> listMatrix = new ArrayList<>();
        for (int[] row : attackBoard) {
            if (!isAllZeros(row)) {
                List<Integer> listRow = new ArrayList<>();
                for (int value : row) {
                    listRow.add(value);
                }
                listMatrix.add(listRow);
            }
        }
        System.out.println(listMatrix);
        return listMatrix;
    }
    //어택보드가 전부0이면 false
    public boolean isAllZeros(int[] array) {
        for (int value : array) {
            if (value != 0) {
                return false;
            }
        }
        return true;
    }
}
