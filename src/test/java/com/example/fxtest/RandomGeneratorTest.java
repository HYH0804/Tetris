import com.example.fxtest.RandomGenerator;
import com.example.fxtest.brick.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.HashMap;
import java.util.Map;

public class RandomGeneratorTest {

    @Test
    public void testBlockDistribution() {
        RandomGenerator generator = new RandomGenerator();
        Map<Class<? extends Brick>, Integer> counts = new HashMap<>();
        int totalRuns = 1000;
        int difficulty = 0; // Test for easy mode
        boolean colorBlindness = false; // Not color blind

        // Initialize counts
        counts.put(BrickJ.class, 0);
        counts.put(BrickL.class, 0);
        counts.put(BrickO.class, 0);
        counts.put(BrickS.class, 0);
        counts.put(BrickT.class, 0);
        counts.put(BrickZ.class, 0);
        counts.put(BrickI.class, 0);

        // Generate bricks and count each type
        for (int i = 0; i < totalRuns; i++) {
            Brick brick = generator.genarateNormal(difficulty, colorBlindness); // Correct method name
            counts.put(brick.getClass(), counts.getOrDefault(brick.getClass(), 0) + 1);
        }

        // Assert each count is within expected probability limits
        double expectedPercentage = 10.0 / 72.0; // For non-I blocks
        double expectedIPercentage = 12.0 / 72.0; // For I blocks
        double errorMargin = 0.05; // 5%

        counts.forEach((key, value) -> {
            double observedPercentage = value / (double) totalRuns;
            if (key.equals(BrickI.class)) {
                Assertions.assertTrue(Math.abs(observedPercentage - expectedIPercentage) <= errorMargin,
                        "I-block frequency out of expected range: " + observedPercentage);
            } else {
                Assertions.assertTrue(Math.abs(observedPercentage - expectedPercentage) <= errorMargin,
                        "Block frequency out of expected range for " + key.getSimpleName() + ": " + observedPercentage);
            }
        });
    }
}
