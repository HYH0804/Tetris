package com.example.fxtest.brick;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class BrickLTest {

    Brick brickL;

    @BeforeEach
    void initBlock(){
        brickL =new BrickL(4, 4, Color.PALEGOLDENROD); //새로 뽑아오기
    }

    @Test
    void canRotate() {
        //given
        boolean flag;

        //when
        flag = brickL.canRotate();

        //then
        Assertions.assertEquals(flag,true);
    }

    @Test
    void canMoveRight() {
        //given
        boolean flag;

        //when
        flag= brickL.canMoveRight();

        //then
        Assertions.assertEquals(flag,true);
    }

    @Test
    void canMoveLeft() {
        //given
        boolean flag;

        //when
        flag= brickL.canMoveLeft();

        //then
        Assertions.assertEquals(flag,true);
    }

    @Test
    void canMoveDown() {
        //given
        boolean flag;

        //when
        flag= brickL.canMoveRight();

        //then
        Assertions.assertEquals(flag,true);
    }
    @Test
    void canRotate2() {
        //given
        brickL.setShape(1);
        boolean flag;

        //when
        flag = brickL.canRotate();

        //then
        Assertions.assertEquals(flag,true);

    }
    @Test
    void canRotate3() {
        //given
        brickL.setShape(2);
        boolean flag;

        //when
        flag = brickL.canRotate();

        //then
        Assertions.assertEquals(flag,true);

    }

    @Test
    void canRotate4() {
        //given
        brickL.setShape(3);
        boolean flag;

        //when
        flag = brickL.canRotate();

        //then
        Assertions.assertEquals(flag,true);

    }

    @Test
    void rotate2(){
        brickL.setShape(1);
        //given
        Block blockA = brickL.getA();
        Block blockB = brickL.getB();
        Block blockC = brickL.getC();
        Block blockD= brickL.getD();
        //when
        brickL.rotate();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 3);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 4);
        Assertions.assertEquals(blockC.getY(), 5);
        Assertions.assertEquals(blockD.getX(), 3);
        Assertions.assertEquals(blockD.getY(), 5);
    }

    @Test
    void rotate3(){
        brickL.setShape(2);
        //given
        Block blockA = brickL.getA();
        Block blockB = brickL.getB();
        Block blockC = brickL.getC();
        Block blockD= brickL.getD();
        //when
        brickL.rotate();
        //then
        Assertions.assertEquals(blockA.getX(), 3);
        Assertions.assertEquals(blockA.getY(), 4);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 5);
        Assertions.assertEquals(blockC.getY(), 4);
        Assertions.assertEquals(blockD.getX(), 5);
        Assertions.assertEquals(blockD.getY(), 5);
    }

    @Test
    void rotate4(){
        brickL.setShape(3);
        //given
        Block blockA = brickL.getA();
        Block blockB = brickL.getB();
        Block blockC = brickL.getC();
        Block blockD= brickL.getD();
        //when
        brickL.rotate();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 5);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 4);
        Assertions.assertEquals(blockC.getY(), 3);
        Assertions.assertEquals(blockD.getX(), 5);
        Assertions.assertEquals(blockD.getY(), 3);
    }

    @Test
    void rotate() {
        //given
        Block blockA = brickL.getA();
        Block blockB = brickL.getB();
        Block blockC = brickL.getC();
        Block blockD= brickL.getD();
        //when
        brickL.rotate();
        //then
        Assertions.assertEquals(blockA.getX(), 5);
        Assertions.assertEquals(blockA.getY(), 4);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 3);
        Assertions.assertEquals(blockC.getY(), 4);
        Assertions.assertEquals(blockD.getX(), 3);
        Assertions.assertEquals(blockD.getY(), 3);

    }

    @Test
    void moveR() {
        //given
        Block blockA = brickL.getA();
        Block blockB = brickL.getB();
        Block blockC = brickL.getC();
        Block blockD= brickL.getD();
        //when
        brickL.moveR();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 6);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 5);
        Assertions.assertEquals(blockC.getX(), 4);
        Assertions.assertEquals(blockC.getY(), 4);
        Assertions.assertEquals(blockD.getX(), 5);
        Assertions.assertEquals(blockD.getY(), 4);
    }

    @Test
    void moveL() {
        //given
        Block blockA = brickL.getA();
        Block blockB = brickL.getB();
        Block blockC = brickL.getC();
        Block blockD= brickL.getD();
        //when
        brickL.moveL();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 4);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 3);
        Assertions.assertEquals(blockC.getX(), 4);
        Assertions.assertEquals(blockC.getY(), 2);
        Assertions.assertEquals(blockD.getX(), 5);
        Assertions.assertEquals(blockD.getY(), 2);
    }

    @Test
    void moveD() {
        //given
        Block blockA = brickL.getA();
        Block blockB = brickL.getB();
        Block blockC = brickL.getC();
        Block blockD= brickL.getD();
        //when
        brickL.moveD();
        //then
        Assertions.assertEquals(blockA.getX(), 5);
        Assertions.assertEquals(blockA.getY(), 5);
        Assertions.assertEquals(blockB.getX(), 5);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 5);
        Assertions.assertEquals(blockC.getY(), 3);
        Assertions.assertEquals(blockD.getX(), 6);
        Assertions.assertEquals(blockD.getY(), 3);
    }

    @Test
    void straightD() {
        //given
        Block blockA = brickL.getA();
        Block blockB = brickL.getB();
        Block blockC = brickL.getC();
        Block blockD= brickL.getD();
        //when
        brickL.straightD();
        //then
        Assertions.assertEquals(blockA.getX(), 20);
        Assertions.assertEquals(blockA.getY(), 5);
        Assertions.assertEquals(blockB.getX(), 20);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 20);
        Assertions.assertEquals(blockC.getY(), 3);
        Assertions.assertEquals(blockD.getX(), 21);
        Assertions.assertEquals(blockD.getY(), 3);
    }

    @Test
    void setCenter_x() {
        //given
        //when
        brickL.setCenter_x(5);
        int x = brickL.getCenter_x();
        //then
        Assertions.assertEquals(x,5);
    }

    @Test
    void setCenter_y() {
        //given
        //when
        brickL.setCenter_y(5);
        int y = brickL.getCenter_y();
        //then
        Assertions.assertEquals(y,5);
    }

    @Test
    void getBlockList() {
        //given
        List<Block> blockList = brickL.getBlockList();
        Block blockA= brickL.getA();
        Block blockB= brickL.getB();
        Block blockC= brickL.getC();
        Block blockD= brickL.getD();
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
        brickL.setItem(itemBlock);
        //then
        Assertions.assertEquals(itemBlock, brickL.getItem());
    }
}