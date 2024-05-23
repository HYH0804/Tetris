
package com.example.fxtest.brick;

import com.example.fxtest.GameBoard1;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class BrickOTest {

    Brick brickO;
    GameBoard1 gameBoard1 = new GameBoard1();


    @BeforeEach
    void initBlock(){
        brickO =new BrickO(4, 4, Color.PALEGOLDENROD,gameBoard1); //새로 뽑아오기
    }

    @Test
    void canRotate() {
        //given
        boolean flag;

        //when
        flag = brickO.canRotate();

        //then
        Assertions.assertEquals(flag,true);
    }

    @Test
    void canMoveRight() {
        //given
        boolean flag;

        //when
        flag= brickO.canMoveRight();

        //then
        Assertions.assertEquals(flag,true);
    }

    @Test
    void canMoveLeft() {
        //given
        boolean flag;

        //when
        flag= brickO.canMoveLeft();

        //then
        Assertions.assertEquals(flag,true);
    }

    @Test
    void canMoveDown() {
        //given
        boolean flag;

        //when
        flag= brickO.canMoveDown();

        //then
        Assertions.assertEquals(flag,true);
    }
    @Test
    void canRotate2() {
        //given
        brickO.setShape(1);
        boolean flag;

        //when
        flag = brickO.canRotate();

        //then
        Assertions.assertEquals(flag,true);

    }
    @Test
    void canRotate3() {
        //given
        brickO.setShape(2);
        boolean flag;

        //when
        flag = brickO.canRotate();

        //then
        Assertions.assertEquals(flag,true);

    }

    @Test
    void canRotate4() {
        //given
        brickO.setShape(3);
        boolean flag;

        //when
        flag = brickO.canRotate();

        //then
        Assertions.assertEquals(flag,true);

    }

    @Test
    void rotate2(){
        brickO.setShape(1);
        //given
        Block blockA = brickO.getA();
        Block blockB = brickO.getB();
        Block blockC = brickO.getC();
        Block blockD= brickO.getD();
        //when
        brickO.rotate();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 3);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 5);
        Assertions.assertEquals(blockC.getY(), 3);
        Assertions.assertEquals(blockD.getX(), 5);
        Assertions.assertEquals(blockD.getY(), 4);
    }

    @Test
    void rotate3(){
        brickO.setShape(2);
        //given
        Block blockA = brickO.getA();
        Block blockB = brickO.getB();
        Block blockC = brickO.getC();
        Block blockD= brickO.getD();
        //when
        brickO.rotate();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 3);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 5);
        Assertions.assertEquals(blockC.getY(), 3);
        Assertions.assertEquals(blockD.getX(), 5);
        Assertions.assertEquals(blockD.getY(), 4);
    }

    @Test
    void rotate4(){
        brickO.setShape(3);
        //given
        Block blockA = brickO.getA();
        Block blockB = brickO.getB();
        Block blockC = brickO.getC();
        Block blockD= brickO.getD();
        //when
        brickO.rotate();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 3);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 5);
        Assertions.assertEquals(blockC.getY(), 3);
        Assertions.assertEquals(blockD.getX(), 5);
        Assertions.assertEquals(blockD.getY(), 4);
    }

    @Test
    void rotate() {
        //given
        Block blockA = brickO.getA();
        Block blockB = brickO.getB();
        Block blockC = brickO.getC();
        Block blockD= brickO.getD();
        //when
        brickO.rotate();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 3);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 5);
        Assertions.assertEquals(blockC.getY(), 3);
        Assertions.assertEquals(blockD.getX(), 5);
        Assertions.assertEquals(blockD.getY(), 4);

    }

    @Test
    void moveR() {
        //given
        Block blockA = brickO.getA();
        Block blockB = brickO.getB();
        Block blockC = brickO.getC();
        Block blockD= brickO.getD();
        //when
        brickO.moveR();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 4);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 5);
        Assertions.assertEquals(blockC.getX(), 5);
        Assertions.assertEquals(blockC.getY(), 4);
        Assertions.assertEquals(blockD.getX(), 5);
        Assertions.assertEquals(blockD.getY(), 5);
    }

    @Test
    void moveL() {
        //given
        Block blockA = brickO.getA();
        Block blockB = brickO.getB();
        Block blockC = brickO.getC();
        Block blockD= brickO.getD();
        //when
        brickO.moveL();
        //then
        Assertions.assertEquals(blockA.getX(), 4);
        Assertions.assertEquals(blockA.getY(), 2);
        Assertions.assertEquals(blockB.getX(), 4);
        Assertions.assertEquals(blockB.getY(), 3);
        Assertions.assertEquals(blockC.getX(), 5);
        Assertions.assertEquals(blockC.getY(), 2);
        Assertions.assertEquals(blockD.getX(), 5);
        Assertions.assertEquals(blockD.getY(), 3);
    }

    @Test
    void moveD() {
        //given
        Block blockA = brickO.getA();
        Block blockB = brickO.getB();
        Block blockC = brickO.getC();
        Block blockD= brickO.getD();
        //when
        brickO.moveD();
        //then
        Assertions.assertEquals(blockA.getX(), 5);
        Assertions.assertEquals(blockA.getY(), 3);
        Assertions.assertEquals(blockB.getX(), 5);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 6);
        Assertions.assertEquals(blockC.getY(), 3);
        Assertions.assertEquals(blockD.getX(), 6);
        Assertions.assertEquals(blockD.getY(), 4);
    }

    @Test
    void straightD() {
        //given
        Block blockA = brickO.getA();
        Block blockB = brickO.getB();
        Block blockC = brickO.getC();
        Block blockD= brickO.getD();
        //when
        brickO.straightD();
        //then
        Assertions.assertEquals(blockA.getX(), 18);
        Assertions.assertEquals(blockA.getY(), 3);
        Assertions.assertEquals(blockB.getX(), 18);
        Assertions.assertEquals(blockB.getY(), 4);
        Assertions.assertEquals(blockC.getX(), 19);
        Assertions.assertEquals(blockC.getY(), 3);
        Assertions.assertEquals(blockD.getX(), 19);
        Assertions.assertEquals(blockD.getY(), 4);
    }

    @Test
    void setCenter_x() {
        //given
        //when
        brickO.setCenter_x(5);
        int x = brickO.getCenter_x();
        //then
        Assertions.assertEquals(x,5);
    }

    @Test
    void setCenter_y() {
        //given
        //when
        brickO.setCenter_y(5);
        int y = brickO.getCenter_y();
        //then
        Assertions.assertEquals(y,5);
    }

    @Test
    void getBlockList() {
        //given
        List<Block> blockList = brickO.getBlockList();
        Block blockA= brickO.getA();
        Block blockB= brickO.getB();
        Block blockC= brickO.getC();
        Block blockD= brickO.getD();
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
        brickO.setItem(itemBlock);
        //then
        Assertions.assertEquals(itemBlock, brickO.getItem());
    }
}
