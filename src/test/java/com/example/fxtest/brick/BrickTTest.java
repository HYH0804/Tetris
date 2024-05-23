
package com.example.fxtest.brick;

import com.example.fxtest.GameBoard1;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BrickTTest {
    Brick brickT;
    GameBoard1 gameBoard1 = new GameBoard1();

    @BeforeEach
    void initBlock(){
        brickT=new BrickT(4, 4, Color.PALEGOLDENROD,gameBoard1); //새로 뽑아오기
    }

    @Test
    void canRotate() {
        //given
        boolean flag;

        //when
        flag = brickT.canRotate();

        //then
        Assertions.assertEquals(flag,true);
    }

    @Test
    void canMoveRight() {
        //given
        boolean flag;

        //when
        flag=brickT.canMoveRight();

        //then
        Assertions.assertEquals(flag,true);
    }

    @Test
    void canMoveLeft() {
        //given
        boolean flag;

        //when
        flag=brickT.canMoveLeft();

        //then
        Assertions.assertEquals(flag,true);
    }

    @Test
    void canMoveDown() {
        //given
        boolean flag;

        //when
        flag=brickT.canMoveRight();

        //then
        Assertions.assertEquals(flag,true);
    }

    @Test
    void canRotate2() {
        //given
        brickT.setShape(1);
        boolean flag;

        //when
        flag = brickT.canRotate();

        //then
        Assertions.assertEquals(flag,true);

    }
    @Test
    void canRotate3() {
        //given
        brickT.setShape(2);
        boolean flag;

        //when
        flag = brickT.canRotate();

        //then
        Assertions.assertEquals(flag,true);

    }

    @Test
    void canRotate4() {
        //given
        brickT.setShape(3);
        boolean flag;

        //when
        flag = brickT.canRotate();

        //then
        Assertions.assertEquals(flag,true);

    }

    @Test
    void rotate2(){
        brickT.setShape(1);
        //given
        Block blockA = brickT.getA();
        Block blockB = brickT.getB();
        Block blockC = brickT.getC();
        Block blockD= brickT.getD();
        //when
        brickT.rotate();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 3);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 4);
        Assertions.assertEquals(blockC.getY(), 5);
        Assertions.assertEquals(blockD.getX(), 5);
        Assertions.assertEquals(blockD.getY(), 4);
    }

    @Test
    void rotate3(){
        brickT.setShape(2);
        //given
        Block blockA = brickT.getA();
        Block blockB = brickT.getB();
        Block blockC = brickT.getC();
        Block blockD= brickT.getD();
        //when
        brickT.rotate();
        //then
        Assertions.assertEquals(blockA.getX(), 3);
        Assertions.assertEquals(blockA.getY(), 4);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 5);
        Assertions.assertEquals(blockC.getY(), 4);
        Assertions.assertEquals(blockD.getX(), 4);
        Assertions.assertEquals(blockD.getY(), 3);
    }

    @Test
    void rotate4(){
        brickT.setShape(3);
        //given
        Block blockA = brickT.getA();
        Block blockB = brickT.getB();
        Block blockC = brickT.getC();
        Block blockD= brickT.getD();
        //when
        brickT.rotate();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 5);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 4);
        Assertions.assertEquals(blockC.getY(), 3);
        Assertions.assertEquals(blockD.getX(), 3);
        Assertions.assertEquals(blockD.getY(), 4);
    }

    @Test
    void rotate() {
        //given
        Block blockA = brickT.getA();
        Block blockB = brickT.getB();
        Block blockC = brickT.getC();
        Block blockD= brickT.getD();
        //when
        brickT.rotate();
        //then
        Assertions.assertEquals(blockA.getX(), 5);
        Assertions.assertEquals(blockA.getY(), 4);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 3);
        Assertions.assertEquals(blockC.getY(), 4);
        Assertions.assertEquals(blockD.getX(), 4);
        Assertions.assertEquals(blockD.getY(), 5);

    }

    @Test
    void moveR() {
        //given
        Block blockA = brickT.getA();
        Block blockB = brickT.getB();
        Block blockC = brickT.getC();
        Block blockD= brickT.getD();
        //when
        brickT.moveR();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 6);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 5);
        Assertions.assertEquals(blockC.getX(), 4);
        Assertions.assertEquals(blockC.getY(), 4);
        Assertions.assertEquals(blockD.getX(), 3);
        Assertions.assertEquals(blockD.getY(), 5);
    }

    @Test
    void moveL() {
        //given
        Block blockA = brickT.getA();
        Block blockB = brickT.getB();
        Block blockC = brickT.getC();
        Block blockD= brickT.getD();
        //when
        brickT.moveL();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 4);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 3);
        Assertions.assertEquals(blockC.getX(), 4);
        Assertions.assertEquals(blockC.getY(), 2);
        Assertions.assertEquals(blockD.getX(), 3);
        Assertions.assertEquals(blockD.getY(), 3);
    }

    @Test
    void moveD() {
        //given
        Block blockA = brickT.getA();
        Block blockB = brickT.getB();
        Block blockC = brickT.getC();
        Block blockD= brickT.getD();
        //when
        brickT.moveD();
        //then
        Assertions.assertEquals(blockA.getX(), 5);
        Assertions.assertEquals(blockA.getY(), 5);
        Assertions.assertEquals(blockB.getX(), 5);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 5);
        Assertions.assertEquals(blockC.getY(), 3);
        Assertions.assertEquals(blockD.getX(), 4);
        Assertions.assertEquals(blockD.getY(), 4);
    }

    @Test
    void straightD() {
        //given
        Block blockA = brickT.getA();
        Block blockB = brickT.getB();
        Block blockC = brickT.getC();
        Block blockD= brickT.getD();
        //when
        brickT.straightD();
        //then
        Assertions.assertEquals(blockA.getX(), 19);
        Assertions.assertEquals(blockA.getY(), 5);
        Assertions.assertEquals(blockB.getX(), 19);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 19);
        Assertions.assertEquals(blockC.getY(), 3);
        Assertions.assertEquals(blockD.getX(), 18);
        Assertions.assertEquals(blockD.getY(), 4);
    }

    @Test
    void setCenter_x() {
        //given
        //when
        brickT.setCenter_x(5);
        int x = brickT.getCenter_x();
        //then
        Assertions.assertEquals(x,5);
    }

    @Test
    void setCenter_y() {
        //given
        //when
        brickT.setCenter_y(5);
        int y = brickT.getCenter_y();
        //then
        Assertions.assertEquals(y,5);
    }

    @Test
    void getBlockList() {
        //given
        List<Block> blockList = brickT.getBlockList();
        Block blockA=brickT.getA();
        Block blockB=brickT.getB();
        Block blockC=brickT.getC();
        Block blockD=brickT.getD();
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
        brickT.setItem(itemBlock);
        //then
        Assertions.assertEquals(itemBlock, brickT.getItem());
    }


}
