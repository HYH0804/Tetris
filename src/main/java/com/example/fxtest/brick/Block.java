package com.example.fxtest.brick;

public class Block {

    //블록 중심좌표
    int x;
    int y;

    Item item; //그 블록이 Item 인지

/*    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }*/

    public Block(int x, int y, Item item) {
        this.x = x;
        this.y = y;
        this.item = item;
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
