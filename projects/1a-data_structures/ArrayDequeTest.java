public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        test.addLast(0);
        test.get(0);
        test.addFirst(2);
        test.addFirst(3);
        test.get(2);
        test.removeLast();
        test.addFirst(6);
        test.addLast(7);
        test.get(0);
        test.addFirst(9);
        test.removeFirst();
        test.get(1);
        test.addLast(12);
        test.addFirst(13);
        test.addLast(14);
        test.addLast(15);
        test.addLast(16);
        test.removeLast();
        test.removeLast();
        test.addFirst(19);
        test.addLast(20);
        test.removeFirst();
        test.removeFirst();
        test.addLast(23);
        test.removeFirst();
        test.removeFirst();
        test.removeFirst();
        test.removeFirst();
        test.removeFirst();
        System.out.println(test.get(1));
    }
}
