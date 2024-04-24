package com.example.fxtest;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameBoard {
    //이거 조심
    public final static int WIDTH =10; //일단 크기로... Index는 9까지
    public final static int HEIGHT=22; //일단 크기로... 0~1 행은 블록 스폰, 2~21 행은 보드

    //Board 2차원 배열 , 있으면 1 없으면 0
    public static int[][] board = new int[HEIGHT][WIDTH]; //0으로 초기화 해야됨

    private static IntegerProperty score = new SimpleIntegerProperty(0); //점수

    static int deleteLine=0; //없앤 줄 >> 나중 속도 조절할때
    static boolean whileGame =false; //Flag가 True로 되면 게임 끝나는 속성 이벤트 리스너

    static boolean pause = false;

    //생성자 (추후 필드 추가시 다시 봐야됨)
    public GameBoard() {
        //board 배열 0으로 전부 초기화
        for (int[] row : board) {
            Arrays.fill(row, 0);
        }
    }

    public static IntegerProperty scoreProperty() {
        return score;
    }

    public static int getScore() {
        return score.get();
    }

    public static void setScore(int newScore) {
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
    private void updateScoreLine(int rowsRemoved) {
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
        for(int i=0; i<GameBoard.HEIGHT; i++){
            GameBoard.board[i][column]=0;
        }
    }


    public static void updateScore(int value) {
        score.set(getScore() + value);
    }


}
