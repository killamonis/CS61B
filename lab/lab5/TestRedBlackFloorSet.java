import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestRedBlackFloorSet {
    @Test
    public void randomizedTest() {
       // TODO: YOUR CODE HERE
        AListFloorSet aListFloor = new AListFloorSet();
        RedBlackFloorSet redBlackFloor = new RedBlackFloorSet();
        for (int i = 0; i < 1000000; i++) {
            double random = StdRandom.uniform(-5000, 5000);
            aListFloor.add(random);
            redBlackFloor.add(random);
        }
        for (int i = 0; i < 100000; i++) {
            double randomFloor = StdRandom.uniform(-5000, 5000);
            assertEquals(aListFloor.floor(randomFloor), redBlackFloor.floor(randomFloor), 0.00001);
        }
    }
}
