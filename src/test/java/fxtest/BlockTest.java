package fxtest;

import com.example.fxtest.RandomGenerator;
import com.example.fxtest.brick.Block;
import com.example.fxtest.brick.Brick;
import com.example.fxtest.brick.BrickW;
import com.example.fxtest.brick.Item;
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


}
