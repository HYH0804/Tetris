package com.example.fxtest.brick;

import com.example.fxtest.brick.Block;
import com.example.fxtest.brick.Brick;
import com.example.fxtest.brick.BrickI;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class BrickITest {

    Brick brickI;

    @BeforeEach
    void initBlock(){
        brickI=new BrickI(4, 4, Color.PALEGOLDENROD); //새로 뽑아오기
    }

    @Test
    void canRotate() {
        //given
        boolean flag;

        //when
        flag = brickI.canRotate();

        //then
        Assertions.assertEquals(flag,true);
    }

    @Test
    void canMoveRight() {
        //given
        boolean flag;

        //when
        flag=brickI.canMoveRight();

        //then
        Assertions.assertEquals(flag,true);
    }

    @Test
    void canMoveLeft() {
        //given
        boolean flag;

        //when
        flag=brickI.canMoveLeft();

        //then
        Assertions.assertEquals(flag,true);
    }

    @Test
    void canMoveDown() {
        //given
        boolean flag;

        //when
        flag=brickI.canMoveRight();

        //then
        Assertions.assertEquals(flag,true);
    }

    @Test
    void canRotate2() {
        //given
        brickI.setShape(1);
        boolean flag;

        //when
        flag = brickI.canRotate();

        //then
        Assertions.assertEquals(flag,true);

    }
    @Test
    void canRotate3() {
        //given
        brickI.setShape(2);
        boolean flag;

        //when
        flag = brickI.canRotate();

        //then
        Assertions.assertEquals(flag,true);

    }

    @Test
    void canRotate4() {
        //given
        brickI.setShape(3);
        boolean flag;

        //when
        flag = brickI.canRotate();

        //then
        Assertions.assertEquals(flag,true);

    }

    @Test
    void rotate2(){
        brickI.setShape(1);
        //given
        Block blockA = brickI.getA();
        Block blockB = brickI.getB();
        Block blockC = brickI.getC();
        Block blockD= brickI.getD();
        //when
        brickI.rotate();
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
    void rotate3(){
        brickI.setShape(2);
        //given
        Block blockA = brickI.getA();
        Block blockB = brickI.getB();
        Block blockC = brickI.getC();
        Block blockD= brickI.getD();
        //when
        brickI.rotate();
        //then
        Assertions.assertEquals(blockA.getX(), 3);
        Assertions.assertEquals(blockA.getY(), 4);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 5);
        Assertions.assertEquals(blockC.getY(), 4);
        Assertions.assertEquals(blockD.getX(), 6);
        Assertions.assertEquals(blockD.getY(), 4);
    }

    @Test
    void rotate4(){
        brickI.setShape(3);
        //given
        Block blockA = brickI.getA();
        Block blockB = brickI.getB();
        Block blockC = brickI.getC();
        Block blockD= brickI.getD();
        //when
        brickI.rotate();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 5);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 4);
        Assertions.assertEquals(blockC.getY(), 3);
        Assertions.assertEquals(blockD.getX(), 4);
        Assertions.assertEquals(blockD.getY(), 2);
    }

    @Test
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 85d5d17c9ebe59aa7a5f9e9fe1b74b8828e87e7c
    void rotate5(){
        brickI.setShape(0);
        //given
        Block blockA = brickI.getA();
        Block blockB = brickI.getB();
        Block blockC = brickI.getC();
        Block blockD= brickI.getD();
        //when
        brickI.rotate();
        //then
        Assertions.assertEquals(blockA.getX(), 5);
        Assertions.assertEquals(blockA.getY(), 4);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 3);
        Assertions.assertEquals(blockC.getY(), 4);
        Assertions.assertEquals(blockD.getX(), 2);
        Assertions.assertEquals(blockD.getY(), 4);
    }

    @Test
<<<<<<< HEAD
=======
>>>>>>> bbe697f0f626ce69284da1bd18325a3529e425b5
=======
>>>>>>> 85d5d17c9ebe59aa7a5f9e9fe1b74b8828e87e7c
    void moveR() {
        //given
        Block blockA = brickI.getA();
        Block blockB = brickI.getB();
        Block blockC = brickI.getC();
        Block blockD= brickI.getD();
        //when
        brickI.moveR();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 6);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 5);
        Assertions.assertEquals(blockC.getX(), 4);
        Assertions.assertEquals(blockC.getY(), 4);
        Assertions.assertEquals(blockD.getX(), 4);
        Assertions.assertEquals(blockD.getY(), 3);
    }

    @Test
    void moveL() {
        //given
        Block blockA = brickI.getA();
        Block blockB = brickI.getB();
        Block blockC = brickI.getC();
        Block blockD= brickI.getD();
        //when
        brickI.moveL();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 4);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 3);
        Assertions.assertEquals(blockC.getX(), 4);
        Assertions.assertEquals(blockC.getY(), 2);
        Assertions.assertEquals(blockD.getX(), 4);
        Assertions.assertEquals(blockD.getY(), 1);
    }

    @Test
    void moveD() {
        //given
        Block blockA = brickI.getA();
        Block blockB = brickI.getB();
        Block blockC = brickI.getC();
        Block blockD= brickI.getD();
        //when
        brickI.moveD();
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
        Block blockA = brickI.getA();
        Block blockB = brickI.getB();
        Block blockC = brickI.getC();
        Block blockD= brickI.getD();
        //when
        brickI.straightD();
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

    @Test
    void setCenter_x() {
        //given
        //when
        brickI.setCenter_x(5);
        int x = brickI.getCenter_x();
        //then
        Assertions.assertEquals(x,5);
    }

    @Test
    void setCenter_y() {
        //given
        //when
        brickI.setCenter_y(5);
        int y = brickI.getCenter_y();
        //then
        Assertions.assertEquals(y,5);
    }

    @Test
    void getBlockList() {
        //given
        List<Block> blockList = brickI.getBlockList();
        Block blockA=brickI.getA();
        Block blockB=brickI.getB();
        Block blockC=brickI.getC();
        Block blockD=brickI.getD();
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
        brickI.setItem(itemBlock);
        //then
        Assertions.assertEquals(itemBlock, brickI.getItem());
    }
}