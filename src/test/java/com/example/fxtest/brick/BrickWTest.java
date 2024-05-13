package com.example.fxtest.brick;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BrickWTest {
    Brick brickW;

    @BeforeEach
    void initBlock(){
        brickW=new BrickW(4, 4, Color.PALEGOLDENROD); //새로 뽑아오기
    }

    @Test
    void canRotate() {
        //given
        boolean flag;

        //when
        flag = brickW.canRotate();

        //then
        Assertions.assertEquals(flag,false);
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 85d5d17c9ebe59aa7a5f9e9fe1b74b8828e87e7c

    }


<<<<<<< HEAD
=======
    }

>>>>>>> bbe697f0f626ce69284da1bd18325a3529e425b5
=======
>>>>>>> 85d5d17c9ebe59aa7a5f9e9fe1b74b8828e87e7c
    @Test
    void canMoveRight() {
        //given
        boolean flag;

        //when
        flag=brickW.canMoveRight();

        //then
        Assertions.assertEquals(flag,true);
    }

    @Test
    void canMoveLeft() {
        //given
        boolean flag;

        //when
        flag=brickW.canMoveLeft();

        //then
        Assertions.assertEquals(flag,true);
    }

    @Test
    void canMoveDown() {
        //given
        boolean flag;

        //when
<<<<<<< HEAD
<<<<<<< HEAD
        flag=brickW.canMoveDown();
=======
        flag=brickW.canMoveRight();
>>>>>>> bbe697f0f626ce69284da1bd18325a3529e425b5
=======
        flag=brickW.canMoveDown();
>>>>>>> 85d5d17c9ebe59aa7a5f9e9fe1b74b8828e87e7c

        //then
        Assertions.assertEquals(flag,true);
    }

    @Test
    void rotate() {
        //given
        Block blockA = brickW.getA();
        Block blockB = brickW.getB();
        Block blockC = brickW.getC();
        Block blockD= brickW.getD();
        //when
        brickW.rotate();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 3);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 4);
        Assertions.assertEquals(blockC.getY(), 5);
        Assertions.assertEquals(blockD.getX(), 4);
        Assertions.assertEquals(blockD.getY(), 6);

    }

    @Test
    void moveR() {
        //given
        Block blockA = brickW.getA();
        Block blockB = brickW.getB();
        Block blockC = brickW.getC();
        Block blockD= brickW.getD();
        //when
        brickW.moveR();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 4);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 5);
        Assertions.assertEquals(blockC.getX(), 4);
        Assertions.assertEquals(blockC.getY(), 6);
        Assertions.assertEquals(blockD.getX(), 4);
        Assertions.assertEquals(blockD.getY(), 7);
    }

    @Test
    void moveL() {
        //given
        Block blockA = brickW.getA();
        Block blockB = brickW.getB();
        Block blockC = brickW.getC();
        Block blockD= brickW.getD();
        //when
        brickW.moveL();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 2);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 3);
        Assertions.assertEquals(blockC.getX(), 4);
        Assertions.assertEquals(blockC.getY(), 4);
        Assertions.assertEquals(blockD.getX(), 4);
        Assertions.assertEquals(blockD.getY(), 5);
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
    @Test
    void moveD() {
        //given
        Block blockA = brickW.getA();
        Block blockB = brickW.getB();
        Block blockC = brickW.getC();
        Block blockD= brickW.getD();
        //when
        brickW.moveD();
        //then
        Assertions.assertEquals(blockA.getX(), 5);
        Assertions.assertEquals(blockA.getY(), 5);
        Assertions.assertEquals(blockB.getX(), 5);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 5);
        Assertions.assertEquals(blockC.getY(), 3);
        Assertions.assertEquals(blockD.getX(), 5);
        Assertions.assertEquals(blockD.getY(), 2);
    }

    @Test
    void straightD() {
        //given
        Block blockA = brickW.getA();
        Block blockB = brickW.getB();
        Block blockC = brickW.getC();
        Block blockD= brickW.getD();
        //when
        brickW.straightD();
        //then
        Assertions.assertEquals(blockA.getX(), 21);
        Assertions.assertEquals(blockA.getY(), 5);
        Assertions.assertEquals(blockB.getX(), 21);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 21);
        Assertions.assertEquals(blockC.getY(), 3);
        Assertions.assertEquals(blockD.getX(), 21);
        Assertions.assertEquals(blockD.getY(), 2);
    }
>>>>>>> bbe697f0f626ce69284da1bd18325a3529e425b5
=======
>>>>>>> 85d5d17c9ebe59aa7a5f9e9fe1b74b8828e87e7c

    @Test
    void setCenter_x() {
        //given
        //when
        brickW.setCenter_x(5);
        int x = brickW.getCenter_x();
        //then
        Assertions.assertEquals(x,5);
    }

    @Test
    void setCenter_y() {
        //given
        //when
        brickW.setCenter_y(5);
        int y = brickW.getCenter_y();
        //then
        Assertions.assertEquals(y,5);
    }

    @Test
    void getBlockList() {
        //given
        List<Block> blockList = brickW.getBlockList();
        Block blockA=brickW.getA();
        Block blockB=brickW.getB();
        Block blockC=brickW.getC();
        Block blockD=brickW.getD();
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
        brickW.setItem(itemBlock);
        //then
        Assertions.assertNotEquals(itemBlock, brickW.getItem());
    }

}