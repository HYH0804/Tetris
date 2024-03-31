package com.example.fxtest;

import java.util.Arrays;

public class GameBoard {
    //이거 조심
    public final static int WIDTH =10; //일단 크기로... Index는 9까지
    public final static int HEIGHT=22; //일단 크기로... 0~1 행은 블록 스폰, 2~21 행은 보드

    //Board 2차원 배열 , 있으면 1 없으면 0
    public static int[][] board = new int[HEIGHT][WIDTH]; //0으로 초기화 해야됨
    static int score=0; //점수
    static int deleteLine=0; //없앤 줄 >> 나중 속도 조절할때
    static boolean whileGame =false; //Flag가 True로 되면 게임 끝나는 속성 이벤트 리스너


    //생성자 (추후 필드 추가시 다시 봐야됨)
    public GameBoard() {
        //board 배열 0으로 전부 초기화
        for (int[] row : board) {
            Arrays.fill(row, 0);
        }
    }

    // 보드를 순회해서 해당 줄 지우고 윗 줄 내리기 ---------------------코드 긁어와서 테스트 해봐야함 -----------------------------
    //사라진 줄 기준으로 위의 모든 줄 내리는지 확인해봐야함
    public void removeFullRows() {
        for (int row = 0; row < board.length; row++) {
            if (isRowFull(row)) {
                removeRow(row);
            }
        }
    }

    public boolean isRowFull(int row) {
        for (int col = 0; col < board[row].length; col++) {
            if (board[row][col] != 1) {
                return false; // 하나라도 1이 아니면, 해당 행은 완전히 차지 않은 것
            }
        }
        return true; // 모든 열이 1이면, 해당 행은 완전히 찼음
    }

    public void removeRow(int fullRow) {
        for (int row = fullRow; row > 0; row--) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = board[row - 1][col]; // 위 행의 값을 아래 행으로 복사
            }
        }

        // 가장 윗 행을 0으로 설정
        for (int col = 0; col < board[0].length; col++) {
            board[0][col] = 0;
        }
    }
    // 보드를 순회해서 해당 줄 지우고 윗 줄 내리기 ---------------------코드 긁어와서 테스트 해봐야함 -----------------------------

    //



}
