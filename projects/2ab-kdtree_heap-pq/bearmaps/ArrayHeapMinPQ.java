package bearmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private ArrayList<PriorityNode> items;
    private HashMap<T, Integer> keys;

    public ArrayHeapMinPQ() {
        this.items = new ArrayList<>();
        this.keys = new HashMap<>();
    }

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentException if item is already present.
     * You may assume that item is never null. */
    public void add(T item, double priority) {
        if (keys.containsKey(item)) {
            throw new IllegalArgumentException();
        }
        items.add(new PriorityNode(item, priority));
        keys.put(item, size() - 1);
        swim(size() - 1);
    }

    /* Returns true if the PQ contains the given item. */
    public boolean contains(T item) {
        return keys.containsKey(item);
    }

    /*
     * Returns the minimum item. Throws NoSuchElementException
     * if the PQ is empty. */
    public T getSmallest() {
        if (items.isEmpty()) {
            throw new NoSuchElementException();
        }
        return items.get(0).getItem();
    }

    /* Removes and returns the minimum item. Throws
     * NoSuchElementException if the PQ is empty. */
    public T removeSmallest() {
        if (items.isEmpty()) {
            throw new NoSuchElementException();
        }
        T smallest = items.get(0).getItem();
        swap(0, size() - 1);
        items.remove(size() - 1);
        sink(0);
        keys.remove(smallest);
        return smallest;
    }

    /* Returns the number of items in the PQ. */
    public int size() {
        return items.size();
    }

    /* Changes the priority of the given item. Throws
     * NoSuchElementException if the item doesn't exist. */
    public void changePriority(T item, double priority) {
        if (!keys.containsKey(item)) {
            throw new NoSuchElementException();
        }
        int index = keys.get(item);
        double prevPriority = items.get(index).getPriority();
        items.get(index).setPriority(priority);
        if (prevPriority > items.get(index).getPriority()) {
            swim(index);
        } else {
            sink(index);
        }
    }

    /**
     * Moves node at param index up into its correct place in the tree.
     */
    private void swim(int index) {
        if (index == 0) {
            return;
        }
        int parent = getParentIndex(index);
        if (isLess(index, parent)) {
            swap(parent, index);
            swim(parent);
        }
    }

    /**
     * Moves node at param index down into its correct place in the tree.
     */
    private void sink(int index) {
        int lesserChild = getLeftIndex(index);
        if (size() <= lesserChild) {
            return;
        }
        int rightChild = getRightIndex(index);
        if (rightChild < size() && isLess(rightChild, lesserChild)) {
            lesserChild = rightChild;
        }
        if (isLess(index, lesserChild)) {
            return;
        }
        swap(lesserChild, index);
        sink(lesserChild);
    }

    /**
     * Swaps the nodes at index x and index y.
     */
    private void swap(int x, int y) {
        PriorityNode temp = items.get(x);
        items.set(x, items.get(y));
        items.set(y, temp);
        keys.put(items.get(x).getItem(), x);
        keys.put(items.get(y).getItem(), y);
    }

    /**
     * Returns true if the node at index x is less than the node at index y.
     */
    private boolean isLess(int x, int y) {
        return (items.get(x).compareTo(items.get(y)) <= 0);
    }

    /**
     * Returns the parent of the node at the given index.
     */
    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    /**
     * Returns the left child of the node at the given index.
     */
    private int getLeftIndex(int index) {
        return index * 2 + 1;
    }

    /**
     * Returns the right child of the node at the given index.
     */
    private int getRightIndex(int index) {
        return index * 2 + 2;
    }

    private class PriorityNode implements Comparable<PriorityNode> {
        private T item;
        private double priority;

        PriorityNode(T i, double p) {
            this.item = i;
            this.priority = p;
        }

        T getItem() {
            return this.item;
        }

        double getPriority() {
            return this.priority;
        }

        void setPriority(double newPriority) {
            this.priority = newPriority;
        }

        @Override
        public int compareTo(PriorityNode o) {
            if (o == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), o.getPriority());
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return this.getItem().equals(((PriorityNode) o).getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }
}
