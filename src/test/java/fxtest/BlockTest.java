<<<<<<< HEAD
/*
=======
>>>>>>> bbe697f0f626ce69284da1bd18325a3529e425b5
package fxtest;

import com.example.fxtest.RandomGenerator;
import com.example.fxtest.brick.*;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BlockTest {
    RandomGenerator rg = new RandomGenerator();
    Block block = new Block(1, 2, Item.ROWDELETE, Color.PALEGOLDENROD); //3

    @Test
    void generateItem(){
        Brick itemBrick = rg.generateItem(0, false);
        Assertions.assertTrue(itemBrick instanceof Brick);
    }

    @Test
    public void getItem(){
        Item item = block.getItem();
        Assertions.assertEquals(item,Item.ROWDELETE);
    }

    @Test
    public void getColor(){
        Color color = block.getColor();
        Assertions.assertEquals(color, Color.PALEGOLDENROD);
    }

    @Test
    void setter(){
        BrickI brickI = new BrickI(1, 4, Color.BLACK);
        Block blockA = new Block(5, 5, Item.ROWDELETE, Color.BLACK);
        Block blockB = new Block(5, 5, Item.ROWDELETE, Color.BLACK);
        Block blockC = new Block(5, 5, Item.ROWDELETE, Color.BLACK);
        Block blockD = new Block(5, 5, Item.ROWDELETE, Color.BLACK);

        brickI.setA(blockA);
        brickI.setB(blockB);
        brickI.setC(blockC);
        brickI.setD(blockD);

        Assertions.assertEquals(brickI.getA().getX(), 5);
        Assertions.assertEquals(brickI.getB().getX(), 5);
        Assertions.assertEquals(brickI.getC().getX(), 5);
        Assertions.assertEquals(brickI.getD().getX(), 5);

    }

    @Test
    void setterJ(){
        BrickJ brickJ = new BrickJ(1, 4, Color.BLACK);
        Block blockA = new Block(5, 5, Item.ROWDELETE, Color.BLACK);
        Block blockB = new Block(5, 5, Item.ROWDELETE, Color.BLACK);
        Block blockC = new Block(5, 5, Item.ROWDELETE, Color.BLACK);
        Block blockD = new Block(5, 5, Item.ROWDELETE, Color.BLACK);

        brickJ.setA(blockA);
        brickJ.setB(blockB);
        brickJ.setC(blockC);
        brickJ.setD(blockD);

        Assertions.assertEquals(brickJ.getA().getX(), 5);
        Assertions.assertEquals(brickJ.getB().getX(), 5);
        Assertions.assertEquals(brickJ.getC().getX(), 5);
        Assertions.assertEquals(brickJ.getD().getX(), 5);

    }

    @Test
    void setterL(){
        BrickL brickL = new BrickL(1, 4, Color.BLACK);
        Block blockA = new Block(5, 5, Item.ROWDELETE, Color.BLACK);
        Block blockB = new Block(5, 5, Item.ROWDELETE, Color.BLACK);
        Block blockC = new Block(5, 5, Item.ROWDELETE, Color.BLACK);
        Block blockD = new Block(5, 5, Item.ROWDELETE, Color.BLACK);

        brickL.setA(blockA);
        brickL.setB(blockB);
        brickL.setC(blockC);
        brickL.setD(blockD);

        Assertions.assertEquals(brickL.getA().getX(), 5);
        Assertions.assertEquals(brickL.getB().getX(), 5);
        Assertions.assertEquals(brickL.getC().getX(), 5);
        Assertions.assertEquals(brickL.getD().getX(), 5);

    }

    @Test
    void setterO(){
        BrickO brickO = new BrickO(1, 4, Color.BLACK);
        Block blockA = new Block(5, 5, Item.ROWDELETE, Color.BLACK);
        Block blockB = new Block(5, 5, Item.ROWDELETE, Color.BLACK);
        Block blockC = new Block(5, 5, Item.ROWDELETE, Color.BLACK);
        Block blockD = new Block(5, 5, Item.ROWDELETE, Color.BLACK);

        brickO.setA(blockA);
        brickO.setB(blockB);
        brickO.setC(blockC);
        brickO.setD(blockD);

        Assertions.assertEquals(brickO.getA().getX(), 5);
        Assertions.assertEquals(brickO.getB().getX(), 5);
        Assertions.assertEquals(brickO.getC().getX(), 5);
        Assertions.assertEquals(brickO.getD().getX(), 5);

    }

    @Test
    void setterS(){
        BrickS brickS = new BrickS(1, 4, Color.BLACK);
        Block blockA = new Block(5, 5, Item.ROWDELETE, Color.BLACK);
        Block blockB = new Block(5, 5, Item.ROWDELETE, Color.BLACK);
        Block blockC = new Block(5, 5, Item.ROWDELETE, Color.BLACK);
        Block blockD = new Block(5, 5, Item.ROWDELETE, Color.BLACK);

        brickS.setA(blockA);
        brickS.setB(blockB);
        brickS.setC(blockC);
        brickS.setD(blockD);

        Assertions.assertEquals(brickS.getA().getX(), 5);
        Assertions.assertEquals(brickS.getB().getX(), 5);
        Assertions.assertEquals(brickS.getC().getX(), 5);
        Assertions.assertEquals(brickS.getD().getX(), 5);

    }

    @Test
    void setterT(){
        BrickT brickT = new BrickT(1, 4, Color.BLACK);
        Block blockA = new Block(5, 5, Item.ROWDELETE, Color.BLACK);
        Block blockB = new Block(5, 5, Item.ROWDELETE, Color.BLACK);
        Block blockC = new Block(5, 5, Item.ROWDELETE, Color.BLACK);
        Block blockD = new Block(5, 5, Item.ROWDELETE, Color.BLACK);

        brickT.setA(blockA);
        brickT.setB(blockB);
        brickT.setC(blockC);
        brickT.setD(blockD);

        Assertions.assertEquals(brickT.getA().getX(), 5);
        Assertions.assertEquals(brickT.getB().getX(), 5);
        Assertions.assertEquals(brickT.getC().getX(), 5);
        Assertions.assertEquals(brickT.getD().getX(), 5);

    }

    @Test
    void setterZ(){
        BrickZ brickZ = new BrickZ(1, 4, Color.BLACK);
        Block blockA = new Block(5, 5, Item.ROWDELETE, Color.BLACK);
        Block blockB = new Block(5, 5, Item.ROWDELETE, Color.BLACK);
        Block blockC = new Block(5, 5, Item.ROWDELETE, Color.BLACK);
        Block blockD = new Block(5, 5, Item.ROWDELETE, Color.BLACK);

        brickZ.setA(blockA);
        brickZ.setB(blockB);
        brickZ.setC(blockC);
        brickZ.setD(blockD);

        Assertions.assertEquals(brickZ.getA().getX(), 5);
        Assertions.assertEquals(brickZ.getB().getX(), 5);
        Assertions.assertEquals(brickZ.getC().getX(), 5);
        Assertions.assertEquals(brickZ.getD().getX(), 5);

    }

}
<<<<<<< HEAD
*/
=======
>>>>>>> bbe697f0f626ce69284da1bd18325a3529e425b5
