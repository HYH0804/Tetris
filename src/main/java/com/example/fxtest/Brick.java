package com.example.fxtest;

import java.util.List;

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

    public List<Block> getBlockList();
}
