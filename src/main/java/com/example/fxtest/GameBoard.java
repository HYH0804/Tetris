package com.example.fxtest;

import java.util.Arrays;

public class GameBoard {
    //이거 조심
    final static int WIDTH =12; //일단 크기로... Index는 11까지 양옆 벽 추가
    final static int HEIGHT=22; //일단 크기로... Index는 21까지 위아래 벽 추가

    //Board 2차원 배열 , 있으면 1 없으면 0
    static int[][] board = new int[HEIGHT][WIDTH]; //0으로 초기화 해야됨
    static int score=0; //점수
    static int deleteLine=0; //없앤 줄 >> 나중 속도 조절할때
    static boolean gameOverFlag=false; //Flag가 True로 되면 게임 끝나는 속성 이벤트 리스너


    //생성자 (추후 필드 추가시 다시 봐야됨)
    public GameBoard() {
        //board 배열 0으로 전부 초기화 테투리 9로 설정
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                if (row == 0 || row == HEIGHT-1 || col == 0 || col == WIDTH-1) {
                    board[row][col] = 9; // 테두리 설정
                } else {
                    board[row][col] = 0; // 내부 칸 비우기
                }
            }
        }
    }

    // 보드를 순회해서 해당 줄 지우고 윗 줄 내리기 ---------------------코드 긁어와서 테스트 해봐야함 -----------------------------
    //사라진 줄 기준으로 위의 모든 줄 내리는지 확인해봐야함
    // 게임 보드에서 완전히 채워진 줄을 찾아서 삭제, 점수 업데이트
    public void removeFullRows() {
        int rowsRemoved = 0;
        for (int row = 0; row < HEIGHT; row++) {
            if (isRowFull(row)) {
                removeRow(row);
                rowsRemoved++;
                row--; // 삭제된 줄 이후의 줄들을 다시 검사하기 위해 인덱스 조정
            }
        }
        if (rowsRemoved > 0) {
            updateScore(rowsRemoved); // 삭제된 줄의 수에 따라 점수를 업데이트
        }
    }

    // 특정 줄이 완전히 채워졌는지 검사
    public boolean isRowFull(int row) {
        for (int col = 1; col < WIDTH-1; col++) {
            if (board[row][col] != 1) {
                return false; // 하나라도 비어있으면 false 반환
            }
        }
        return true; // 모든 칸이 채워져 있으면 true 반환
    }


    // 완전히 채워진 줄을 삭제, 위의 줄들을 내림
    public void removeRow(int fullRow) {
        for (int row = fullRow; row > 1; row--) {
            for (int col = 1; col < WIDTH-1; col++) {
                board[row][col] = board[row - 1][col]; // 위의 줄을 아래로 복사
            }
        }
        Arrays.fill(board[1], 1, WIDTH-1, 0); // 가장 윗 줄의 내부를 비움
    }

    // 삭제된 줄의 수에 따라 점수를 업데이트
    private void updateScore(int rowsRemoved) {
        switch (rowsRemoved) {
            case 1:
                score += 100;
                break;
            case 2:
                score += 300;
                break;
            case 3:
                score += 500;
                break;
            case 4:
                score += 800;
                break;
            default:
                break;
        }
        deleteLine += rowsRemoved;
    }
    // 보드를 순회해서 해당 줄 지우고 윗 줄 내리기 ---------------------코드 긁어와서 테스트 해봐야함 -----------------------------

    //



}