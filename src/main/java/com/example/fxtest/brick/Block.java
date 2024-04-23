package com.example.fxtest.brick;

import javafx.scene.paint.Color;

public class Block {

    //블록 중심좌표
    int x;
    int y;

    Item item; //그 블록이 Item 인지
    //BrickW에서만 생성과 동시에 Item 부여, 나머지 일반 블록들은 RandomGenerator에서 미리 만든 후 Setter로 부여

    Color color;

/*    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }*/

    public Block(int x, int y, Item item, Color color) {
        this.x = x;
        this.y = y;
        this.item = item;
        this.color = color;
    }

    public Block(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color=color;
    }

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
