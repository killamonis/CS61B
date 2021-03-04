public class OffByN implements CharacterComparator {
    private int n;
    public OffByN(int distance) {
        n = distance;
    }
    @Override
    public boolean equalChars(char x, char y) {
        int xInt = x;
        int yInt = y;
        return (Math.abs(xInt - yInt) == n);
    }
}
