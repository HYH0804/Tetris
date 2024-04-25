package com.example.fxtest.brick;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BrickJTest {

    Brick brickJ;

    @BeforeEach
    void initBlock(){
        brickJ=new BrickJ(4, 4, Color.PALEGOLDENROD); //새로 뽑아오기
    }

    @Test
    void canRotate() {
        //given
        boolean flag;

        //when
        flag = brickJ.canRotate();

        //then
        Assertions.assertEquals(flag,true);

    }

    @Test
    void canMoveRight() {
        //given
        boolean flag;

        //when
        flag=brickJ.canMoveRight();

        //then
        Assertions.assertEquals(flag,true);

    }

    @Test
    void canMoveLeft() {
        //given
        boolean flag;

        //when
        flag=brickJ.canMoveLeft();

        //then
        Assertions.assertEquals(flag,true);

    }

    @Test
    void canMoveDown() {
        //given
        boolean flag;

        //when
        flag=brickJ.canMoveRight();

        //then
        Assertions.assertEquals(flag,true);

    }
    @Test
    void canRotate2() {
        //given
        brickJ.setShape(1);
        boolean flag;

        //when
        flag = brickJ.canRotate();

        //then
        assertEquals(flag,true);

    }
    @Test
    void canRotate3() {
        //given
        brickJ.setShape(2);
        boolean flag;

        //when
        flag = brickJ.canRotate();

        //then
        Assertions.assertEquals(flag,true);

    }

    @Test
    void canRotate4() {
        //given
        brickJ.setShape(3);
        boolean flag;

        //when
        flag = brickJ.canRotate();

        //then
        assertEquals(flag,true);

    }

    @Test
    void rotate2(){
        brickJ.setShape(1);
        //given
        Block blockA = brickJ.getA();
        Block blockB = brickJ.getB();
        Block blockC = brickJ.getC();
        Block blockD= brickJ.getD();
        //when
        brickJ.rotate();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 5);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 4);
        Assertions.assertEquals(blockC.getY(), 3);
        Assertions.assertEquals(blockD.getX(), 3);
        Assertions.assertEquals(blockD.getY(), 3);
    }

    @Test
    void rotate3(){
        brickJ.setShape(2);
        //given
        Block blockA = brickJ.getA();
        Block blockB = brickJ.getB();
        Block blockC = brickJ.getC();
        Block blockD= brickJ.getD();
        //when
        brickJ.rotate();
        //then
        Assertions.assertEquals(blockA.getX(), 5);
        Assertions.assertEquals(blockA.getY(), 4);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 3);
        Assertions.assertEquals(blockC.getY(), 4);
        Assertions.assertEquals(blockD.getX(), 3);
        Assertions.assertEquals(blockD.getY(), 5);
    }

    @Test
    void rotate4(){
        brickJ.setShape(3);
        //given
        Block blockA = brickJ.getA();
        Block blockB = brickJ.getB();
        Block blockC = brickJ.getC();
        Block blockD= brickJ.getD();
        //when
        brickJ.rotate();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 3);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 4);
        Assertions.assertEquals(blockC.getY(), 5);
        Assertions.assertEquals(blockD.getX(), 5);
        Assertions.assertEquals(blockD.getY(), 5);
    }

    @Test
    void rotate() {
        //given
        Block blockA = brickJ.getA();
        Block blockB = brickJ.getB();
        Block blockC = brickJ.getC();
        Block blockD= brickJ.getD();
        //when
        brickJ.rotate();
        //then
        Assertions.assertEquals(blockA.getX(), 3);
        Assertions.assertEquals(blockA.getY(), 4);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 5);
        Assertions.assertEquals(blockC.getY(), 4);
        Assertions.assertEquals(blockD.getX(), 5);
        Assertions.assertEquals(blockD.getY(), 3);

    }

    @Test
    void moveR() {
        //given
        Block blockA = brickJ.getA();
        Block blockB = brickJ.getB();
        Block blockC = brickJ.getC();
        Block blockD= brickJ.getD();
        //when
        brickJ.moveR();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 4);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 5);
        Assertions.assertEquals(blockC.getX(), 4);
        Assertions.assertEquals(blockC.getY(), 6);
        Assertions.assertEquals(blockD.getX(), 5);
        Assertions.assertEquals(blockD.getY(), 6);


    }

    @Test
    void moveL() {
        //given
        Block blockA = brickJ.getA();
        Block blockB = brickJ.getB();
        Block blockC = brickJ.getC();
        Block blockD= brickJ.getD();
        //when
        brickJ.moveL();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 2);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 3);
        Assertions.assertEquals(blockC.getX(), 4);
        Assertions.assertEquals(blockC.getY(), 4);
        Assertions.assertEquals(blockD.getX(), 5);
        Assertions.assertEquals(blockD.getY(), 4);
    }

    @Test
    void moveD() {
        //given
        Block blockA = brickJ.getA();
        Block blockB = brickJ.getB();
        Block blockC = brickJ.getC();
        Block blockD= brickJ.getD();
        //when
        brickJ.moveD();
        //then
        assertEquals(blockA.getX(), 5);
        assertEquals(blockA.getY(), 3);
        assertEquals(blockB.getX(), 5);
        assertEquals(blockB.getY(), 4);
        assertEquals(blockC.getX(), 5);
        assertEquals(blockC.getY(), 5);
        assertEquals(blockD.getX(), 6);
        assertEquals(blockD.getY(), 5);

    }

    @Test
    void straightD() {
        //given
        Block blockA = brickJ.getA();
        Block blockB = brickJ.getB();
        Block blockC = brickJ.getC();
        Block blockD= brickJ.getD();
        //when
        brickJ.straightD();
        //then
        assertEquals(blockA.getX(), 20);
        assertEquals(blockA.getY(), 3);
        assertEquals(blockB.getX(), 20);
        assertEquals(blockB.getY(), 4);
        assertEquals(blockC.getX(), 20);
        assertEquals(blockC.getY(), 5);
        assertEquals(blockD.getX(), 21);
        assertEquals(blockD.getY(), 5);

    }

    @Test
    void setCenter_x() {
        //given
        //when
        brickJ.setCenter_x(5);
        int x = brickJ.getCenter_x();
        //then
        Assertions.assertEquals(x,5);

    }

    @Test
    void setCenter_y() {
        //given
        //when
        brickJ.setCenter_y(5);
        int y = brickJ.getCenter_y();
        //then
        Assertions.assertEquals(y,5);

    }

    @Test
    void getBlockList() {
        //given
        List<Block> blockList = brickJ.getBlockList();
        Block blockA=brickJ.getA();
        Block blockB=brickJ.getB();
        Block blockC=brickJ.getC();
        Block blockD=brickJ.getD();
        //when
        Block block1 = blockList.get(0);
        Block block2 = blockList.get(1);
        Block block3 = blockList.get(2);
        Block block4 = blockList.get(3);
        //then
        assertEquals(blockA, block1);
        assertEquals(blockB, block2);
        assertEquals(blockC, block3);
        assertEquals(blockD, block4);

    }

    @Test
    void setItem() {
        //given
        Block itemBlock = new Block(1, 2);
        //when
        brickJ.setItem(itemBlock);
        //then
        Assertions.assertEquals(itemBlock, brickJ.getItem());

    }

}