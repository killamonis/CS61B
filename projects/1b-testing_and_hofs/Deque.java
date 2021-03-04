interface Deque<T> {
    /**
     *@param item
     * Adds parameter item to the front of the deque.
     */
    void addFirst(T item);

    /**
     * @param item
     * Adds parameter item to the back of the deque.
     */
    void addLast(T item);

    /**
     * Returns the size of the LinkedList.
     */
    int size();

    /**
     * Prints items in deque from first to last without altering deque.
     */
    void printDeque();

    /**
     * Removes the first item from the deque.
     * @return removed item
     */
    T removeFirst();

    /**
     * Removes the last item from the deque.
     * @return removed item
     */
    T removeLast();

    /**
     * Retrieve the item at the given index of the deque.
     * @param index of item wanted
     * @return item at index
     */
    T get(int index);

    /**
     * @return True if deque is empty, False if not.
     */
    default boolean isEmpty() {
        return (size() == 0);
    }
}
