package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testPeek() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(8);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        int peek = arb.peek();
        assertEquals(peek, 1);
    }

    @Test
    public void testDequeue() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(8);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        int removed = arb.dequeue();
        assertEquals(removed, 1);
    }

    @Test
    public void testFill() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(3);
        assertTrue(arb.isEmpty());
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        assertTrue(arb.isFull());
        arb.dequeue();
        assertFalse(arb.isFull());
    }

    @Test
    public void testCircular() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(3);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.dequeue();
        arb.enqueue(4);
        arb.dequeue();
        arb.enqueue(5);
        assertTrue(arb.isFull());
        ArrayRingBuffer<Integer> equalsT = new ArrayRingBuffer<>(3);
        equalsT.enqueue(3);
        equalsT.enqueue(4);
        equalsT.enqueue(5);
        assertTrue(arb.equals(equalsT));
    }



}
