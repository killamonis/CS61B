public class ArrayDeque<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;
    private double lowUsageRatio = 0.25;

    /**
     * Creates an empty ArrayDeque.
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /**
     * Circularly subtracts one from an index value.
     *
     * @param x the index we are subtracting from
     * @return an index subtracted by one circularly
     */
    private int minusIndex(int x) {
        return (x - 1 + items.length) % items.length;
    }

    /**
     * Circularly adds one to an index value.
     *
     * @param x the index we are adding to
     * @return an index plus one circularly
     */
    private int addIndex(int x) {
        return (x + 1) % items.length;
    }

    /**
     * Increases the size of the ArrayDeque by a factor of two.
     */
    private void upsize() {
        resize(items.length * 2);
    }

    /**
     * Adjusts the usage ratio based on the new size.
     * When usage ratio is less than the lowUsageRatio, the array is resized.
     */
    private void adjustUsageRatio() {
        double currentUsage = (double) size / items.length;
        if (currentUsage < lowUsageRatio) {
            resize(items.length / 2);
        }
    }

    /**
     * Creates a new ArrayDeque with a new length.
     * Changes nextFirst and nextLast to match the new ArrayDeque.
     *
     * @param capacity the desired length of the new ArrayDeque
     */
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newItems[i] = get(i);
        }
        items = newItems;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    /**
     * Adds a new item to the front of the ArrayDeque.
     *
     * @param item being added
     */
    public void addFirst(T item) {
        if (size == items.length) {
            upsize();
        }
        items[nextFirst] = item;
        nextFirst = minusIndex(nextFirst);
        size++;
    }

    /**
     * Adds a new item to the back of the ArrayDeque.
     *
     * @param item being added
     */
    public void addLast(T item) {
        if (size == items.length) {
            upsize();
        }
        items[nextLast] = item;
        nextLast = addIndex(nextLast);
        size++;
    }

    /**
     * Removes the item at the front of the ArrayDeque.
     *
     * @return item being removed
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        int first = addIndex(nextFirst);
        T removed = items[first];
        items[first] = null;
        nextFirst = first;
        size--;
        if (items.length >= 16) {
            adjustUsageRatio();
        }
        return removed;
    }

    /**
     * Removes the last item of the ArrayDeque.
     *
     * @return item being removed
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        int last = minusIndex(nextLast);
        T removed = items[last];
        items[last] = null;
        nextLast = last;
        size--;
        if (items.length >= 16) {
            adjustUsageRatio();
        }
        return removed;
    }

    /**
     * Checks if the ArrayDeque is empty.
     *
     * @return True if ArrayDeque is empty, False if not
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return size of the ArrayDeque
     */
    public int size() {
        return size;
    }

    /**
     * Retrieves the item at the given index.
     *
     * @param index of the wanted item
     * @return wanted item
     */
    public T get(int index) {
        int wantedIndex = (addIndex(nextFirst) + index) % items.length;
        return items[wantedIndex];
    }

    /**
     * Prints the ArrayDeque from front to back.
     */
    public void printDeque() {
        int printIndex = 0;
        while (printIndex < size) {
            T printItem = get(printIndex);
            System.out.print(printItem + " ");
            printIndex++;
        }
        System.out.println();
    }
}
