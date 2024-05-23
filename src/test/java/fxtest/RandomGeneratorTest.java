package fxtest;

import com.example.fxtest.GameBoard1;
import com.example.fxtest.RandomGenerator;
import com.example.fxtest.brick.Brick;
import com.example.fxtest.brick.BrickW;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class RandomGeneratorTest {

    @Test
    public void testGenerateItem() {
        RandomGenerator randomGenerator = new RandomGenerator();
        GameBoard1 gameBoard = new GameBoard1(); // 테스트용 게임 보드 생성

        // Counters for each item
        int item2Count = 0;
        int item3Count = 0;
        int item4Count = 0;
        int item5Count = 0;
        int item6Count = 0;

        // Repeat the generation process multiple times to ensure randomness
        int iterations = 10000; // 정확도를 위해 필요에 따라 조절
        for (int i = 0; i < iterations; i++) {
            Brick brick = randomGenerator.generateItem(1, false, gameBoard);
            if (brick instanceof BrickW) {
                item2Count++;
            } else {
                // 각 블록의 아이템 값을 확인하여 해당하는 카운터 증가
                if (brick.getA().getItem().getNum() == 3 ||
                        brick.getB().getItem().getNum() == 3 ||
                        brick.getC().getItem().getNum() == 3 ||
                        brick.getD().getItem().getNum() == 3) {
                    item3Count++;
                } else if (brick.getA().getItem().getNum() == 4 ||
                        brick.getB().getItem().getNum() == 4 ||
                        brick.getC().getItem().getNum() == 4 ||
                        brick.getD().getItem().getNum() == 4) {
                    item4Count++;
                } else if (brick.getA().getItem().getNum() == 5 ||
                        brick.getB().getItem().getNum() == 5 ||
                        brick.getC().getItem().getNum() == 5 ||
                        brick.getD().getItem().getNum() == 5) {
                    item5Count++;
                } else if (brick.getA().getItem().getNum() == 6 ||
                        brick.getB().getItem().getNum() == 6 ||
                        brick.getC().getItem().getNum() == 6 ||
                        brick.getD().getItem().getNum() == 6) {
                    item6Count++;
                }
            }
        }

        // Calculate probabilities
        double total = item2Count + item3Count + item4Count + item5Count + item6Count;
        double probability2 = item2Count / total;
        double probability3 = item3Count / total;
        double probability4 = item4Count / total;
        double probability5 = item5Count / total;
        double probability6 = item6Count / total;

        // Print probabilities for debugging
        System.out.println("Probability of item 2: " + probability2);
        System.out.println("Probability of item 3: " + probability3);
        System.out.println("Probability of item 4: " + probability4);
        System.out.println("Probability of item 5: " + probability5);
        System.out.println("Probability of item 6: " + probability6);

        // Assert that probabilities are within a reasonable range
        double epsilon = 0.05; // adjust as needed
        assertTrue(Math.abs(0.2 - probability2) < epsilon, "Probability of item 2 out of range");
        assertTrue(Math.abs(0.2 - probability3) < epsilon, "Probability of item 3 out of range");
        assertTrue(Math.abs(0.2 - probability4) < epsilon, "Probability of item 4 out of range");
        assertTrue(Math.abs(0.2 - probability5) < epsilon, "Probability of item 5 out of range");
        assertTrue(Math.abs(0.2 - probability6) < epsilon, "Probability of item 6 out of range");
    }
}