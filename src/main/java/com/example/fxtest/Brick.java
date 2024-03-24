package com.example.fxtest;

public interface Brick {
    public boolean canRotate();

    public boolean canMoveRight();

    public boolean canMoveLeft();

    public boolean canMoveDown();

    public void rotate();

    public void moveR();

    public void moveL();

    public void moveD();

    public void preChange();

    public void postChange();

}
