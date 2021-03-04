package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    @Test
    public void testAdd() {
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        test.add(3, 1.0);
        test.add(5, 5.0);
        test.add(2, 2.0);
        test.add(6, 6.0);
        assertEquals(test.getSmallest(), (Integer) 3);
    }

    @Test
    public void testChangePriority() {
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        test.add(3, 1.0);
        test.add(5, 5.0);
        test.add(2, 2.0);
        test.add(6, 6.0);
        assertEquals(test.getSmallest(), (Integer) 3);
        test.changePriority(3, 3.0);
        assertEquals(test.getSmallest(), (Integer) 2);
    }

    @Test
    public void testRemoveSmallest() {
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        test.add(3, 3.0);
        test.add(5, 5.0);
        //test.add(2, 2.0);
        //test.add(6, 6.0);
        assertEquals(test.removeSmallest(), (Integer) 3);
        assertEquals(test.getSmallest(), (Integer) 5);
        assertFalse(test.contains(2));
        assertEquals(test.size(), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddException() {
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        test.add(3, 3.0);
        test.add(5, 5.0);
        test.add(2, 2.0);
        test.add(6, 6.0);
        test.add(3, 1.0);
    }

    @Test(expected = NoSuchElementException.class)
    public void testOtherExceptions() {
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        test.removeSmallest();
        test.getSmallest();
        test.add(3, 3.0);
        test.add(5, 5.0);
        test.add(2, 2.0);
        test.add(6, 6.0);
        test.changePriority(27, 6.0);
    }

    @Test
    public void testAddTime() {
        Stopwatch swAdd = new Stopwatch();
        ArrayHeapMinPQ<Integer> amh = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 1000000; i += 1) {
            amh.add(i, i - 1);
        }
        double addTimeArray = swAdd.elapsedTime();

        swAdd = new Stopwatch();
        NaiveMinPQ<Integer> naive = new NaiveMinPQ<>();
        for (int i = 0; i < 1000000; i += 1) {
            naive.add(i, 100000 - i);
        }
        double addTimeNaive = swAdd.elapsedTime();

        Stopwatch swChange = new Stopwatch();
        for (int i = 0; i < 1000; i += 1) {
            amh.changePriority(i, i + 1);
        }
        double changeTimeArray = swChange.elapsedTime();

        swChange = new Stopwatch();
        for (int i = 0; i < 1000; i += 1) {
            naive.changePriority(i, i + 1);
        }
        double changeTimeNaive = swChange.elapsedTime();

        System.out.println("ArrayHeapMinPQ add() time: " + addTimeArray);
        System.out.println("Naive add() time: " + addTimeNaive);
        System.out.println();
        System.out.println("ArrayHeapMinPQ changePriority() time: " + changeTimeArray);
        System.out.println("Naive changePriority() time: " + changeTimeNaive);
    }

    /*private ArrayList<Integer> generateUniqueItems(int numberOfItems) {
        ArrayList<Integer> hs = new ArrayList<>();
        for (int i = 0; i < numberOfItems; i++) {
            int random = StdRandom.uniform(-numberOfItems, numberOfItems);
            if (!hs.contains(random)) {
                hs.add(random);
            }
        }
        return hs;
    }

    private ArrayList<Double> generatePriorities(int numberOfPriorities) {
        ArrayList<Double> hs = new ArrayList<>();
        for (int i = 0; i < numberOfPriorities; i++) {
            double random = StdRandom.uniform(0.0, numberOfPriorities);
            hs.add(random);
        }
        return hs;
    }*/
}
