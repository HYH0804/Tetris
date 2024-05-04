package com.example.fxtest;

import java.util.List;

public interface GameBoard {
    int WIDTH = 10;
    int HEIGHT = 22;
    int[][] board = new int[HEIGHT][WIDTH];



    void removeFullRows();

    void removeRow(int fullRow);

    boolean isRowFull(int row);

    void updateScoreLine(int rowsRemoved);

    List<Integer> getRemovedRows();

    void removeFullColumn(int column);

    void updateScore(int value);

    int[][] getBoard();

}
