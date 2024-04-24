package com.example.fxtest.brick;

import com.example.fxtest.brick.Block;

import java.util.List;

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

    public void straightD();

    public void preChange();

    public void postChange();

    public List<Block> getBlockList();

    //Getter Setter
    public Block getA();

    public void setA(Block a);

    public Block getB();

    public void setB(Block b);

    public Block getC();

    public void setC(Block c);

    public Block getD();

    public void setD(Block d);

    public int getCenter_x();

    public void setCenter_x(int center_x);

    public int getCenter_y();

    public void setCenter_y(int center_y);

    public int getShape();

    public void setShape(int shape);

    public Block getItem();


    public void setItem(Block item);


}