public class LinkedListDeque<T> {
    /**
     * The naked backbone of our doubly linked list deque.
     * Where the item, next, and previous pointers are held.
     */
    private class DNode {
        private DNode prev;
        private DNode next;
        private T item;

        private DNode(T i, DNode p, DNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    /**
     * The Sentinel is the middle man between DNodes and the Deque.
     * Size stores the length of the deque.
     */
    private DNode sentinel;
    private int size;

    /**
     * Returns the size of the LinkedList.
     */
    public int size() {
        return size;
    }

    /**
     * Creates an empty linked list deque.
     */
    public LinkedListDeque() {
        sentinel = new DNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**
     *@param item
     * Adds parameter item to the front of the deque.
     */
    public void addFirst(T item) {
        sentinel.next = new DNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    /**
     * @param item
     * Adds parameter item to the back of the deque.
     */
    public void addLast(T item) {
        sentinel.prev = new DNode(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    /**
     * @return True if deque is empty, False if not.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Prints items in deque from first to last without altering deque.
     */
    public void printDeque() {
        DNode printing = sentinel.next;
        while (printing != sentinel && printing != null) {
            System.out.print(printing.item + " ");
            printing = printing.next;
        }
        System.out.println();
    }

    /**
     * Removes the first item from the deque.
     * @return removed item
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T removed = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return removed;
    }

    /**
     * Removes the last item from the deque.
     * @return removed item
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T removed = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return removed;
    }

    /**
     * Retrieve the item at the given index of the deque.
     * @param index of item wanted
     * @return item at index
     */
    public T get(int index) {
        DNode copy = sentinel.next;
        int i = 0;
        if (index > size || index < 0) {
            return null;
        }
        while (i < index) {
            copy = copy.next;
            i++;
        }
        return copy.item;
    }

    /**
     * Retrieve the item at the given index of the deque using recursion.
     * @param index of item wanted
     * @return item at index
     */
    public T getRecursive(int index) {
        if (index > size || index < 0) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    /**
     * Private helper function of getRecursive.
     * @param copy Node holding item wanted
     * @param index of item wanted
     * @return item at index
     */
    private T getRecursiveHelper(DNode copy, int index) {
        if (index == 0) {
            return copy.item;
        }
        return getRecursiveHelper(copy.next, index - 1);
    }
}
