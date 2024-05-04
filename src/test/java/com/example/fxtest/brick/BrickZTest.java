package com.example.fxtest.brick;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BrickZTest {
    Brick brickZ;

    @BeforeEach
    void initBlock(){
        brickZ=new BrickZ(4, 4, Color.PALEGOLDENROD); //새로 뽑아오기
    }

    @Test
    void canRotate() {
        //given
        boolean flag;

        //when
        flag = brickZ.canRotate();

        //then
        Assertions.assertEquals(flag,true);
    }

    @Test
    void canMoveRight() {
        //given
        boolean flag;

        //when
        flag=brickZ.canMoveRight();

        //then
        Assertions.assertEquals(flag,true);
    }

    @Test
    void canMoveLeft() {
        //given
        boolean flag;

        //when
        flag=brickZ.canMoveLeft();

        //then
        Assertions.assertEquals(flag,true);
    }

    @Test
    void canMoveDown() {
        //given
        boolean flag;

        //when
        flag=brickZ.canMoveRight();

        //then
        Assertions.assertEquals(flag,true);
    }

    @Test
    void canRotate2() {
        //given
        brickZ.setShape(1);
        boolean flag;

        //when
        flag = brickZ.canRotate();

        //then
        Assertions.assertEquals(flag,true);

    }
    @Test
    void canRotate3() {
        //given
        brickZ.setShape(2);
        boolean flag;

        //when
        flag = brickZ.canRotate();

        //then
        Assertions.assertEquals(flag,true);

    }

    @Test
    void canRotate4() {
        //given
        brickZ.setShape(3);
        boolean flag;

        //when
        flag = brickZ.canRotate();

        //then
        Assertions.assertEquals(flag,true);

    }

    @Test
    void rotate2(){
        brickZ.setShape(1);
        //given
        Block blockA = brickZ.getA();
        Block blockB = brickZ.getB();
        Block blockC = brickZ.getC();
        Block blockD= brickZ.getD();
        //when
        brickZ.rotate();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 5);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 3);
        Assertions.assertEquals(blockC.getY(), 4);
        Assertions.assertEquals(blockD.getX(), 3);
        Assertions.assertEquals(blockD.getY(), 3);
    }

    @Test
    void rotate3(){
        brickZ.setShape(2);
        //given
        Block blockA = brickZ.getA();
        Block blockB = brickZ.getB();
        Block blockC = brickZ.getC();
        Block blockD= brickZ.getD();
        //when
        brickZ.rotate();
        //then
        Assertions.assertEquals(blockA.getX(), 5);
        Assertions.assertEquals(blockA.getY(), 4);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 4);
        Assertions.assertEquals(blockC.getY(), 5);
        Assertions.assertEquals(blockD.getX(), 3);
        Assertions.assertEquals(blockD.getY(), 5);
    }

    @Test
    void rotate4(){
        brickZ.setShape(3);
        //given
        Block blockA = brickZ.getA();
        Block blockB = brickZ.getB();
        Block blockC = brickZ.getC();
        Block blockD= brickZ.getD();
        //when
        brickZ.rotate();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 3);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 5);
        Assertions.assertEquals(blockC.getY(), 4);
        Assertions.assertEquals(blockD.getX(), 5);
        Assertions.assertEquals(blockD.getY(), 5);
    }

    @Test
    void rotate() {
        //given
        Block blockA = brickZ.getA();
        Block blockB = brickZ.getB();
        Block blockC = brickZ.getC();
        Block blockD= brickZ.getD();
        //when
        brickZ.rotate();
        //then
        Assertions.assertEquals(blockA.getX(), 3);
        Assertions.assertEquals(blockA.getY(), 4);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 4);
        Assertions.assertEquals(blockC.getY(), 3);
        Assertions.assertEquals(blockD.getX(), 5);
        Assertions.assertEquals(blockD.getY(), 3);

    }

    @Test
    void moveR() {
        //given
        Block blockA = brickZ.getA();
        Block blockB = brickZ.getB();
        Block blockC = brickZ.getC();
        Block blockD= brickZ.getD();
        //when
        brickZ.moveR();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 4);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 5);
        Assertions.assertEquals(blockC.getX(), 5);
        Assertions.assertEquals(blockC.getY(), 5);
        Assertions.assertEquals(blockD.getX(), 5);
        Assertions.assertEquals(blockD.getY(), 6);
    }

    @Test
    void moveL() {
        //given
        Block blockA = brickZ.getA();
        Block blockB = brickZ.getB();
        Block blockC = brickZ.getC();
        Block blockD= brickZ.getD();
        //when
        brickZ.moveL();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 2);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 3);
        Assertions.assertEquals(blockC.getX(), 5);
        Assertions.assertEquals(blockC.getY(), 3);
        Assertions.assertEquals(blockD.getX(), 5);
        Assertions.assertEquals(blockD.getY(), 4);
    }

    @Test
    void moveD() {
        //given
        Block blockA = brickZ.getA();
        Block blockB = brickZ.getB();
        Block blockC = brickZ.getC();
        Block blockD= brickZ.getD();
        //when
        brickZ.moveD();
        //then
        Assertions.assertEquals(blockA.getX(), 5);
        Assertions.assertEquals(blockA.getY(), 3);
        Assertions.assertEquals(blockB.getX(), 5);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 6);
        Assertions.assertEquals(blockC.getY(), 4);
        Assertions.assertEquals(blockD.getX(), 6);
        Assertions.assertEquals(blockD.getY(), 5);
    }

    @Test
    void straightD() {
        //given
        Block blockA = brickZ.getA();
        Block blockB = brickZ.getB();
        Block blockC = brickZ.getC();
        Block blockD= brickZ.getD();
        //when
        brickZ.straightD();
        //then
        Assertions.assertEquals(blockA.getX(), 20);
        Assertions.assertEquals(blockA.getY(), 3);
        Assertions.assertEquals(blockB.getX(), 20);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 21);
        Assertions.assertEquals(blockC.getY(), 4);
        Assertions.assertEquals(blockD.getX(), 21);
        Assertions.assertEquals(blockD.getY(), 5);
    }

    @Test
    void setCenter_x() {
        //given
        //when
        brickZ.setCenter_x(5);
        int x = brickZ.getCenter_x();
        //then
        Assertions.assertEquals(x,5);
    }

    @Test
    void setCenter_y() {
        //given
        //when
        brickZ.setCenter_y(5);
        int y = brickZ.getCenter_y();
        //then
        Assertions.assertEquals(y,5);
    }

    @Test
    void getBlockList() {
        //given
        List<Block> blockList = brickZ.getBlockList();
        Block blockA=brickZ.getA();
        Block blockB=brickZ.getB();
        Block blockC=brickZ.getC();
        Block blockD=brickZ.getD();
        //when
        Block block1 = blockList.get(0);
        Block block2 = blockList.get(1);
        Block block3 = blockList.get(2);
        Block block4 = blockList.get(3);
        //then
        Assertions.assertEquals(blockA, block1);
        Assertions.assertEquals(blockB, block2);
        Assertions.assertEquals(blockC, block3);
        Assertions.assertEquals(blockD, block4);

    }

    @Test
    void setItem() {
        //given
        Block itemBlock = new Block(1, 2);
        //when
        brickZ.setItem(itemBlock);
        //then
        Assertions.assertEquals(itemBlock, brickZ.getItem());
    }

}