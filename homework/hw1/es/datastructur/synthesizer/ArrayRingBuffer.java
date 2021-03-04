package es.datastructur.synthesizer;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.rb = (T[]) new Object[capacity];
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer overflow");
        }
        rb[last] = x;
        last = (last + 1) % capacity();
        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        T returnItem = rb[first];
        first = (first + 1) % capacity();
        fillCount--;
        return returnItem;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        return rb[first];
    }

    /**
     * Return size of the buffer.
     */
    @Override
    public int capacity() {
        return rb.length;
    }

    /**
     * Return number of items currently in the buffer.
     */
    @Override
    public int fillCount() {
        return fillCount;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArbIterator();
    }

    @Override
    public boolean equals(Object o) {
        ArrayRingBuffer<T> newO = (ArrayRingBuffer) o;
        if (newO.getClass() != this.getClass()) {
            return false;
        }
        if (newO.peek() == this.peek()) {
            return true;
        }
        return false;
    }

    private class ArbIterator implements Iterator<T> {
        private int index;
        private int fill;
        public ArbIterator() {
            this.index = 0;
            this.fill = 0;
        }

        @Override
        public boolean hasNext() {
            return fill < fillCount();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T returnItem = rb[index];
            index = (index + 1) % fillCount;
            fill++;
            return returnItem;
        }
    }

}
