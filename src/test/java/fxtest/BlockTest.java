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

}
